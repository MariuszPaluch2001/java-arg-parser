import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {

    @Test
    void ParsingBooleanPatternsTest() {
        Args arg = new Args("l", new String[] {"-l", "0"});
        assertTrue(arg.getBoolean('l'));
    }

    @Test
    void ParsingIntegerPatternsTest() {
        Args arg = new Args("i#", new String[] {"-i", "123"});
        assertEquals(arg.getInteger('i'), 123);
    }

    @Test
    void ParsingIntegerPatternsWithWrongDataTest() {
        // @TO DO - Better tests for
        try {
            Args arg = new Args("i#", new String[] {"-i", "sto jeden"});
            arg.getInteger('i');
        } catch (NumberFormatException e){
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
}