package cn.edu.sjtu.task4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.sjtu.task4.domain.container;

@Controller
public class controller {
    @Autowired
    container c = new container(10);

    @RequestMapping("/")
    public void run(String s) throws InterruptedException {
        c.get(s);
    }
}