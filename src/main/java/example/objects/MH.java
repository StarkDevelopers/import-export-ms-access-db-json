package example.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MH {
    @JsonProperty("Header")
    MH_Inspection Header;
    @JsonProperty("Pipe Connections")
    MH_Connection[] PipeConnections;

    public static ArrayList<MH> getList(ArrayList<MH_Inspection> inspections, ArrayList<MH_Connection> connections) {
        ArrayList<MH> mhs = new ArrayList<MH>();

        for (int i = 0; i < inspections.size(); i++) {
            MH mh = new MH();
            mh.Header = inspections.get(i);
            ArrayList<MH_Connection> mh_connections = new ArrayList<MH_Connection>();
            for (int j = 0; j < connections.size(); j++) {
                if (connections.get(i).InspectionID == mh.Header.InspectionID) {
                    mh_connections.add(connections.get(i));
                }
            }
            mh.PipeConnections = new MH_Connection[mh_connections.size()];
            mh.PipeConnections = connections.toArray(mh.PipeConnections);

            mhs.add(mh);
        }

        return mhs;
    }
}
