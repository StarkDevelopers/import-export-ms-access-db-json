package example;

import java.sql.*;

public class MsAccess {
    Connection connection;
    Statement statement;
    public void init() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Problem in loading or registering MS Access JDBC driver");
            ex.printStackTrace();
        }
    }

    public void makeConnection(String path) throws SQLException {
        String databaseUrl = "jdbc:ucanaccess://" + path;

        this.connection = DriverManager.getConnection(databaseUrl);
        System.out.println("Connection Successfully Created");
    }

    public ResultSet executeQuery(String query) throws SQLException  {
        this.statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public void closeConnection() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
        if (this.statement != null) {
            this.statement.close();
        }
    }
}
