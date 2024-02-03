

package modelo.entidad;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import modelo.waypoint.MyWaypoint;
import org.jxmapviewer.viewer.GeoPosition;



public class MyEvent implements Comparable<MyEvent>, giveSomething{
    
    
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private GeoPosition mainGeoPosition;
    private MyWaypoint mainWaypoint;
    private long durationReal;

    
    public MyEvent() {
    }

    
    public void initDurationReal(){
        this.durationReal = getDuration();
        }

    public long getDurationReal() {
        return durationReal;
    }

    public void setDurationReal(long durationReal) {
        this.durationReal = durationReal;
    }
    
    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public GeoPosition getMainGeoPosition() {
        return mainGeoPosition;
    }

    public void setMainGeoPosition(GeoPosition mainGeoPosition) {
        this.mainGeoPosition = mainGeoPosition;
    }

    public MyWaypoint getMainWaypoint() {
        return mainWaypoint;
    }

    public void setMainWaypoint(MyWaypoint mainWaypoint) {
        this.mainWaypoint = mainWaypoint;
    }
    
    public long getDuration(){
        return ChronoUnit.MINUTES.between(startTimestamp, endTimestamp);
    }

    
    
    public String toHtml(){
        String cadena;
        cadena = "<br>hora <b>" + startTimestamp.toLocalTime() + "</b>";
//                + " <b>A  </b>" + endTimestamp.toLocalTime();
//                + "<br> " + ChronoUnit.MINUTES.between(startTimestamp, endTimestamp) + " <b>MINUTOS</b>";

        return cadena;
    }
    
    @Override
    public String toString() {
        return "\nstartTimestamp\t=\t" + startTimestamp
                + "\nendTimestamp\t=\t" + endTimestamp
                + "\nduration\t=\t" + getDuration() + " minutos";
    }
    
    
    @Override
    public int compareTo(MyEvent o) {
        return this.startTimestamp.compareTo(o.getStartTimestamp());
    }

    @Override
    public List<MyWaypoint> giveWaypoints() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GeoPosition> giveGeoPositions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
