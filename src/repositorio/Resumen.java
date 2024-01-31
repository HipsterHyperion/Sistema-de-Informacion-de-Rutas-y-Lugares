

package repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.entidad.MyEvent;
import modelo.entidad.PlaceVisit;



public class Resumen {
    
    private int totalEventos;
    private int totalPlaceVisit;
    private int totalActivitySegment;
    private int totalMetrosRecorridos;
    private int mayorActividad;
    private int mayorMetrosRecorridos;
    private Map<String, Set<PlaceVisit>> direccionesMapa;
    private List<String> direccionesLista;
    private Repositorio repo;
    
    public Resumen(Repositorio repo){
        this.repo = repo;
        initDirecciones();
    }
    
    private void initDirecciones(){
        List<PlaceVisit> placeVisits = repo.getPlaceVisits();
        direccionesMapa = new HashMap<>();
        direccionesLista = new ArrayList<>();
        for(PlaceVisit p : placeVisits){
            String dire = p.getAddress();
            if(direccionesMapa.containsKey(dire)){
                direccionesMapa.get(dire).add(p);
            }
            else{
                direccionesMapa.put(dire, new HashSet<>());
                direccionesMapa.get(dire).add(p);
                direccionesLista.add(dire);
            }
        }
        Collections.sort(direccionesLista);
    }

    public Map<String, Set<PlaceVisit>> getDireccionesMapa() {
        return direccionesMapa;
    }

    public List<String> getDireccionesLista() {
        return direccionesLista;
    }
    
    
    
    
}
