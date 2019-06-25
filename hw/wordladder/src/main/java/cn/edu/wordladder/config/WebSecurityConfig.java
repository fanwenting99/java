package cn.edu.wordladder.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// 允许基于HttpServletRequest使用限制访问
                .antMatchers("/").permitAll()// 不需要身份认证
                .antMatchers("/js/**", "/css/**", "/images/*", "/fonts/**", "/**/*.png", "/**/*.jpg",
                        "/templates/*.html")
                .permitAll().anyRequest().authenticated()// 其他路径必须验证身份
                .and().formLogin().loginPage("/login")// 自定义登录界面
                .permitAll().and().logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(ENCODER).withUser("user").password(ENCODER.encode("123456"))
                .roles("USER");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        // 配置模板
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        // 使用HTML的模式，也就是支持HTML5的方式，使用data-th-*的H5写法来写thymeleaf的标签语法
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 之前在application.properties中看到的缓存配置
        templateResolver.setCacheable(true);

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        // 模板引擎增加SpringSecurityDialect，让模板能用到sec前缀，获取spring security的内容
        SpringTemplateEngine engine = new SpringTemplateEngine();
        SpringSecurityDialect securityDialect = new SpringSecurityDialect();
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(securityDialect);
        engine.setAdditionalDialects(dialects);

        engine.setTemplateResolver(templateResolver());
        // 允许在内容中使用spring EL表达式
        engine.setEnableSpringELCompiler(true);

        return engine;
    }

    // 声明ViewResolver
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
}