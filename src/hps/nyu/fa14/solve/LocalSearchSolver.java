package hps.nyu.fa14.solve;

import hps.nyu.fa14.Basis;
import hps.nyu.fa14.Vector;

/**
 * Implements the LocalSearch
 * 
 * @author ck1456@nyu.edu
 */
public class LocalSearchSolver extends AbstractSolver {

    @Override
    public Vector solve(Basis b) {

        Vector v = new TrivialSolver().solve(b);
        v = localSearch(v);
        updateIfBest(v);
        return v;
    }

    public static Vector localSearch(Vector v) {

        int tests = 0;
        // Incrementally optimize this vector
        v = v.clone(); // don't modify the original
        double bestLength = v.length();
        Vector bestVector = v.clone();

        int[] increments = new int[] { -1, 2, -1 };

        boolean improved = true;
        while (improved) {
            improved = false;
            // n^2 search over coefficient length
            cLoop: for(int j = 0; j < v.coef.length; j++) {
                for(int inc1 : increments) {
                    v.coef[j] = v.coef[j] + inc1;

                    for(int i = 0; i < v.coef.length; i++) {
                        for(int inc2 : increments) {
                            v.coef[i] = v.coef[i] + inc2;
                            tests++;
                            if(v.length() > 0 && v.length() < bestLength) {
                                bestLength = v.length();
                                bestVector = v.clone();
                                improved = true;
                                System.out.println("[" + tests + "] Improved: "
                                        + bestLength);
                                break cLoop;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Tests: " + tests);
        return bestVector;
    }
}
