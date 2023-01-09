package org.ulearn;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.sql.*;
import java.util.Formatter;
import java.util.Locale;

public class Database {

    private static Connection connection;

    //создание БД
    public static void createDb() throws SQLException, ClassNotFoundException {
        if (connection != null)
            return;

        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        var statement = connection.createStatement();

        statement.execute("drop table IF EXISTS 'country';");
        statement.execute(
                "CREATE TABLE if not exists 'country' (" +
                        "'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "'nameOfCountry' varchar(30), " +
                        "'subregion' varchar(30), " +
                        "'region' varchar(30), " +
                        "'internetUsers' INT, " +
                        "'population' INT);");
        statement.close();
    }

    //ввод данных в БД
    public static void insertData(String name, String subregion, String region, long internetUsers, long population) throws SQLException {
        final var SQL = "INSERT INTO 'country' ('nameOfCountry','subregion','region','internetUsers','population') VALUES (?,?,?,?,?);";
        var statement = connection.prepareStatement(SQL);

        statement.setString(1, name);
        statement.setString(2, subregion);
        statement.setString(3, region);
        statement.setLong(4, internetUsers);
        statement.setLong(5, population);
        statement.execute();
        statement.close();
    }
    //задание 1 Постройте график процентного соотношения пользователей в интернете от всего населения по субрегионам.
    public static PieDataset getInternetUsersPercentSubregionDatasetPieDataset() throws SQLException {
        String query = "SELECT subregion, CAST(sum(internetUsers) AS real) / sum(population) AS percent FROM country GROUP BY subregion;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        DefaultPieDataset dataset = new DefaultPieDataset();
        while (rs.next()) {
            String subregion = rs.getString("subregion");
            double percent = rs.getDouble("percent");
            dataset.setValue(subregion, percent);
        }
        statement.close();
        return dataset;
    }

    //задание 2 Выведите название страны с наименьшим кол-вом зарегистрированных в ин-ете пользователей в Восточной Европе.
    public static String getCountryNameWithSmallestRegisteredInternetUsersBySubregion(String subregion) throws SQLException {
        String query = String.format("SELECT nameOfCountry,min(internetUsers) FROM country WHERE subregion = '%s';", subregion);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        String countryName = rs.getString("nameOfCountry");
        statement.close();
        return countryName;
    }

    //задание 3 Выведите в консоль название страны процент зарегистрированных в интернете пользователей которой находится в промежутке от 75% до 85%
    public static String getCountryNameWhereInternetUsersBetween(double from, double to) throws SQLException {
        String query = new Formatter(Locale.US).format("SELECT nameOfCountry FROM country WHERE cast(internetUsers as real)/population BETWEEN %f and %f;", from, to).toString();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        String countryName = rs.getString("nameOfCountry");
        statement.close();
        return countryName;
    }
}