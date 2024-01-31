/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.entidad.ActivitySegment;
import modelo.entidad.LectorArchivo;
import modelo.entidad.MyEvent;
import modelo.entidad.PlaceVisit;
import modelo.waypoint.EventWaypoint;
import modelo.waypoint.MyWaypoint;
import repositorio.Repositorio;
import vista.MainFrame;
import vista.MainPanel;
import vista.PanelDetallesEvento;

public class ControlPanel {

    
    
    private String title;
    private MainPanel mainPanel;
    private LectorArchivo fuente;
    private Repositorio repositorio;
    private EventWaypoint event;
    
    
    public ControlPanel() {
        event = getEvent();
    }
    
    public ControlPanel(String title, String path){
        this.title = title;
        this.event = getEvent();
        repositorio = new Repositorio(this, event);
        fuente = new LectorArchivo(path, repositorio);
        mainPanel = new MainPanel(this);
        MainFrame.getInstance().nuevoTab(title, mainPanel);
    }
    
    public EventWaypoint getEvent() {
        return new EventWaypoint() {
            @Override
            public void selected(MyWaypoint waypoint) {

                if (waypoint.getPointType() == MyWaypoint.PointType.STARTLOCATION
                        || waypoint.getPointType() == MyWaypoint.PointType.WAYPOINTPATH
                        || waypoint.getPointType() == MyWaypoint.PointType.ENDLOCATION) {
                    
                    initRouting(waypoint);
                }
                JOptionPane.showMessageDialog(mainPanel, waypoint.getTitle());
            }
        };
    }
    
    
    public EventWaypoint getEvent2() {
        return new EventWaypoint() {
            @Override
            public void selected(MyWaypoint waypoint) {
                    
                mainPanel.getjXMapViewer().setAddressLocation(waypoint.getPosition());
                if (waypoint.getPointType() == MyWaypoint.PointType.STARTLOCATION
                        || waypoint.getPointType() == MyWaypoint.PointType.WAYPOINTPATH
                        || waypoint.getPointType() == MyWaypoint.PointType.ENDLOCATION) {
                    
                    initRouting(waypoint);
                }
                JOptionPane.showMessageDialog(mainPanel, waypoint.getTitle());
            }
        };
    }
    
        
    private void initRouting(MyWaypoint waypoint){
        
//        List<GHPoint> points = new ArrayList<>();
//        for (MyWaypoint myw : waypoint.getMyEvent().giveWaypoints()) {
//            GHPoint ghpGeop = new GHPoint(myw.getPosition().getLatitude(), myw.getPosition().getLongitude());
//            points.add(ghpGeop);
//        }
//        mainPanel.getjXMapViewer().setRoutingData(RoutingService.getInstance().routing(points));
        Set<MyWaypoint> waypoints = new HashSet<>();
        waypoints.addAll(waypoint.getMyEvent().giveWaypoints());
        mainPanel.getjXMapViewer().setWaypointsExtra(waypoints);
        mainPanel.getjXMapViewer().setPath(waypoint.getMyEvent().giveGeoPositions());
        
    }
    
    public Set<MyWaypoint> getWaypoints(){
        return repositorio.getWaypoints();
    }
    
    public List<LocalDate> getFechas(){
        return repositorio.getFechas();
    }
    
    public List<ActivitySegment> getActivitySegments(){
        return repositorio.getActivitySegments();
    }
    
    public List<PlaceVisit> getPlaceVisits(){
        return repositorio.getPlaceVisits();
    }
    
    public void initPanelEventos(LocalDate fecha){
        
        mainPanel.getPanelEventos().removeAll();
        List<MyEvent> eventos = repositorio.devolverPorFecha(fecha);
        Set<MyWaypoint> waypoints = new HashSet<>();
        for(MyEvent evento : eventos){
            waypoints.add(evento.getMainWaypoint());
            mainPanel.getPanelEventos().add(new PanelDetallesEvento(evento, getEvent2()));
        }
        mainPanel.getjXMapViewer().setWaypoints(waypoints);
        mainPanel.getPanelEventos().updateUI();
    }
    
    public void resetValores(){
//        if (mainPanel != null ) {
            mainPanel.getjXMapViewer().setWaypoints(repositorio.getWaypoints());    
//        }
    }
    
    
    public void stateChanged(){
//        if(mainPanel.getTabFiltro().isFiltroActivado()){
//            
//        }
//        else{
//            if(mainPanel.getTabFiltro().getCheckRutas() == true 
//                    && mainPanel.getTabFiltro().getCheckUbicaciones() == true){
//                
//            }
//            else{
//            }
//        }
            
    }
    
    public void aplicarFiltros(){
        System.out.println("____\n\n\n___");
        Set<MyWaypoint> filtro = new HashSet<>();
        LocalDate fechaStart;
        LocalDate fechaEnd;
        if(mainPanel.getTabFiltro().getFechaStart().compareTo(mainPanel.getTabFiltro().getFechaEnd())<1){
            fechaStart = mainPanel.getTabFiltro().getFechaStart();
            fechaEnd = mainPanel.getTabFiltro().getFechaEnd();
        }
        else{
            fechaStart = mainPanel.getTabFiltro().getFechaEnd();
            fechaEnd = mainPanel.getTabFiltro().getFechaStart();
        }
        String horaStart = mainPanel.getTabFiltro().getHoraBoxStart();
        String horaEnd =mainPanel.getTabFiltro().getHoraBoxEnd();
        System.out.println(horaStart);
        System.out.println(horaEnd);
        for(PlaceVisit p : repositorio.getPlaceVisits()){
            String pEnd = p.getEndTimestamp().toString().substring(11);
            String pStart = p.getStartTimestamp().toString().substring(11);
            if(p.getEndTimestamp().toLocalDate().compareTo(fechaStart) 
                    *p.getStartTimestamp().toLocalDate().compareTo(fechaEnd)
                    <1){
                if(pEnd.compareTo(horaStart)*pStart.compareTo(horaEnd)<1){
                    filtro.add(p.getMainWaypoint());
                }
            }
        }
        for(ActivitySegment a : repositorio.getActivitySegments()){
            if(a.getEndTimestamp().toLocalDate().compareTo(fechaStart) 
                    *a.getStartTimestamp().toLocalDate().compareTo(fechaEnd)
                    <1){
                String aEnd = a.getEndTimestamp().toString().substring(11);
                String aStart = a.getStartTimestamp().toString().substring(11);
                if(aEnd.compareTo(horaStart)*aStart.compareTo(horaEnd)<1){
                    filtro.add(a.getMainWaypoint());
                }
            }
        }
        mainPanel.getjXMapViewer().setWaypoints(filtro);
        
    }
    
    
}
