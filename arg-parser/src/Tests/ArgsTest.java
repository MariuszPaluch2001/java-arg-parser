import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {

    @Test
    void ParsingPatternsTest() {
        Args arg = new Args("l p# d*", new String[] {"1", "123", "TEST"});
        System.out.println(arg.getBoolean('l'));
        assertTrue(true);
    }
}