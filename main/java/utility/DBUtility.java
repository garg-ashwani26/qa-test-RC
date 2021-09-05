package utility;

import model.dbResult;
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
            String sqlCreateDBTable = "CREATE TABLE records(id int," +
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
                    String sqlInsertData = "INSERT INTO records(id,firstName,lastName,email,dayOfBirth) " +
                            "values(" + singleRecord.get("id") + ",'" +
                            singleRecord.get("firstName") + "','" + singleRecord.get("lastName") + "','" +
                            singleRecord.get("email") + "','" + singleRecord.get("dayOfBirth") + "')";
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

    public ArrayList<dbResult> derbyFetchData() {
        connection = derbyGetConnection();
        ArrayList<dbResult> resultList = new ArrayList();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            String sqlFetchData = "SELECT * FROM records";
            resultSet = statement.executeQuery(sqlFetchData);
            while(resultSet.next())
            {
                resultList.add(new dbResult(resultSet.getInt("id"), resultSet.getString("firstName"),
                        resultSet.getString("lastName"), resultSet.getString("email"),
                        resultSet.getDate("dayOfBirth")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<dbResult> derbyFetchDataWithCondition(String queryCondition) {
        connection = derbyGetConnection();
        ArrayList<dbResult> resultList = new ArrayList();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            String sqlFetchData = "SELECT * FROM records " + queryCondition;
            resultSet = statement.executeQuery(sqlFetchData);
            while(resultSet.next())
            {
                resultList.add(new dbResult(resultSet.getInt("id"), resultSet.getString("firstName"),
                        resultSet.getString("lastName"), resultSet.getString("email"),
                        resultSet.getDate("dayOfBirth")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
