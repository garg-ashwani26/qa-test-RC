package utility;

import model.dbResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
                    "firstName varchar(31) CHECK(LENGTH(firstName)>1 AND LENGTH(firstname)<=30)," +
                    "lastName varchar(16) CHECK(LENGTH(lastName)>1 and LENGTH(lastName)<=15)," +
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

    public void derbyInsertData(LinkedHashMap<String, String> dataMap) {
        if (!dataMap.isEmpty()) {
            connection = derbyGetConnection();
            try {
                Statement statement = connection.createStatement();
                String sqlInsertData = "INSERT INTO records(id,firstName,lastName,email,dayOfBirth) " +
                        "values(" + dataMap.get("id") + ",'" +
                        dataMap.get("firstName") + "','" + dataMap.get("lastName") + "','" +
                        dataMap.get("email") + "','" + dataMap.get("dayOfBirth") + "')";
                int row = statement.executeUpdate(sqlInsertData);
                if (row > 0) {
                    System.out.println("A record has been created in Records Table");
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void derbyInsertData(LinkedHashMap<String, String> dataMap, Connection connection) throws SQLException {
        if (!dataMap.isEmpty()) {
            Statement statement = connection.createStatement();
            String sqlInsertData = "INSERT INTO records(id,firstName,lastName,email,dayOfBirth) " +
                    "values(" + dataMap.get("id") + ",'" +
                    dataMap.get("firstName") + "','" + dataMap.get("lastName") + "','" +
                    dataMap.get("email") + "','" + dataMap.get("dayOfBirth") + "')";
            int row = statement.executeUpdate(sqlInsertData);
            if (row > 0) {
                System.out.println("A record has been created in Records Table");
            }
        }
    }

    public void derbyInsertDataList(List<LinkedHashMap<String, String>> dataList) {
        if (!dataList.isEmpty()) {
            connection = derbyGetConnection();
            try {
                System.out.println("Number of records to be pushed in derby database: "+ dataList.size());
                for (LinkedHashMap<String, String> singleRecord : dataList) {
                    derbyInsertData(singleRecord, connection);
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
                String sqlGetLastID = "SELECT MAX(id) from records";
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
                else
                {
                    System.out.println("Record entry skipped as provided date is greater than or equal to current date");
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
                String sqlGetLastID = "SELECT MAX(id) from records";
                ResultSet resultSet = statement.executeQuery(sqlGetLastID);
                resultSet.next();
                recordID = resultSet.getInt(1);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return recordID;
    }

    public void derbyDeleteRow(int ID) {
        connection = derbyGetConnection();
        try {
            Statement statement = connection.createStatement();
            String sqlDeleteData = "DELETE FROM records where id = " + ID;
            int row = statement.executeUpdate(sqlDeleteData);
            if (row > 0) {
                System.out.println("Record ID " + ID + " has been deleted from Records Table");
            }
            connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
