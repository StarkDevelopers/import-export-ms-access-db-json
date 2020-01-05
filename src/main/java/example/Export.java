package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import example.objects.MH;
import example.objects.MH_Connection;
import example.objects.MH_Inspection;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Export implements RequestHandler<Integer, String> {
    public String handleRequest(Integer myCount, Context context) {
        MsAccess msAccess = new MsAccess();;
        try {
            AWS awsService = new AWS();
            awsService.init();

            msAccess.init();

            awsService.downloadFile("import-export-mdb",
                    "/tmp/MACPv6055.mdb",
                    "MACPv6055.mdb");

            msAccess.makeConnection("/tmp/MACPv6055.mdb");

            ResultSet inspectionsResultSet = msAccess.executeQuery("SELECT * FROM MH_Inspections");
            ResultSet connectionsResultSet = msAccess.executeQuery("SELECT * FROM MH_Connections");

            ArrayList<MH_Inspection> mh_inspections = MH_Inspection.getList(inspectionsResultSet);
            ArrayList<MH_Connection> mh_connections = MH_Connection.getList(connectionsResultSet);

            ArrayList<MH> mhs = MH.getList(mh_inspections, mh_connections);

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String mh_json = mapper.writeValueAsString(mhs);

            System.out.println(mh_json);

            String exportFileName = "export_" + System.currentTimeMillis() + ".json";
            Path path = Paths.get("/tmp/" + exportFileName);
            byte[] mh_jsonBytes = mh_json.getBytes();

            Files.write(path, mh_jsonBytes);

            awsService.uploadFile("import-export-mdb",
                    path.toString(),
                    exportFileName);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                msAccess.closeConnection();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "HELLO";
    }
}
