

package vista;

import java.util.List;
import modelo.entidad.ActivitySegment;
import modelo.entidad.PlaceVisit;


public class TabConfiguracion extends javax.swing.JPanel {

    private MainPanel mainPanel;
    
    public TabConfiguracion(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        initComponents();
    }
    
    public void initGraph(List<ActivitySegment> activitySegments, List<PlaceVisit> placeVisits){
        panelGraph1.init(activitySegments, placeVisits);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGraph1 = new vista.PanelGraph();

        setPreferredSize(new java.awt.Dimension(1012, 572));

        panelGraph1.setPreferredSize(new java.awt.Dimension(900, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGraph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGraph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.PanelGraph panelGraph1;
    // End of variables declaration//GEN-END:variables
}
