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

    public int derbyInsertDataWithoutID(LinkedHashMap<String, String> dataMap) {
        int recordID = 0;
        if (!dataMap.isEmpty()) {
            connection = derbyGetConnection();
            try {
                Statement statement = connection.createStatement();
                String sqlGetLastID = "SELECT max(id) from records";
                ResultSet resultSet = statement.executeQuery(sqlGetLastID);
                resultSet.next();
                int lastID = resultSet.getInt(1);
                    String sqlInsertData = "INSERT INTO records(id,firstName,lastName,email,dayOfBirth) " +
                            "values(" + (lastID + 1) + ",'" +
                            dataMap.get("firstName") + "','" + dataMap.get("lastName") + "','" +
                            dataMap.get("email") + "','" + dataMap.get("dayOfBirth") + "')";
                    int row = statement.executeUpdate(sqlInsertData);
                    if (row > 0) {
                        System.out.println("A record has been created in Records Table");
                        recordID = lastID + 1;
                        lastID++;
                    }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recordID;
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

    public ArrayList<dbResult> derbyFetchFirstRecord() {
        connection = derbyGetConnection();
        ArrayList<dbResult> resultList = new ArrayList();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            String sqlFetchData = "SELECT * FROM records";
            resultSet = statement.executeQuery(sqlFetchData);
            if (resultSet.next()) {
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

    public int derbyFetchLastID() {
        int recordID = 0;
            connection = derbyGetConnection();
            try {
                Statement statement = connection.createStatement();
                String sqlGetLastID = "SELECT max(id) from records";
                ResultSet resultSet = statement.executeQuery(sqlGetLastID);
                resultSet.next();
                recordID = resultSet.getInt(1);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return recordID;
    }
}
