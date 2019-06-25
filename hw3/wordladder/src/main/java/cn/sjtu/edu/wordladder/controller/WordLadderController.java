package cn.sjtu.edu.wordladder.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.sjtu.edu.wordladder.domain.WordLadder;

@RestController
public class WordLadderController {

    @RequestMapping("")
    public ModelAndView root() {
        ModelAndView mv = new ModelAndView("redirect:/index");
        return mv;
    }

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("wordladder")
    public String register(HttpServletRequest request) throws FileNotFoundException, UnsupportedEncodingException {
        HttpClient client = HttpClients.createDefault();
 
        String urlStr = "http://localhost:8082/test";
        HttpGet get = new HttpGet(urlStr);
        String result = "";
        try {
             
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            System.out.print(result);;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ans = "";
        if(result.equals("1")) {
            String start = request.getParameter("startword");
            String destination = request.getParameter("targetword");
            WordLadder wordLadder = new WordLadder();
            wordLadder.readEnglish();
            wordLadder.inputWords(start, destination);
            ans = wordLadder.showAns();
        }
        else {
            ans = "未登录";
        }
        return ans;
    }
}