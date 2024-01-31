

package vista;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.entidad.PlaceVisit;
import org.jxmapviewer.viewer.GeoPosition;


public class TabLugares extends javax.swing.JPanel {

    private JTable tabla;
    private MainPanel mainPanel;
    
    public TabLugares(MainPanel mainPanel, List<PlaceVisit> placeVisits) {
        this.mainPanel = mainPanel;
        initComponents();
        initTable(placeVisits);
    }
    
    
    public void initTable(List<PlaceVisit> placeVisits){
        tabla = new JTable();
        
        Map<String, List<PlaceVisit>> direccionesMapa = new HashMap<>();
        List<String> direccionesLista = new ArrayList<>();
        for(PlaceVisit p : placeVisits){
            String dire = p.getAddress();
            if(direccionesMapa.containsKey(dire)){
                direccionesMapa.get(dire).add(p);
            }
            else{
                direccionesMapa.put(dire, new ArrayList<>());
                direccionesMapa.get(dire).add(p);
                direccionesLista.add(dire);
            }
        }
        Collections.sort(direccionesLista);
        
        int tam = placeVisits.size();
        Object[][] data = new Object[tam][6];
        String[] columnas = {"Direccion", "Desde", "Hasta", "Latitud", "Longitud", "Comentarios"};
        
        
        tabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            data, columnas) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.getColumnModel().getColumn(0).setMinWidth(240);
        tabla.getColumnModel().getColumn(0).setMaxWidth(480);
        tabla.getColumnModel().getColumn(1).setMinWidth(80);
        tabla.getColumnModel().getColumn(1).setMaxWidth(180);
        tabla.getColumnModel().getColumn(2).setMinWidth(80);
        tabla.getColumnModel().getColumn(2).setMaxWidth(180);
        tabla.getColumnModel().getColumn(3).setMaxWidth(90);
        tabla.getColumnModel().getColumn(4).setMaxWidth(90);
        tabla.getColumnModel().getColumn(5).setMinWidth(240);
        tabla.getColumnModel().getColumn(5).setMaxWidth(480);
        jScrollPane1.setViewportView(tabla);

        ColorCeldaTabla color = new ColorCeldaTabla();
        int i=0;
        int semitam = 0;
        for(String d: direccionesLista){
            semitam = semitam + direccionesMapa.get(d).size();
            color.setColor(direccionesMapa.get(d).size());
            for(PlaceVisit p : direccionesMapa.get(d)){
                tabla.setValueAt(p.getAddress(), i, 0);
                tabla.setValueAt(p.getStartTimestamp().toString(), i, 1);
                tabla.setValueAt(p.getEndTimestamp().toString(), i, 2);
                tabla.setValueAt(p.getLocation().getLatitude(), i, 3);
                tabla.setValueAt(p.getLocation().getLongitude(), i, 4);
                i++;
            }
        }
        tabla.getColumnModel().getColumn(0).setCellRenderer(color);
        
        updateUI();
    }

    private void initBoton(){
        int r;
        r = tabla.getSelectedRow();
        if(r>0){
            GeoPosition position = new GeoPosition(
                    (Double)tabla.getValueAt(r, 3), 
                    (Double)tabla.getValueAt(r, 4));
            mainPanel.buscarGeoPosition(position);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("IR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 160, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        initBoton();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
