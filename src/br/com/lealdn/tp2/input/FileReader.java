package br.com.lealdn.tp2.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.lealdn.tp2.entity.City;
import br.com.lealdn.tp2.entity.DistanceMatrix;

public class FileReader implements Reader {
    private String file;

    public FileReader(String file) {
        this.file = file;
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
    private double[][] createCityMatrix(int size, BufferedReader br)
            throws IOException {
        double[][] dMatrix = new double[size][size];

        String line = null;
        for (int i = 0; i < size; i++) {
            line = br.readLine();
            double[] row = new double[size];
            int j = 0;
            for (String entry : line.split(",")) {
                row[j++] = Double.valueOf(entry);
            }

            dMatrix[i] = row;
        }

        return dMatrix;
    }

    private DistanceMatrix readFile() {
        Path file = Paths.get(this.file);
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(file, Charset.forName("UTF-8"));
            int numberOfCities = Integer.valueOf(br.readLine());
            List<City> cityList = createCities(numberOfCities);
            double[][] cityMatrix = createCityMatrix(numberOfCities, br);

            return new DistanceMatrix(cityList, cityMatrix);
        } catch(Exception e) {
            // TODO: Do proper error handling
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DistanceMatrix read() {
        return readFile();
    }
}
