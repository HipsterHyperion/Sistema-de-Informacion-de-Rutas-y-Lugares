
package repositorio;

import control.ControlPanel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import modelo.entidad.ActivitySegment;
import modelo.entidad.MyEvent;
import modelo.entidad.PlaceVisit;
import modelo.waypoint.MyWaypoint;

public class Fuente {
    
    
    private ControlPanel control;
    
    private Set<MyWaypoint> waypoints;
    private Set<MyWaypoint> waypointsActivity;
    private Set<MyWaypoint> waypointsPlaceVisit;
    private List<ActivitySegment> activitySegments;
    private List<PlaceVisit> placeVisits;
    private SortedMap<LocalDate, List<MyEvent>> lineaDeEventos;
    

    public Fuente(ControlPanel control) {
        
        this.control = control;
        waypoints = new HashSet<>();
        waypointsActivity = new HashSet<>();
        waypointsPlaceVisit = new HashSet<>();
        activitySegments = new ArrayList<>();
        placeVisits = new ArrayList<>();
        lineaDeEventos = new TreeMap<>();
    }


    public List<ActivitySegment> getActivitySegments() {
        return activitySegments;
    }

    public List<PlaceVisit> getPlaceVisits() {
        return placeVisits;
    }

    public SortedMap<LocalDate, List<MyEvent>> getLineaDeEventos() {
        return lineaDeEventos;
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
    
    public List<MyEvent> devolverPorFecha(LocalDate fecha){
        System.out.println(lineaDeEventos.keySet());
        return lineaDeEventos.get(fecha);
    }
    
        
    public void nuevoPlaceVisit(PlaceVisit placevisit){
        
        placevisit.initWaypoint(control.getEvent());
        waypoints.add(placevisit.getMainWaypoint());
        waypointsPlaceVisit.add(placevisit.getMainWaypoint());
        
        LocalDateTime fechaHoraStart;
        LocalDateTime fechaHoraEnd;
        fechaHoraStart = placevisit.getStartTimestamp();
        fechaHoraEnd = placevisit.getEndTimestamp();
        if(fechaHoraStart.getDayOfMonth()== fechaHoraEnd.getDayOfMonth()){
            agregarPlaceVisit(placevisit);
        }
        else{
            LocalDateTime aux = fechaHoraStart.toLocalDate().plusDays(1).atStartOfDay();
            PlaceVisit nuevo =  new PlaceVisit();
            nuevo.copiar(placevisit);
            placevisit.setEndTimestamp(aux);
            nuevo.setStartTimestamp(aux);
            agregarPlaceVisit(placevisit);
            nuevoPlaceVisit(nuevo);
        }
    }
    
    private void agregarPlaceVisit(PlaceVisit placevisit){
        placeVisits.add( placevisit);//agregar a la lista
        LocalDate fecha = placevisit.getStartTimestamp().toLocalDate();
        if(lineaDeEventos.get(fecha)!=null){
            lineaDeEventos.get(fecha).add(placevisit);
        }
        else{
            List <MyEvent> lista = new ArrayList<>();
            lista.add(placevisit);
            lineaDeEventos.put(fecha, lista);
        }
    }
    
    
    public void nuevoActivitySegment(ActivitySegment activitysegment){
        
        activitysegment.initWaypoint(control.getEvent());
        waypoints.add(activitysegment.getStartWaypoint());
        waypointsActivity.add(activitysegment.getStartWaypoint());
        activitySegments.add(activitysegment);
        LocalDate fecha = activitysegment.getStartTimestamp().toLocalDate();
        if(lineaDeEventos.get(fecha)!=null){
            lineaDeEventos.get(fecha).add(activitysegment);
        }
        else{
            List <MyEvent> lista = new ArrayList<>();
            lista.add(activitysegment);
            lineaDeEventos.put(fecha, lista);
        }
        Collections.sort(activitySegments);
    }
    
    public void ordenarListas(){
        Collections.sort(placeVisits);
        Collections.sort(activitySegments);
    }
}
