package hps.nyu.fa14;

import static org.junit.Assert.assertTrue;
import hps.nyu.fa14.solve.EvolutionarySolver;

import org.junit.Test;

public class EvolutionarySolverTest {

    @Test
    public void testSolve_1() throws Exception {
        Basis b = Basis.parseFile("data/input1.in");
        ISolver s = new EvolutionarySolver();
        Vector v = s.solve(b);
        System.out.println(v.length());
        assertTrue(v.length() < 1000);
    }

}
