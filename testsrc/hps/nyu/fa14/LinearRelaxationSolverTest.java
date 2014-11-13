package hps.nyu.fa14;

import static org.junit.Assert.assertTrue;
import hps.nyu.fa14.solve.LLLSolver;
import hps.nyu.fa14.solve.LinearRelaxationSolver;

import org.junit.Test;

public class LinearRelaxationSolverTest {

    @Test
    public void testSolveSmall() throws Exception {
        int iCount = 0;
        for(int i = 0; i < 10; i++) {
            Basis b = Basis.random(45, 40);
            ISolver s = new LinearRelaxationSolver();
            Vector v = s.solve(b);
            ISolver s2 = new LLLSolver();
            Vector v2 = s2.solve(b);

            assertTrue(v.length() <= v2.length());
            if(v.length() < v2.length()) {
                iCount++;
                System.out.println(String.format("win %d / %d", iCount, i));
            }
        }
        System.out.println(String.format("Improve percent: %d/%d", iCount, 1000));
    }
}
