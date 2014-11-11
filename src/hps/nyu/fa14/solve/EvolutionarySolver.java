package hps.nyu.fa14.solve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import hps.nyu.fa14.Basis;
import hps.nyu.fa14.ISolver;
import hps.nyu.fa14.Vector;

/**
 * Implements an evolutionary solver
 * 
 * @author ck1456@nyu.edu
 */
public class EvolutionarySolver implements ISolver {

  private static Random rand = new Random();
  public int populationSize = 100;
  public int generations = 500;
  public double mutationProb = 0.9;

  @Override
  public Vector solve(Basis b) {
    List<Vector> population = new ArrayList<Vector>();
    List<Vector> parentPopulation = new ArrayList<Vector>();
    // Initial popuation with random assignments
    for(int i=0;i<populationSize;i++) {
      Vector v = getRandomAssignments(b);
      population.add(v);
      parentPopulation.add(v.clone());
    }
    Comparator<Vector> cmp = new Comparator<Vector>() {
      public int compare(Vector v1, Vector v2) {
        double len1 = v1.length();
        double len2 = v2.length();
        if(len1 > len2) {
          return 1;
        }
        else if(len1 < len2) {
          return -1;
        }
        return 0;
      }
    };
    Collections.sort(population,cmp);
    Vector bestAssignment = parentPopulation.get(0).clone();
    double bestLength = bestAssignment.length();
    int t = 0;
    while(t < generations) {
      //pick the best assignments and combine them
      for(int i=0;i<populationSize;i++) {
        Vector v = combine(parentPopulation,5);
        //mutate vector v
        v = mutate(v);
        population.set(i, v);
      }
      Collections.sort(population,cmp);
      double bestLengthInGeneration = population.get(0).length();
      if(bestLengthInGeneration < bestLength) {
        bestLength = bestLengthInGeneration;
        bestAssignment = population.get(0);
      }
      //set parent population to present population
      for(int i=0;i<populationSize;i++) {
        parentPopulation.add(population.get(i).clone());
      }
      t++;
    }
    return bestAssignment;
  }
  
  Vector mutate(Vector v) {
    for(int i=0;i<v.coef.length;i++) {
      //mutate this coeff with probability = mutationProb
      int n = rand.nextInt(100);
      if(n < mutationProb * 100) {
        //TODO: think how should it be mutated
        //right now it makes the coefficient 
        //a one-fifth of what it was earlier
        v.coef[i] = v.coef[i] / 5;
      }
    }
    return v;
  }
  
  /*
   * combine the n best parents to create a new Vector
   */
  Vector combine(List<Vector> population, int n) {
    Vector v = new Vector(population.get(0).B);
    for(int i=0;i<v.coef.length;i++) {
      //pick from the k best parents
      int k = rand.nextInt(n);
      v.coef[i] = population.get(k).coef[i];
    }
    return v;
  }

  public static Vector getRandomAssignments(Basis b) {
    Vector randomVector = new Vector(b);
    for(int i=0;i<b.vecCount;i++) {
      randomVector.coef[i] = rand.nextInt(Integer.MAX_VALUE - 1);
      if(rand.nextBoolean()) {
        randomVector.coef[i] = -randomVector.coef[i];
      }
    }
    return randomVector;
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
