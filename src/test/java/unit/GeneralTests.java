package unit;

import org.junit.jupiter.api.Test;
import processing.General;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralTests {

    @Test
    void shouldReturn65AsMostCommonByte(){
        String text = "aaaaabcfdsfsdjfdskjfdscxc";

        byte mostCommon = General.getMostCommonCharInText(text);

        assertEquals(97, mostCommon);
    }

    @Test
    void shouldReturn0(){
        String text = "";

        byte mostCommon = General.getMostCommonCharInText(text);

        assertEquals(0, mostCommon);
    }

}
