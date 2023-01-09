package org.ulearn;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSV {
    public static ArrayList<Country> csvParse(String filePath) throws IOException {
        var reader = new CSVReader(new FileReader("src/main/resources/Country.csv"), ',', '"');
        var countries = new ArrayList<Country>();

        //3 Распарсив данные в файле CSV, нужно по ним создать набор объектов, заполнив все необходимые поля.
        String[] data;
        while ((data = reader.readNext()) != null) {
            var nameOfCountry = data[0];
            var subregion = data[1];
            var region = data[2];
            long internetUsers = 0;
            long population = 0;
            try {
                internetUsers = parseLong(data[3]);
                population = parseLong(data[4]);
            }
            catch (NumberFormatException exception){
                System.out.println("Неверный формат строки.");
                continue;
            }
            countries.add(new Country(nameOfCountry, subregion, region, internetUsers, population));
        }
        return countries;
    }

    private static long parseLong(String str) {
        if (str == null || str.length() == 0) return 0;

        return Long.parseLong(str.replace(",", ""));
    }
}
