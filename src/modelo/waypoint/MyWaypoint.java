
package modelo.waypoint;

import modelo.entidad.MyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class MyWaypoint extends DefaultWaypoint {
    
    private String title;
    private JButton button;
    private PointType pointType;
    private MyEvent myEvent;
    
    public MyWaypoint() {
    }

    public MyWaypoint(GeoPosition coord, String title, MyEvent myEvent, 
            PointType pointType, EventWaypoint eventWaypoint) {
        super(coord);
        this.title = title;
        this.pointType = pointType;
        this.myEvent = myEvent;
        String color;
        switch (pointType) {
            case PLACEVISIT:
                color = "/icon/pin.png";
//                color = "/icon/cir.png";
                break;
            case CHILDVISIT:
                color = "/icon/flag_rojo.png";
                break;
            case STARTLOCATION:
                color = "/icon/flag_verde.png";
//                color = "/icon/cir.png";
                break;
            case ENDLOCATION:
                color = "/icon/flag_rojo.png";
//                color = "/icon/cua.png";
                break;
            case WAYPOINTPATH:
                color = "/icon/azul_der.png";
//                color = "/icon/cua.png";
                break;
            case OTHER:
                color = "/icon/negro.png";
                break;
            case MAIN:
                color = "/icon/flag_rojo.png";
                break;
            default:
                throw new AssertionError();
        }
        initButton(eventWaypoint, color);
    }
    
    /*
    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }
    */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JButton getButton() {
        return button;
    }

    
    public JButton getButton2() {
        return button;
    }
    
    
    public void setButton(JButton button) {
        this.button = button;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public MyEvent getMyEvent() {
        return myEvent;
    }

    public void setMyEvent(MyEvent myEvent) {
        this.myEvent = myEvent;
    }

    
    
    private void initButton(EventWaypoint event, String color) {
        button = new ButtonWaypoint(color);//###
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(MyWaypoint.this);
            }
        });
    }
    
    public static enum PointType {
        PLACEVISIT, CHILDVISIT, STARTLOCATION, ENDLOCATION, WAYPOINTPATH, OTHER, MAIN 
    }
}
