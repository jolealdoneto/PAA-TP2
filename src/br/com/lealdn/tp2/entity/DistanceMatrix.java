package br.com.lealdn.tp2.entity;

import java.util.ArrayList;
import java.util.List;

public class DistanceMatrix {
    private List<City> cities;
    private double[][] distanceMatrix;

    public DistanceMatrix(List<City> cities, double[][] distanceMatrix) {
        this.cities = new ArrayList<City>(cities);
        this.distanceMatrix = distanceMatrix.clone();
    }

    private boolean checkValidId(City city) {
        return city.getId() >= 0 && city.getId() < distanceMatrix.length;
    }
    public double getDistance(City a, City b) {
        if (checkValidId(a) && checkValidId(b)) {
            return this.distanceMatrix[a.getId()][b.getId()];
        }
        return -1;
    }

    /**
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * @param cities the cities to set
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * @return the distanceMatrix
     */
    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    /**
     * @param distanceMatrix the distanceMatrix to set
     */
    public void setDistanceMatrix(double[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }
}
