package br.com.lealdn.tp2.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.lealdn.tp2.entity.City;
import br.com.lealdn.tp2.entity.DistanceMatrix;

public class MockReader implements Reader {
    private int nCities;

    public MockReader(int nCities) {
        this.nCities = nCities;
    }

    private List<City> createCities(int n) {
        List<City> cityList = new ArrayList<City>();
        for (int i = 0; i < n; i++) {
            City city = new City(i);
            cityList.add(city);
        }

        return cityList;
    }

    // Create here the matrix
    private double[][] createCityMatrix(int size) {
        double[][] dMatrix = new double[size][size];
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            double[] row = new double[size];
            for (int j = 0; j < size; j++) {
                row[j] = 1 + (100) * r.nextDouble();
            }

            dMatrix[i] = row;
        }

        return dMatrix;
    }

    private DistanceMatrix generateMatrix(int numberOfCities) {
        List<City> cityList = createCities(numberOfCities);
        double[][] cityMatrix = createCityMatrix(numberOfCities);
        return new DistanceMatrix(cityList, cityMatrix);
    }

    @Override
    public DistanceMatrix read() {
        return this.generateMatrix(this.nCities);
    }
}
