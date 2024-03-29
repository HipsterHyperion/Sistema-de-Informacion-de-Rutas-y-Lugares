

package vista;


import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import modelo.entidad.ActivitySegment;
import modelo.entidad.MyEvent;
import modelo.waypoint.EventWaypoint;
import modelo.waypoint.MyWaypoint;
import static modelo.waypoint.MyWaypoint.PointType.*;


public class PanelDetallesEvento extends javax.swing.JPanel {
    
    
    private MyWaypoint waypoint;
    
    
    public PanelDetallesEvento(MyEvent evento, EventWaypoint event){
        this.waypoint = evento.getMainWaypoint();
        initComponents();
        detalles.setText( evento.toHtml());
        this.updateUI();
        String color;
        switch (waypoint.getPointType()) {
            case PLACEVISIT:
                color = "/icon/pin.png";
                break;
            case STARTLOCATION:
                ActivitySegment act = (ActivitySegment)evento;
                if("A PIE".equals(act.getTravelmode())){
                    color = "/icon/caminar.png";
                }else{
                    color = "/icon/carro.png";
                }
                break;
            default:
                color = "/icon/pin.png";
        }
        initButton(event, color);
    }
    
        
    
    private void initButton(EventWaypoint event, String color) {
        button.setContentAreaFilled(false);
        button.setIcon(new ImageIcon(getClass().getResource(color)));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
//        setSize(new Dimension(24, 24));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(waypoint, true);
            }
        });
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detalles = new javax.swing.JLabel();
        button = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(240, 120));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detalles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        detalles.setText("Av. Ej�rcito de los Andes 950,");
        detalles.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        detalles.setPreferredSize(new java.awt.Dimension(158, 99));
        add(detalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, -1));

        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/caminar.png"))); // NOI18N
        add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 40, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel detalles;
    // End of variables declaration//GEN-END:variables
}
