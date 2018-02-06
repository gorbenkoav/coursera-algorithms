import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class BurrowsWheelerTest {

    @Test
    void transform() throws FileNotFoundException {
        PrintStream oldOut = System.out;
        InputStream oldIn = System.in;

        System.setIn(new FileInputStream("./data/abra.txt"));

        ByteArrayOutputStream mtfEncodeOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mtfEncodeOut));

        BurrowsWheeler.main(new String[]{"-"});

        System.setIn(new ByteArrayInputStream(mtfEncodeOut.toByteArray()));
        ByteArrayOutputStream hexDumpOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(hexDumpOut));

        edu.princeton.cs.algs4.HexDump.main(new String[]{"16"});
        assertEquals("00 00 00 03 41 52 44 21 52 43 41 41 41 41 42 42\n128 bits\n", hexDumpOut.toString());

        System.setOut(oldOut);
        System.setIn(oldIn);
    }

    @Test
    void inverseTransform() {
    }
}