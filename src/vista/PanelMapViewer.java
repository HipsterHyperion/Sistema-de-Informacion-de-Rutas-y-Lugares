

package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.MouseInputListener;
import modelo.waypoint.MyWaypoint;
import modelo.waypoint.WaypointRender;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

public class PanelMapViewer extends JXMapViewer {

    
    private boolean first = true;
    private Set<MyWaypoint> waypoints;
    private Set<MyWaypoint> waypointsExtra;
    private List<GeoPosition> path;
    

    public Set<MyWaypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Set<MyWaypoint> waypoints) {
        clearWaypoint();
        this.waypoints = waypoints;
        initWaypoint();
    }
    
    public List<GeoPosition> getPath() {
        return path;
    }

    public void setPath(List<GeoPosition> path) {
        this.path = path;
        repaint();
    }
    
    
        
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (path != null && !path.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D p2 = new Path2D.Double();
            first = true;
            drawPath(p2);
            
            g2.setColor(new Color(50, 50, 255));
            g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(p2);
            g2.dispose();
        }
        
    }

    private void drawPath(Path2D p2){
        for(GeoPosition geop: path){
            Point2D point = convertGeoPositionToPoint(geop);
            if (first) {
                first = false;
                p2.moveTo(point.getX(), point.getY());
            } else {
                p2.lineTo(point.getX(), point.getY());
            }
        }
    }
    
    
    public void init(){
        
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        this.setTileFactory(tileFactory);
        this.setAddressLocation(new GeoPosition(-33.2921, -66.3401));
        this.setZoom(3);

        //  Create event mouse move
        MouseInputListener mm = new PanMouseInputListener(this);
        this.addMouseListener(mm);
        this.addMouseMotionListener(mm);
        this.addMouseWheelListener(new ZoomMouseWheelListenerCenter(this));
        
    }
    
    
    public void initWaypoint(){
        if (waypoints != null && !waypoints.isEmpty()) {
            WaypointPainter<MyWaypoint> wp = new WaypointRender();
            wp.setWaypoints(waypoints);
            this.setOverlayPainter(wp);

            for (MyWaypoint mywaypoint : waypoints) {
                this.add(mywaypoint.getButton());
            }
        }
    }
        
        
    public void clearWaypoint(){
        clearWaypointExtra();
        path=null;
        if (waypoints != null && !waypoints.isEmpty()) {
            for (MyWaypoint d : waypoints) {
                this.remove(d.getButton());
            }
        }
    }
    
    
    public void setWaypointsExtra(Set<MyWaypoint> waypointsExtra){
        clearWaypointExtra();
        this.waypointsExtra = waypointsExtra;
        if (waypointsExtra != null && !waypointsExtra.isEmpty()) {
            Set<MyWaypoint> wps = new HashSet<>();
            wps.addAll(waypoints);
            wps.addAll(waypointsExtra);
            WaypointPainter<MyWaypoint> wp = new WaypointRender();
            wp.setWaypoints(wps);
            this.setOverlayPainter(wp);

            for (MyWaypoint mywaypoint : waypointsExtra) {
                this.add(mywaypoint.getButton());
            }
        }
        
    }
    
    public void clearWaypointExtra(){
        if (waypointsExtra != null && !waypointsExtra.isEmpty()) {
            for (MyWaypoint d : waypointsExtra) {
                this.remove(d.getButton());
            }
        }
    }
    
    public void cambiarTileFactory(int tipo){
        TileFactoryInfo info;
//        int zoom = getZoom();
        switch (tipo) {
            case 0:
            info = new OSMTileFactoryInfo();
            break;
            case 1:
                setZoom(4);
                info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
                break;
            case 2:
                setZoom(4);
                info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
                break;
            default:
                setZoom(4);
                info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
                break;
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);
    }
    
    public void setTypeWaypoint(boolean activitySegment, boolean placeVisit){
        clearWaypoint();
        if(!activitySegment || !placeVisit){
            Set<MyWaypoint> s = new HashSet<>();
            for(MyWaypoint w: waypoints){
                if(placeVisit && w.getPointType().equals(MyWaypoint.PointType.PLACEVISIT)){
                    s.add(w);
                }
                if(activitySegment && w.getPointType().equals(MyWaypoint.PointType.STARTLOCATION)){
                    s.add(w);
                }
            }
            WaypointPainter<MyWaypoint> wp = new WaypointRender();
            wp.setWaypoints(s);
            this.setOverlayPainter(wp);

            for (MyWaypoint mywaypoint : s) {
                this.add(mywaypoint.getButton());
            }
            
        }
        else{
            initWaypoint();
        }
    }
    
    
}
