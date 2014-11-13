package hps.nyu.fa14;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasisTest {

    @Test
    public void testParseFile() throws Exception {
        Basis b = Basis.parseFile("data/input1.in");
        assertEquals(23, b.vecCount);
        assertEquals(23, b.dim);
    }

}
