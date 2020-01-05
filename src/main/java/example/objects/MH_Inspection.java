package example.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MH_Inspection {
    @JsonProperty("Inspection ID")
    public int InspectionID;
    @JsonProperty("Surveyed By")
    public String Surveyed_By;
    @JsonProperty("Certificate Number")
    public int Certificate_Number;
    @JsonProperty("Sheet Number")
    public int Sheet_Number;
    @JsonProperty("Date")
    public String Date;
    @JsonProperty("Time")
    public String Time;
    @JsonProperty("Street")
    public String Street;
    @JsonProperty("City")
    public String City;
    @JsonProperty("Manhole Number")
    public String Manhole_Number;
    @JsonProperty("Rim to Invert")
    public float Rim_to_Invert;
    @JsonProperty("Grade to Invert")
    public float Grade_to_Invert;
    @JsonProperty("Rim to Grade")
    public float Rim_to_Grade;
    @JsonProperty("MH Use")
    public String MH_Use;
    @JsonProperty("Purpose")
    public String Purpose;
    @JsonProperty("Pre Cleaning")
    public String Pre_Cleaning;
    @JsonProperty("Date Cleaned")
    public String Date_Cleaned;
    @JsonProperty("Location Code")
    public String Location_Code;
    @JsonProperty("Access Type")
    public String Access_Type;
    @JsonProperty("Inspection Status")
    public String Inspection_Status;
    @JsonProperty("Cone Material")
    public String Cone_Material;
    @JsonProperty("Wall Material")
    public String Wall_Material;
    @JsonProperty("Bench Present")
    public String Bench_Present;
    @JsonProperty("Step Number")
    public int Step_Number;
    @JsonProperty("Pressure Value")
    public int PressureValue;
    @JsonProperty("Inspection Level")
    public String InspectionLevel;
    @JsonProperty("Evidence of Surcharge")
    public String Evidence_of_Surcharge;

    public static ArrayList<MH_Inspection> getList(ResultSet resultSet) throws SQLException {
        ArrayList<MH_Inspection> inspections = new ArrayList<MH_Inspection>();

        while(resultSet.next()) {
            MH_Inspection mh_inspection = new MH_Inspection();
            mh_inspection.InspectionID = resultSet.getInt("InspectionID");
            mh_inspection.Surveyed_By = resultSet.getString("Surveyed_By");
            mh_inspection.Certificate_Number = resultSet.getInt("Certificate_Number");
            mh_inspection.Sheet_Number = resultSet.getInt("Sheet_Number");
            mh_inspection.Date = resultSet.getString("Date");
            mh_inspection.Time = resultSet.getString("Time");
            mh_inspection.Street = resultSet.getString("Street");
            mh_inspection.City = resultSet.getString("City");
            mh_inspection.Manhole_Number = resultSet.getString("Manhole_Number");
            mh_inspection.Rim_to_Invert = resultSet.getFloat("Rim_to_Invert");
            mh_inspection.Grade_to_Invert = resultSet.getFloat("Grade_to_Invert");
            mh_inspection.Rim_to_Grade = resultSet.getFloat("Rim_to_Grade");
            mh_inspection.Purpose = resultSet.getString("Purpose");
            mh_inspection.Pre_Cleaning = resultSet.getString("Pre-Cleaning");
            mh_inspection.Date_Cleaned = resultSet.getString("Date_Cleaned");
            mh_inspection.Location_Code = resultSet.getString("Location_Code");
            mh_inspection.Access_Type = resultSet.getString("Access_Type");
            mh_inspection.Inspection_Status = resultSet.getString("Inspection_Status");
            mh_inspection.Cone_Material = resultSet.getString("Cone_Material");
            mh_inspection.Wall_Material = resultSet.getString("Wall_Material");
            mh_inspection.Bench_Present = resultSet.getString("Bench_Present");
            mh_inspection.Step_Number = resultSet.getInt("Step_Number");
            mh_inspection.PressureValue = resultSet.getInt("PressureValue");
            mh_inspection.InspectionLevel = resultSet.getString("InspectionLevel");

            inspections.add(mh_inspection);
        }

        return inspections;
    }
}
