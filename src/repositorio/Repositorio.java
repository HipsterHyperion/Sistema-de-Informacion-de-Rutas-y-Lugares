
package repositorio;

import control.ControlPanel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import modelo.entidad.ActivitySegment;
import modelo.entidad.MyEvent;
import modelo.entidad.PlaceVisit;
import modelo.waypoint.EventWaypoint;
import modelo.waypoint.MyWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class Repositorio {
    
    
    private GeoPosition mainGeoPosition;
    private MyWaypoint mainWaypoint;
    
    private Set<MyWaypoint> waypoints;
    private Set<MyWaypoint> waypointsFiltro;
    private Set<MyWaypoint> waypointsActivity;
    private Set<MyWaypoint> waypointsPlaceVisit;
    private List<ActivitySegment> activitySegments;
    private List<PlaceVisit> placeVisits;
    private List<MyEvent> eventos;
    private List<LocalDate> fechas;
    private SortedMap<LocalDate, List<MyEvent>> lineaDeEventos;
    
    private ControlPanel control;
    private EventWaypoint event;

    
    public Repositorio(ControlPanel control, EventWaypoint event) {
        
        this.control = control;
        this.event = event;
        waypoints = new HashSet<>();
        waypointsFiltro = new HashSet<>();
        waypointsActivity = new HashSet<>();
        waypointsPlaceVisit = new HashSet<>();
        activitySegments = new ArrayList<>();
        placeVisits = new ArrayList<>();
        eventos = new ArrayList<>();
        fechas =  new ArrayList<>();
        lineaDeEventos = new TreeMap<>();
    }

    public List<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas(List<LocalDate> fechas) {
        this.fechas = fechas;
    }
    
    public List<ActivitySegment> getActivitySegments() {
        return activitySegments;
    }

    public void setActivitySegments(List<ActivitySegment> activitySegments) {
        this.activitySegments = activitySegments;
    }

    public List<PlaceVisit> getPlaceVisits() {
        return placeVisits;
    }

    public void setPlaceVisits(List<PlaceVisit> placeVisits) {
        this.placeVisits = placeVisits;
    }

    public SortedMap<LocalDate, List<MyEvent>> getLineaDeEventos() {
        return lineaDeEventos;
    }

    public void setLineaDeEventos(SortedMap<LocalDate, List<MyEvent>> lineaDeEventos) {
        this.lineaDeEventos = lineaDeEventos;
    }
    
    
    public void nuevoPlaceVisit(PlaceVisit placevisit){
        
        placevisit.initWaypoint(event);
        waypoints.add(placevisit.getMainWaypoint());
        waypointsPlaceVisit.add(placevisit.getMainWaypoint());
        placeVisits.add( placevisit);//agregar a la lista
        eventos.add(placevisit);
        LocalDate fecha = placevisit.getStartTimestamp().toLocalDate();
        if(lineaDeEventos.get(fecha)!=null){
            lineaDeEventos.get(fecha).add(placevisit);
        }
        else{
            List <MyEvent> lista = new ArrayList<>();
            lista.add(placevisit);
            fechas.add(fecha);
            lineaDeEventos.put(fecha, lista);
        }
    }
    
    
    public void nuevoActivitySegment(ActivitySegment activitysegment){
        
        activitysegment.initWaypoint(control.getEvent());
        waypoints.add(activitysegment.getStartWaypoint());
        waypointsActivity.add(activitysegment.getStartWaypoint());
        activitySegments.add(activitysegment);
        eventos.add(activitysegment);
        LocalDate fecha = activitysegment.getStartTimestamp().toLocalDate();
        if(lineaDeEventos.get(fecha)!=null){
            lineaDeEventos.get(fecha).add(activitysegment);
        }
        else{
            List <MyEvent> lista = new ArrayList<>();
            lista.add(activitysegment);
            fechas.add(fecha);
            lineaDeEventos.put(fecha, lista);
        }
    }
    
    
    public Set<MyWaypoint> getWaypoints(){
        return waypoints;
    }

    public Set<MyWaypoint> getWaypointsActivity() {
        return waypointsActivity;
    }

    public Set<MyWaypoint> getWaypointsPlaceVisit() {
        return waypointsPlaceVisit;
    }

    public Set<MyWaypoint> getWaypointsFiltro() {
        return waypointsFiltro;
    }
    
    public void setWaypointsFiltro(Set<MyWaypoint> filtro){
        waypointsFiltro = filtro;
    }
    
    
    public List<MyEvent> devolverPorFecha(LocalDate fecha){
        return lineaDeEventos.get(fecha);
    }
}
