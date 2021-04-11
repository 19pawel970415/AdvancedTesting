package pl.sda.statement;

import pl.sda.util.JDBCUtil;

import java.sql.*;

public class JDBCStatementExample {



    public static void main(String[] args) {

        try {
            Connection connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM person");

            int counter = 0;

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String pesel = rs.getString("pesel");

                System.out.println(id + " " + name + " " + lastName + " " + pesel);
                counter++;
            }

            System.out.println("Ilość zwróconych wierszy: " + counter);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
