package pl.sda.preparedstatement;

import pl.sda.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPreparedStatementExample {

    public static void main(String[] args) {

        String selectFromPerson = "SELECT * FROM person WHERE last_name = ?";
        String updatePerson = "UPDATE person SET last_name = ? WHERE id = ?";

        try {
            Connection connection = JDBCUtil.getConnection();

            PreparedStatement psUpdate = connection.prepareStatement(updatePerson);
            psUpdate.setString(1,"Kowalski");
            psUpdate.setInt(2,3);
            psUpdate.executeUpdate();

            //********************************************************************

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE last_name = ?");

            preparedStatement.setString(1, "Kowalski");

            ResultSet rs = preparedStatement.executeQuery();

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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
