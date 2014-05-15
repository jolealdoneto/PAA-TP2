package br.com.leal2.tp2.algorithm;

import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.Set;
import java.util.TreeSet;

import br.com.lealdn.tp2.entity.City;
import br.com.lealdn.tp2.entity.DistanceMatrix;

public class TwoCitiesSolution {

    private SortedSet<City> A;
    private SortedSet<City> B;
    private DistanceMatrix dm;

    public TwoCitiesSolution(SortedSet<City> A, SortedSet<City> B, DistanceMatrix dm) {
        this.A = new TreeSet<City>(A);
        this.B = new TreeSet<City>(B);
        this.dm = dm;
    }

    public TwoCitiesSolution(TwoCitiesSolution sol) {
        this(sol.getA(), sol.getB(), sol.getDm());
    }

    public TwoCitiesSolution(DistanceMatrix dm) {
        this.A = new TreeSet<City>();
        this.B = new TreeSet<City>();
        this.dm = dm;
    }

    public void addToA(City city) {
        this.getA().add(city);
    }
    public void addToB(City city) {
        this.getB().add(city);
    }

    public int totalNumberOfCities() {
        return this.getA().size() + this.getB().size();
    }

    public double getSumFromSet(SortedSet<City> set) {
        double sum = 0;
        City last = null;
        for (City city : set) {
            if (last != null) {
                sum += this.dm.getDistance(last, city);
            }
            last = city;
        }

        return sum;
    }

    public double getSum() {
       return getSumFromSet(this.getA()) + getSumFromSet(this.getB()); 
    }

    /**
     * @return the a
     */
    public SortedSet<City> getA() {
        return A;
    }

    /**
     * @param a the a to set
     */
    public void setA(SortedSet<City> a) {
        A = a;
    }

    /**
     * @return the b
     */
    public SortedSet<City> getB() {
        return B;
    }

    /**
     * @param b the b to set
     */
    public void setB(SortedSet<City> b) {
        B = b;
    }

    /**
     * @return the dm
     */
    public DistanceMatrix getDm() {
        return dm;
    }

    /**
     * @param dm the dm to set
     */
    public void setDm(DistanceMatrix dm) {
        this.dm = dm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((A == null) ? 0 : A.hashCode());
        result = prime * result + ((B == null) ? 0 : B.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TwoCitiesSolution other = (TwoCitiesSolution) obj;
        if (A == null) {
            if (other.A != null)
                return false;
        } else if (!A.equals(other.A))
            return false;
        if (B == null) {
            if (other.B != null)
                return false;
        } else if (!B.equals(other.B))
            return false;
        return true;
    }
}
