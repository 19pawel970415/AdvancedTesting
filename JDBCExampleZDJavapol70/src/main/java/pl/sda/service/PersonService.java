package pl.sda.service;

import pl.sda.model.Person;
import pl.sda.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    /**
     * Stworzyć metodę, która zwróci wszystkie osoby (listę obiektów) urodzone przed 1990 rokiem.
     * Rozpatrujemy osoby urodzone przed 2000 rokiem.
     */

    public List<Person> getPersonBornedBefore1990() {

        List<Person> personList = new ArrayList<>();

        try {
            Connection connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM person");

            while (rs.next()) {
                String pesel = rs.getString("pesel");

                if (isPESELOlderThan1990(pesel)) {
                    int id = rs.getInt("id");
                    String name = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Person person = new Person(id, name, lastName, pesel);
                    personList.add(person);
                }

            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return personList;

    }

    private boolean isPESELOlderThan1990(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2));//dwie pierwsze cyfry z numeru PESEL

        return year < 90;
    }

}
