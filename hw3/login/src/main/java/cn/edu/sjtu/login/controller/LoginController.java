package cn.edu.sjtu.login.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @RequestMapping("")
    public ModelAndView root() {
        ModelAndView mv = new ModelAndView("redirect:/login");
        return mv;
    }

    @RequestMapping("/loginin")
    public ModelAndView loginin() {
        ModelAndView mv = new ModelAndView("redirect:localhost:8081");
        return mv;
    }

    @RequestMapping("/test")
    public String test() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        if (!(auth instanceof AnonymousAuthenticationToken)) { 
            return "1";
        }
        else {
            return "0";
        }
    }

}