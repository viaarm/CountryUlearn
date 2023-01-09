package org.ulearn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Country> countries;
    private static String PATH = "C:\\Users\\Artem\\IdeaProjects\\Country\\src\\main\\resources\\Country.csv";

    public static void main(String[] args) {
        try {
            countries = CSV.csvParse(PATH);
            Database.createDb();
            insertData(countries);
            new PieChart(Database.getInternetUsersPercentSubregionDatasetPieDataset());
            System.out.println("Задание 2 (наименьшее кол-во интернет пользователей в Восточной Европе:");
            System.out.println(Database.getCountryNameWithSmallestRegisteredInternetUsersBySubregion("Southern Europe"));
            System.out.println("Задание 3 (название странцы, процент интернет пользователей в которой находит в промежутке между 75 и 85%:");
            System.out.println(Database.getCountryNameWhereInternetUsersBetween(0.75, 0.8));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertData(ArrayList<Country> countries) {
        countries.forEach(country -> {
            try {
                Database.insertData(country.getNameOfCountry(), country.getSubregion(), country.getRegion(), country.getInternetUsers(), country.getPopulation());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}