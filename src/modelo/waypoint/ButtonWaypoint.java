
package modelo.waypoint;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonWaypoint extends JButton {

    public ButtonWaypoint(String color) {
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource(color)));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24, 24));
        //setSize(new Dimension(64, 64));
    }
    
    public JButton dame2(){
        return this;
    }
}
