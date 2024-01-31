/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.entidad;

import java.util.List;
import modelo.waypoint.MyWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 *
 * @author Martín
 */
public interface giveSomething {
    
    public List<MyWaypoint> giveWaypoints();
    
    
    public List<GeoPosition> giveGeoPositions();
}
