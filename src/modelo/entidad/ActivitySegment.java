
package modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import modelo.waypoint.EventWaypoint;
import modelo.waypoint.MyWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
/**
 *
 * @author Marti
 */
public class ActivitySegment extends MyEvent{
    
    private GeoPosition endLocation;
    private int distance;
    private int distanceMeters;
    private String travelmode;
    private List<GeoPosition> path; 
    
    private MyWaypoint endWaypoint;
    private List<MyWaypoint> waypointPath;
    
    public ActivitySegment() {
        path = new ArrayList<>();
    }

    public GeoPosition getStartLocation() {
        return getMainGeoPosition();
    }
    
    public void setStartLocation(GeoPosition startLocation) {
        this.setMainGeoPosition(startLocation);
    }

    public void setStartLocation(double lat, double lon) {
        this.setMainGeoPosition(new GeoPosition(lat,lon));
    }
    
    public GeoPosition getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(GeoPosition endLocation) {
        this.endLocation = endLocation;
    }
    
    public void setEndLocation(double lat, double lon) {
        this.endLocation = new GeoPosition(lat,lon);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<GeoPosition> getPath() {
        return path;
    }

    public void setPath(List<GeoPosition> path) {
        this.path = path;
    }
    
    public int getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(int distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public String getTravelmode() {
        return travelmode;
    }

    public void setTravelmode(String travelmode) {
        if(travelmode.contains("WALK")){
            this.travelmode = "A PIE";
        } 
        else{
            this.travelmode = "EN VEHICULO";
        }
    }

    public MyWaypoint getStartWaypoint() {
        return this.getMainWaypoint();
    }

    public void setStartWaypoint(MyWaypoint startWaypoint) {
        this.setMainWaypoint(startWaypoint);
    }

    public MyWaypoint getEndWaypoint() {
        return endWaypoint;
    }

    public void setEndWaypoint(MyWaypoint endWaypoint) {
        this.endWaypoint = endWaypoint;
    }

    public List<MyWaypoint> getWaypointPath() {
        return waypointPath;
    }

    public void setWaypointPath(List<MyWaypoint> waypointPath) {
        this.waypointPath = waypointPath;
    }
    
    

    @Override
    public String toHtml(){
        String cadena;
        cadena = "<html><body><br><i>ACTIVITY SEGMENT</i>" 
                + super.toHtml()
                + "<br> <b>" + distanceMeters + "</b> metros  <b>"+ travelmode +"</b><br></body></html>";
        return cadena;
    }
    
    
    @Override
    public String toString() {
        return "ActivitySegment{" 
                + super.toString()
                + "\ndistance\t=\t" + distance 
                + "\ndistanceMeters\t=\t" + distanceMeters + " metros" 
                + "\ntravelmode\t=\t" + travelmode
                + "\n}\n";
    }

    public void initWaypoint(EventWaypoint eventWaypoint){
        
            waypointPath = new ArrayList<>();
            this.setMainWaypoint( new MyWaypoint(this.getStartLocation(), this.toString(), this, 
                    MyWaypoint.PointType.STARTLOCATION, eventWaypoint));
            endWaypoint = new MyWaypoint(this.getEndLocation(), this.toString(), this, 
                    MyWaypoint.PointType.ENDLOCATION, eventWaypoint);
//            waypointPath.add(getMainWaypoint());
            for (GeoPosition geop : this.path) {
                MyWaypoint myw = new MyWaypoint(geop, this.toString(), this, 
                        MyWaypoint.PointType.WAYPOINTPATH, eventWaypoint);
                waypointPath.add(myw);
            }
            waypointPath.add(endWaypoint);
    }

    @Override
    public List<MyWaypoint> giveWaypoints() {
        return this.waypointPath;
        
    }
    
    @Override
    public List<GeoPosition> giveGeoPositions(){
        return this.path;
    }
    
}
