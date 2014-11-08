package hps.nyu.fa14;

import hps.nyu.fa14.solve.TrivialSolver;

import java.io.File;
import java.io.IOException;

/**
 * Given a basis for a lattice, determine the shortest vector in the lattice
 */
public class ShortestVector {

    private static Vector solve(Basis b){
        
        // TODO: Implement this better
        ISolver solver = new TrivialSolver();
        Vector vec = solver.solve(b);
        return vec;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            usage();
        }
        // first parameter is input
        String inputFile = args[0];
        String outputFile = args[1];

        Basis b = Basis.parseFile(inputFile);
        Vector vec = solve(b);
        
        // Make directory for the output file if it does not exist
        File outFile = new File(outputFile);
        outFile.getAbsoluteFile().getParentFile().mkdirs();
        vec.writeFile(outputFile);
    }

    private static void usage() {
        // How to use it
        System.out.println("java -jar ShortestVector <input> <output>");
        System.exit(1);
    }
        
}        

