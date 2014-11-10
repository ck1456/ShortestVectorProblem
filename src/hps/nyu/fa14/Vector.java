package hps.nyu.fa14;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Vector {

    public final Basis B;
    public final int[] coef;

    public Vector(Basis b) {
        B = b;
        coef = new int[B.vecCount];
    }

    public double length() {
        // determine the coordinates of the new vector
        int[] vec = new int[B.dim];
        for(int v = 0; v < coef.length; v++) {
            for(int d = 0; d < B.dim; d++) {
                vec[d] += (coef[v] * B.vectors[v][d]);
            }
        }

        // and then its length
        return vectorLength(vec);
    }

    public static double vectorLength(int[] vec) {
        int sum = 0;
        for(int d = 0; d < vec.length; d++) {
            sum += (vec[d] * vec[d]);
        }
        return Math.sqrt(sum);
    }

    public void write(OutputStream output) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
        for(int i = 0; i < coef.length; i++) {
            bw.write(String.format("%d", coef[i]));
            bw.newLine();
        }
        bw.close();
    }
    
    public void writeFile(String filename) throws IOException {
        write(new FileOutputStream(new File(filename)));
    }
}
