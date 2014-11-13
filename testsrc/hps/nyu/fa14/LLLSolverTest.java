package hps.nyu.fa14;

import static org.junit.Assert.*;
import hps.nyu.fa14.solve.LLLSolver;

import org.junit.Test;

public class LLLSolverTest {

    @Test
    public void testSolveSmall() throws Exception {
        Basis b = Basis.random(3, 3);
        ISolver s = new LLLSolver();
        Vector v = s.solve(b);
        assertNotNull(v);
        //System.out.println(v.length());
    }
    
    @Test
    public void testSolveNonSquare() throws Exception {
        Basis b = Basis.random(5, 3);
        ISolver s = new LLLSolver();
        Vector v = s.solve(b);
        assertNotNull(v);
        //System.out.println(v.length());
    }
    
    @Test
    public void testSolveMaxDim() throws Exception {
        Basis b = Basis.random(150, 150);
        ISolver s = new LLLSolver();
        Vector v = s.solve(b);
        assertNotNull(v);
        System.out.println(v.length());
    }

    @Test
    public void testSolve_1() throws Exception {
        Basis b = Basis.parseFile("data/input1.in");
        ISolver s = new LLLSolver();
        Vector v = s.solve(b);
        //System.out.println(v.length());
        assertTrue(v.length() < 156);
    }

    @Test
    public void testSolveAll() throws Exception {
        for(int i = 1; i <= 4; i++){
            testFile(i);
        }
    }

    int[] upperBounds = new int[]{
      0,
      156,
      128,
      132,
      158
    };
    
    public void testFile(int i) throws Exception {
        Basis b = Basis.parseFile(String.format("data/input%d.in", i));
        ISolver s = new LLLSolver();
        Vector v = s.solve(b);
        double vLen = v.length();
        System.out.println("Len "+vLen);
        assertTrue(String.format("Vector Length was %f > %d", vLen, upperBounds[i]), vLen <= upperBounds[i]);
    }
}
