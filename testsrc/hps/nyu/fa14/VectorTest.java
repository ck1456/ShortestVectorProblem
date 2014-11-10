package hps.nyu.fa14;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hps.nyu.fa14.solve.TrivialSolver;

import org.junit.Test;

public class VectorTest {

    @Test
    public void testZeroLength() {
        Basis b = Basis.random(24, 24);
        Vector v = b.zeroVector();
        assertEquals(0, v.length(), 1e-24);
    }
    
    @Test
    public void testNonZeroLength() {
        Basis b = Basis.random(24, 24);
        Vector v = new TrivialSolver().solve(b);
        assertTrue(v.length() > 1);
    }
    
    @Test
    public void testKnownLength() throws Exception {
        Basis b = Basis.parseFile("data/input1.in");
        Vector v = new TrivialSolver().solve(b);
        assertEquals(371.1414824564886, v.length(), 1e-12);
    }

}
