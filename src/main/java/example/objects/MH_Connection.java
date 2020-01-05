package example.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MH_Connection {
    @JsonProperty("id")
    public int ConnectionID;
    @JsonProperty("Inspection ID")
    public int InspectionID;
    @JsonProperty("Pipe Number")
    public int Pipe_Number;
    @JsonProperty("Clock Position")
    public int Clock_Position;
    @JsonProperty("Rim to Invert")
    public float Rim_To_Invert;
    @JsonProperty("Direction")
    public String Direction;
    @JsonProperty("Material")
    public String Material;
    @JsonProperty("Shape")
    public String Shape;
    @JsonProperty("Diam 1")
    public int Diam_1;
    @JsonProperty("Diam 2")
    public int Diam_2;
    @JsonProperty("Pipe Condition")
    public String Pipe_Condition;
    @JsonProperty("Seal Condition")
    public String Seal_Condition;
    @JsonProperty("Special Condition")
    public String Special_Condition;
    @JsonProperty("Structure ID")
    public String Structure_ID;
    @JsonProperty("Comments")
    public String Comments;

    public static ArrayList<MH_Connection> getList(ResultSet resultSet) throws SQLException {
        ArrayList<MH_Connection> connections = new ArrayList<MH_Connection>();

        while(resultSet.next()) {
            MH_Connection mh_connection = new MH_Connection();
            mh_connection.ConnectionID = resultSet.getInt("ConnectionID");
            mh_connection.InspectionID = resultSet.getInt("InspectionID");
            mh_connection.Pipe_Number = resultSet.getInt("Pipe_Number");
            mh_connection.Clock_Position = resultSet.getInt("Clock_Position");
            mh_connection.Rim_To_Invert = resultSet.getFloat("Rim_To_Invert");
            mh_connection.Direction = resultSet.getString("Direction");
            mh_connection.Material = resultSet.getString("Material");
            mh_connection.Shape = resultSet.getString("Shape");
            mh_connection.Diam_1 = resultSet.getInt("Diam_1");
            mh_connection.Diam_2 = resultSet.getInt("Diam_2");
            mh_connection.Pipe_Condition = resultSet.getString("Pipe_Condition");
            mh_connection.Seal_Condition = resultSet.getString("Seal_Condition");
            mh_connection.Special_Condition = resultSet.getString("Special_Condition");
            mh_connection.Structure_ID = resultSet.getString("Structure_ID");

            connections.add(mh_connection);
        }

        return connections;
    }
}
