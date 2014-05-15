package br.com.leal2.tp2.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import br.com.lealdn.tp2.entity.City;
import br.com.lealdn.tp2.entity.DistanceMatrix;

public class Backtracking implements OsDoisViajantes {
    @Override
    public TwoCitiesSolution solveDoisViajantes(DistanceMatrix dm) {
        return solve(dm);
    }

    private double getMinSum(Set<TwoCitiesSolution> sols) {
        TwoCitiesSolution minSol = null;
        for (TwoCitiesSolution tcs : sols) {
            if (minSol == null || minSol.getSum() > tcs.getSum()) {
                minSol = tcs;
            }
        }

        return minSol.getSum();
    }

    private Set<TwoCitiesSolution> recursiveTreeGenerator(SortedSet<City> citiesToVisit, final TwoCitiesSolution current) {
        if (citiesToVisit.isEmpty()) {
            return new HashSet<TwoCitiesSolution>() {{
                add(current);
            }};
        }

        Set<TwoCitiesSolution> intermediateSols = new HashSet<TwoCitiesSolution>();
        City city = citiesToVisit.first();
        SortedSet<City> tailSet = new TreeSet<City>(citiesToVisit);
        tailSet.remove(city);

        TwoCitiesSolution sol1 = new TwoCitiesSolution(current);
        sol1.addToA(city);
        intermediateSols.addAll(recursiveTreeGenerator(tailSet, sol1));

        TwoCitiesSolution sol2 = new TwoCitiesSolution(current);
        sol2.addToB(city);
        if (sol2.getSum() <= getMinSum(intermediateSols)) {
            intermediateSols.addAll(recursiveTreeGenerator(tailSet, sol2));
        }
        
        return intermediateSols;
    }

    private TwoCitiesSolution solve(DistanceMatrix dm) {
        Set<TwoCitiesSolution> solBucket = recursiveTreeGenerator(new TreeSet<City>(dm.getCities()), new TwoCitiesSolution(dm));
        TwoCitiesSolution min = null;
        for (TwoCitiesSolution tcs : solBucket) {
            if (min == null || min.getSum() > tcs.getSum()) {
                min = tcs;
            }
        }

        return min;
    }
}
