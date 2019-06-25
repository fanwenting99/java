package cn.edu.wordladder.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(WordLadderController.class)
public class WordLadderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHome() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testIndex() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testInput() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/input"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}