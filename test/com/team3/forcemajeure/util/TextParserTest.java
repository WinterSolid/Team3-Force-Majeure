package com.team3.forcemajeure.util;

import com.team3.forcemajeure.util.TextParser;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

// user for testing user input at command prompt
public class TextParserTest {
    public InputStream userIn(String testCase){
        InputStream in = new ByteArrayInputStream(testCase.getBytes());
        return in;
    }

//    TODO textInputMainMenuValidQuit and textInputMainMenuInvalid
    @Test
    public void testTextInputMainMenuValidStart() {
//        start returns "game" which starts game
        InputStream in = userIn("start");
        System.setIn(in);
        String answer = TextParser.textInputMainMenu();
        assertEquals("game", answer);
    }
//    TODO testGameScannerInputInvalid testGameScannerInputHelp testGameScannerInputQuit
    @Test
    public void testGameScannerInputTwoWord() throws IOException {
//        returns 2 word command
        InputStream in = userIn("go north");
        System.setIn(in);
        String answer = TextParser.gameScannerInput();
        assertEquals("go north", answer);
    }
//    TODO testGameScannerOutput
}