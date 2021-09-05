package utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    String jdbcURI = "jdbc:derby:ringcentral;create=true";
    Connection connection;

    public Connection derbyGetConnection() {
        try {
            connection = DriverManager.getConnection(jdbcURI);
            System.out.println("New Database connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void derbyCreateTable() {
        connection = derbyGetConnection();
        try {
            String sqlCreateDBTable = "Create table records(id int," +
                    "firstName varchar(15)," +
                    "lastName varchar(30)," +
                    "email varchar(100) UNIQUE," +
                    "dayOfBirth DATE)";

            Statement statement = connection.createStatement();
            statement.execute(sqlCreateDBTable);
            System.out.println("Database table created successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void derbyInsertData(List<LinkedHashMap<String, String>> dataList) {
        if (!dataList.isEmpty()) {
            connection = derbyGetConnection();
            try {
                Statement statement = connection.createStatement();
                for (Map<String, String> singleRecord : dataList) {
                    String sqlInsertData = "Insert into records values(" + singleRecord.get("id") +
                            singleRecord.get("firstName") + singleRecord.get("lastName") +
                            singleRecord.get("email") + singleRecord.get("dayOfBirth") + ")";
                    int row = statement.executeUpdate(sqlInsertData);
                    if (row > 0) {
                        System.out.println("A record has been created in Records Table");
                    }
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void derbyFetchData() {
        connection = derbyGetConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlFetchData = "Select * from records";
            ResultSet resultSet = statement.executeQuery(sqlFetchData);
            resultSet.first();
            System.out.println(resultSet.getInt("id") + resultSet.getString("firstName"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
