package cn.edu.wordladder.controller;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.wordladder.domain.WordLadder;

@RestController
public class WordLadderController {

    @RequestMapping("/")
    public ModelAndView root() {
        ModelAndView mv = new ModelAndView("redirect:/index");
        return mv;
    }
    

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/input")
    public ModelAndView input() {
        ModelAndView mv = new ModelAndView("input");
        return mv;
    }

    @RequestMapping("/wordladder")
    public String register(HttpServletRequest request) throws FileNotFoundException {
        String start = request.getParameter("startword");
        String destination = request.getParameter("targetword");
        WordLadder wordLadder = new WordLadder();
        wordLadder.readEnglish();
        wordLadder.inputWords(start, destination);
        String ans = wordLadder.showAns();
        return ans;
    }
}