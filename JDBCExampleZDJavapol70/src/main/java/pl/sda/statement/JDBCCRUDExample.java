package pl.sda.statement;

import pl.sda.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCRUDExample {

    /**
     *
     * W ramach pracy domowej należy przetestować zapytanie typu CREATE TABLE, DELETE, DROP TABLE
     */

    public static void main(String[] args) {

        String insertIntoPerson = "INSERT INTO person VALUES (4, 'Michał', 'Nowak', '97122309876')";

        String updatePerson = "UPDATE person SET pesel = '98765432110' WHERE id = 1";
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            int rowCount = statement.executeUpdate(updatePerson); //tutaj wstawiamy zmienną z odpowiednim skryptem aktualizującym bazę danych
            System.out.println("Zaktualizowano " + rowCount + " wierszy");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

}
