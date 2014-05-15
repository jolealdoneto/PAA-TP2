package br.com.leal2.tp2.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.lealdn.tp2.entity.City;
import br.com.lealdn.tp2.entity.DistanceMatrix;

public class Greedy implements OsDoisViajantes {
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

    private TwoCitiesSolution checkBothWaysAndChooseBest(TwoCitiesSolution partial, City city) {
        TwoCitiesSolution sol1 = new TwoCitiesSolution(partial);
        TwoCitiesSolution sol2 = new TwoCitiesSolution(partial);

        sol1.addToA(city);
        sol2.addToB(city);

        if (sol1.getSum() > sol2.getSum()) {
            return sol2;
        }
        else {
            return sol1;
        }
    }

    private TwoCitiesSolution solve(DistanceMatrix dm) {
        Map<TwoCitiesSolution, Double> partialSums = new HashMap<TwoCitiesSolution, Double>();
        TwoCitiesSolution solution = new TwoCitiesSolution(dm);
        for (City city : dm.getCities()) {
            solution = checkBothWaysAndChooseBest(solution, city);
        }

        return solution;
    }
}
