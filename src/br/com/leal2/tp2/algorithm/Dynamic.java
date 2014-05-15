package br.com.leal2.tp2.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.lealdn.tp2.entity.City;
import br.com.lealdn.tp2.entity.DistanceMatrix;

public class Dynamic implements OsDoisViajantes {
    private DistanceMatrix dm;

    @Override
    public TwoCitiesSolution solveDoisViajantes(DistanceMatrix dm) {
        this.dm = dm;
        return solve(dm);
    }

    private TwoCitiesSolution[] createPartialSolution(TwoCitiesSolution tcs, City city) {
        TwoCitiesSolution tcs1 = new TwoCitiesSolution(tcs.getA(), tcs.getB(), tcs.getDm());
        TwoCitiesSolution tcs2 = new TwoCitiesSolution(tcs.getA(), tcs.getB(), tcs.getDm());

        tcs1.addToA(city);
        tcs2.addToB(city);

        return new TwoCitiesSolution[] { tcs1, tcs2 };
    }

    private Set<TwoCitiesSolution> getSolutionsWithSize(Map<TwoCitiesSolution, Double> partialSums, int size) {
        Set<TwoCitiesSolution> sols = new HashSet<TwoCitiesSolution>();
        for (Map.Entry<TwoCitiesSolution, Double> entry : partialSums.entrySet()) {
            if (entry.getKey().totalNumberOfCities() == size) {
                sols.add(entry.getKey());
            }
        }
        if (sols.size() == 0) {
            sols.add(new TwoCitiesSolution(this.dm));
        }
        return sols;
    }

    private TwoCitiesSolution getMinSumWithLength(Set<TwoCitiesSolution> sols) {
        TwoCitiesSolution min = null;

        for (TwoCitiesSolution sol : sols) {
            if (min == null || min.getSum() > sol.getSum()) {
                min = sol;
            }
        }

        return min;
    }


    private TwoCitiesSolution solve(final DistanceMatrix dm) {
        Set<TwoCitiesSolution> partialSums = new HashSet<TwoCitiesSolution>() {{
            add(new TwoCitiesSolution(dm));
        }};
        for (City city : dm.getCities()) {
            Set<TwoCitiesSolution> sols = new HashSet<TwoCitiesSolution>(partialSums);
            partialSums.clear();
            for (TwoCitiesSolution tcs : sols) {
                TwoCitiesSolution[] tcsPair = createPartialSolution(tcs, city);

                partialSums.add(tcsPair[0]);
                partialSums.add(tcsPair[1]);
            }
        }

        return getMinSumWithLength(partialSums);
    }

}
