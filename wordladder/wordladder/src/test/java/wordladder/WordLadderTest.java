package wordladder;

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
    public void testReadEglish() {
        WordLadder wr = new WordLadder();
        setInput("C:\\Users\\BlackAngle\\Desktop\\java\\wordladder\\wordladder\\src\\main\\java\\wordladder\\EnglishWords.txt");
        wr.readEnglish();
        assertEquals(127145, wr.words.size());
    }  

    @Test
    public void testInputWords() {
        WordLadder wr = new WordLadder();
        setInput("C:\\Users\\BlackAngle\\Desktop\\java\\wordladder\\wordladder\\src\\main\\java\\wordladder\\EnglishWords.txt");
        wr.readEnglish();
        setInput("cat13218790-*&()&\nDOG13218790-*&()&\n");
        wr.inputWords();
        assertEquals("cat", wr.start);
        assertEquals("dog", wr.destination);
    }

    @Test
    public void testShowAns() {
        WordLadder wr = new WordLadder();
        setInput("C:\\Users\\BlackAngle\\Desktop\\java\\wordladder\\wordladder\\src\\main\\java\\wordladder\\EnglishWords.txt");
        wr.readEnglish();
        setInput("cat13218790-*&()&\nDOG13218790-*&()&\n");
        wr.inputWords();
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        wr.showAns();
        assertEquals(wr.ans, "dog <- dot <- cot <- cat");
    }
}
//C:\\Users\\BlackAngle\\Desktop\\java\\wordladder\\wordladder\\src\\main\\java\\wordladder\\EnglishWords.txt