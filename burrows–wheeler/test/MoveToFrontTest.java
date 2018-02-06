import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveToFrontTest {

    @Test
    void encode() throws FileNotFoundException {
        PrintStream oldOut = System.out;
        InputStream oldIn = System.in;

        System.setIn(new FileInputStream("./data/abra.txt"));

        ByteArrayOutputStream mtfEncodeOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mtfEncodeOut));

        MoveToFront.main(new String[]{"-"});

        System.setIn(new ByteArrayInputStream(mtfEncodeOut.toByteArray()));
        ByteArrayOutputStream hexDumpOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(hexDumpOut));

        edu.princeton.cs.algs4.HexDump.main(new String[]{"16"});
        assertEquals("41 42 52 02 44 01 45 01 04 04 02 26\n96 bits\n", hexDumpOut.toString());

        System.setOut(oldOut);
        System.setIn(oldIn);
    }

    @Test
    void decode() throws FileNotFoundException {
        PrintStream oldOut = System.out;
        InputStream oldIn = System.in;

        System.setIn(new FileInputStream("./data/abra.txt"));

        ByteArrayOutputStream mtfEncodeOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mtfEncodeOut));

        MoveToFront.main(new String[]{"-"});

        System.setIn(new ByteArrayInputStream(mtfEncodeOut.toByteArray()));
        ByteArrayOutputStream mtfDecodeOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mtfDecodeOut));

        MoveToFront.main(new String[]{"+"});
        assertEquals("ABRACADABRA!", mtfDecodeOut.toString());

        System.setOut(oldOut);
        System.setIn(oldIn);
    }
}