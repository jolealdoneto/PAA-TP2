package br.com.lealdn.tp2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import br.com.leal2.tp2.algorithm.Backtracking;
import br.com.leal2.tp2.algorithm.Dynamic;
import br.com.leal2.tp2.algorithm.Greedy;
import br.com.leal2.tp2.algorithm.OsDoisViajantes;
import br.com.leal2.tp2.algorithm.TwoCitiesSolution;
import br.com.lealdn.tp2.entity.DistanceMatrix;
import br.com.lealdn.tp2.input.FileReader;
import br.com.lealdn.tp2.input.MockReader;
import br.com.lealdn.tp2.input.Reader;

public class Start {

    private static void printHelp() {
        System.out
                .println("java -jar tp2.java <input-file> <output-file> <algorithm (1,2,3)>");
        System.out.println("1 - Dynamic, 2 - Greedy, 3 - Backtracking");
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            printHelp();
            return;
        }
        final String fileIn = args[0];
        final String fileOut = args[1];
        final int algorithm = Integer.valueOf(args[2]);

        Reader reader = new FileReader(fileIn);
        //Reader reader = new MockReader(21);
        DistanceMatrix dm = reader.read();

        TwoCitiesSolution solution = Start.solve(algorithm, dm);
        Start.printSolution(solution, new PrintWriter(fileOut));
    }

    private static void printSolution(TwoCitiesSolution tcs, PrintWriter w) {
        w.println(Start.join(tcs.getA(), ","));
        w.println(Start.join(tcs.getB(), ","));
        w.println(tcs.getSumFromSet(tcs.getA()));
        w.println(tcs.getSumFromSet(tcs.getB()));

        w.close();
    }

    public static String join(SortedSet set, String sep) {
        String result = null;
        if(set != null) {
            StringBuilder sb = new StringBuilder();
            Iterator it = set.iterator();
            if(it.hasNext()) {
                sb.append(it.next().toString());
            }
            while(it.hasNext()) {
                sb.append(sep).append(it.next().toString());
            }
            result = sb.toString();
        }
        return result;
    }

    private static TwoCitiesSolution solve(int algorithm, DistanceMatrix dm) {
        OsDoisViajantes odv = null;
        switch(algorithm) {
            case 1:
                odv = new Dynamic();
                break;
            case 2:
                odv = new Greedy();
                break;
            default:
                odv = new Backtracking();
                break;
        }

        return odv.solveDoisViajantes(dm);
    }
}
