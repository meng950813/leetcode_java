package Exercise;

import java.sql.*;

public class JDBC_Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("","","");

        Statement statement = connection.createStatement();
    }
}
