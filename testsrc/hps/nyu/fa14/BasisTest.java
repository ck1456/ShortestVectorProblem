package hps.nyu.fa14;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasisTest {

    @Test
    public void testParseFile() throws Exception {
        Basis b = Basis.parseFile("data/input1.in");
        assertEquals(40, b.vecCount);
        assertEquals(50, b.dim);
    }

}
