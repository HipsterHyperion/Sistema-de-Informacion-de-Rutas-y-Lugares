

package modelo.entidad;


import java.util.ArrayList;
import java.util.List;
import modelo.waypoint.EventWaypoint;
import modelo.waypoint.MyWaypoint;
import org.jxmapviewer.viewer.GeoPosition;


public class PlaceVisit extends MyEvent{
    
    
    private String address;
    private boolean visitImportance;
    private List<GeoPosition> childVisits;
    private String name;
    private List<MyWaypoint> waypoints;

    public PlaceVisit() {
        childVisits = new ArrayList<>();
        waypoints = new ArrayList<>();
        name = "";
    }

    public GeoPosition getLocation() {
        return this.getMainGeoPosition();
    }

    public void setLocation(GeoPosition location) {
        this.setMainGeoPosition(location);
    }

    public void setLocation(double lat, double lon) {
        this.setMainGeoPosition( new GeoPosition(lat,lon));
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisitImportance() {
        return visitImportance;
    }

    public void setVisitImportance(boolean visitImportance) {
        this.visitImportance = visitImportance;
    }

    public List<GeoPosition> getChildVisits() {
        return childVisits;
    }

    public void setChildVisits(List<GeoPosition> childVisits) {
        this.childVisits = childVisits;
    }

    public List<MyWaypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<MyWaypoint> waypoints) {
        this.waypoints = waypoints;
    }
    
    
    @Override
    public String toHtml(){
        String cadena;
        cadena = "<html><body><br><i>PLACE VISIT </i>" 
                + super.toHtml()
                + "<br><b>" + address + "</b><br></body></html>";
        return cadena;
    }
    
    @Override
    public String toString() {
        return "PlaceVisit{" 
                + super.toString()
                + "\naddress\t=\t" + address 
                + "\nvisitImportance\t=\t" + visitImportance
                + "\n}\n";
    }

    public void initWaypoint(EventWaypoint eventWaypoint){
        
        this.setMainWaypoint(new MyWaypoint(this.getLocation(), this.toString(), this,
                MyWaypoint.PointType.PLACEVISIT, eventWaypoint));        
        waypoints.add(getMainWaypoint());
        for (GeoPosition geop : this.childVisits) {
            MyWaypoint myw = new MyWaypoint(geop, this.toString(), this, 
                    MyWaypoint.PointType.WAYPOINTPATH, eventWaypoint);
            waypoints.add(myw);
        }                        
    }
    
 @Override
    public List<MyWaypoint> giveWaypoints() {
        return this.waypoints;
        
    }
        
    @Override
    public List<GeoPosition> giveGeoPositions(){
        return this.childVisits;
    }
    
    public void copiar(PlaceVisit original){
        this.address = original.address;
        this.name = original.name;
        this.visitImportance = original.visitImportance;
        this.childVisits = original.childVisits;
        this.setStartTimestamp(original.getStartTimestamp());
        this.setEndTimestamp(original.getEndTimestamp());
        this.setLocation(original.getLocation());
        this.setDurationReal(original.getDurationReal());
    }
    
}
