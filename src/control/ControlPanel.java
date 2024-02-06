

package control;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import javax.swing.JOptionPane;
import modelo.entidad.ActivitySegment;
import modelo.entidad.MyEvent;
import modelo.entidad.PlaceVisit;
import modelo.waypoint.EventWaypoint;
import modelo.waypoint.MyWaypoint;
import repositorio.LectorArchivo;
import repositorio.Fuente;
import vista.MainFrame;
import vista.MainPanel;
import vista.PanelDetallesEvento;

public class ControlPanel {

    
    private String title;
    private MainPanel mainPanel;
    private LectorArchivo lector;
    private Fuente repositorio;
    
    
    public ControlPanel() {
    }
    
    public ControlPanel(String title, String path){
        this.title = title;
        repositorio = new Fuente(this);
        lector = new LectorArchivo(path, repositorio);
        mainPanel = new MainPanel(this);
        MainFrame.getInstance().nuevoTab(title, mainPanel);
    }
    
    public Set<MyWaypoint> getWaypoints(){
        return repositorio.getWaypoints();
    }
    
    public List<ActivitySegment> getActivitySegments(){
        return repositorio.getActivitySegments();
    }
    
    public List<PlaceVisit> getPlaceVisits(){
        return repositorio.getPlaceVisits();
    }
    
    public SortedMap<LocalDate, List<MyEvent>> getLineaDeEventos() {
        return repositorio.getLineaDeEventos();
    }
    
    
    public EventWaypoint getEvent() {
        return new EventWaypoint() {
            @Override
            public void selected(MyWaypoint waypoint, boolean b) {
                if(b){
                    mainPanel.getjXMapViewer().setAddressLocation(waypoint.getPosition());
                }
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
        
        Set<MyWaypoint> waypoints = new HashSet<>();
        waypoints.addAll(waypoint.getMyEvent().giveWaypoints());
        mainPanel.getjXMapViewer().setWaypointsExtra(waypoints);
        mainPanel.getjXMapViewer().setPath(waypoint.getMyEvent().giveGeoPositions());
        
    }
    
    
    public void initPanelEventos(LocalDate fecha){
        
        mainPanel.getPanelEventos().removeAll();
        List<MyEvent> eventos = repositorio.devolverPorFecha(fecha);
        Set<MyWaypoint> waypoints = new HashSet<>();
        for(MyEvent evento : eventos){
            waypoints.add(evento.getMainWaypoint());
            mainPanel.getPanelEventos().add(new PanelDetallesEvento(evento, getEvent()));
        }
        mainPanel.getjXMapViewer().setWaypoints(waypoints);
        mainPanel.getPanelEventos().updateUI();
    }
    
    public void resetValores(){
//        if (mainPanel != null ) {
            mainPanel.getjXMapViewer().setWaypoints(repositorio.getWaypoints());    
//        }
    }
    
    
    public void filtroCheckers(){
    }
    
    public void aplicarFiltros(){
        Set<MyWaypoint> waypointFiltrado = new HashSet<>();
        LocalDate fechaStart;
        LocalDate fechaEnd;
        //Comparo las fechas de los filtro para saber cual es menor y cual es el mayor.
        if(mainPanel.getTabFiltro().getFechaStart().compareTo(mainPanel.getTabFiltro().getFechaEnd())<1){
            //Salida por true: fechaStart < fechaEnd.
            fechaStart = mainPanel.getTabFiltro().getFechaStart();
            fechaEnd = mainPanel.getTabFiltro().getFechaEnd();
        }
        else{
            //Salida por false: fechaEnd < fechaStart.
            fechaStart = mainPanel.getTabFiltro().getFechaEnd();
            fechaEnd = mainPanel.getTabFiltro().getFechaStart();
        }
        String horaStart = mainPanel.getTabFiltro().getHoraBoxStart();
        String horaEnd =mainPanel.getTabFiltro().getHoraBoxEnd();
        for(PlaceVisit p : repositorio.getPlaceVisits()){
            String pEnd = p.getEndTimestamp().toString().substring(11);
            String pStart = p.getStartTimestamp().toString().substring(11);
            if(p.getEndTimestamp().toLocalDate().compareTo(fechaStart) 
                    *p.getStartTimestamp().toLocalDate().compareTo(fechaEnd)
                    <1){
                if(pEnd.compareTo(horaStart)*pStart.compareTo(horaEnd)<1){
                    waypointFiltrado.add(p.getMainWaypoint());
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
                    waypointFiltrado.add(a.getMainWaypoint());
                }
            }
        }
        mainPanel.getjXMapViewer().setWaypoints(waypointFiltrado);
        
    }
    
    public void agregarDatosDefault(){
        
        String nombreArchivo = "/2022_MAY.json";
        // Obtener la ruta del directorio "src"
        String directorioSrc = System.getProperty("user.dir") + File.separator + "src";
        // Combinar la ruta del directorio "src" con el nombre del archivo
        String rutaAbsoluta = directorioSrc + File.separator + nombreArchivo;
        lector = new LectorArchivo(rutaAbsoluta, repositorio);
    }
    

}
