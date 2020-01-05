package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.objects.MH_Connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Import implements RequestHandler<Integer, String> {
    public String handleRequest(Integer myCount, Context context) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        try {
            InputStream inputStream = new URL("https://import-export-mdb.s3.ap-south-1.amazonaws.com/MACPv60552.mdb").openStream();
            Files.copy(inputStream, Paths.get("/tmp/MACPv6055.mdb"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Database copied to /tmp");

            String msAccDB = "/tmp/MACPv6055.mdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            connection = DriverManager.getConnection(dbURL);
            System.out.println("Connection Successfully Created");

//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM MH_Connections");
//            System.out.println("Query Executed Successfully");

//            ResultSetMetaData metaData = resultSet.getMetaData();

            URL url = new URL("https://import-export-mdb.s3.ap-south-1.amazonaws.com/input-import.json");
            BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));

            String mh_connections_json = "";
            String i;
            while ((i = read.readLine()) != null)
                mh_connections_json += i;

            ObjectMapper mapper = new ObjectMapper();
            List<MH_Connection> mh_connections = Arrays.asList(mapper.readValue(mh_connections_json, MH_Connection[].class));

            statement = connection.createStatement();

            for (int index = 0; index < mh_connections.size(); index++) {
                MH_Connection mh_connection = mh_connections.get(index);

                statement.executeQuery("INSERT INTO MH_Connections");
            }
//            while(resultSet.next()) {
//                MH_Connection mh_connection = new MH_Connection();
//                mh_connection.ConnectionID = resultSet.getInt(metaData.getColumnName(0));
//                mh_connection.InspectionID = resultSet.getInt(metaData.getColumnName(0));
//                mh_connection.Clock_Position = resultSet.getInt(metaData.getColumnName(0));
//                mh_connection.Rim_To_Invert = resultSet.getFloat(metaData.getColumnName(0));
//                mh_connection.Direction = resultSet.getString(metaData.getColumnName(0));
//                mh_connection.Material = resultSet.getString(metaData.getColumnName(0));
//                mh_connection.Shape = resultSet.getString(metaData.getColumnName(0));
//                mh_connection.Diam_1 = resultSet.getInt(metaData.getColumnName(0));
//                mh_connection.Diam_2 = resultSet.getInt(metaData.getColumnName(0));
//                mh_connection.Pipe_Condition = resultSet.getString(metaData.getColumnName(0));
//                mh_connection.Seal_Condition = resultSet.getString(metaData.getColumnName(0));
//                mh_connection.Diam_2 = resultSet.getInt(metaData.getColumnName(0));
//                mh_connection.Pipe_Type = resultSet.getString(metaData.getColumnName(0));
//
//                mh_connections.add(mh_connection);
//            }

            System.out.println(mh_connections_json);
        }
        catch(Exception sqlex) {
            sqlex.printStackTrace();
        }
        finally {
            try {
                if(null != connection) {
                    resultSet.close();
                    statement.close();

                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return "HELLO";
    }
}
