package cn.edu.wordladder.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;

import org.junit.jupiter.api.Test;

public class WordLadderTest{

    private static ByteArrayInputStream in;
    
    public void setInput(String input) {
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    
    @Test
    public void testReadEglish() throws FileNotFoundException {
        WordLadder wr = new WordLadder();
        wr.readEnglish();
        assertEquals(127145, wr.words.size());
    }  

    @Test
    public void testInputWords() throws FileNotFoundException {
        WordLadder wr = new WordLadder();
        wr.readEnglish();
        wr.inputWords("cat","dog");
        assertEquals("cat", wr.start);
        assertEquals("dog", wr.destination);
    }

    @Test
    public void testShowAns() throws FileNotFoundException {
        WordLadder wr = new WordLadder();
        wr.readEnglish();
        wr.inputWords("cat","dog");
        assertEquals(wr.showAns(), "dog <- dot <- cot <- cat");
    }
}
//C:\\Users\\BlackAngle\\Desktop\\java\\wordladder\\wordladder\\src\\main\\java\\wordladder\\EnglishWords.txt