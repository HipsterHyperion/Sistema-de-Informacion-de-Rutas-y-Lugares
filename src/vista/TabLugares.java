

package vista;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import javax.swing.JTable;
import modelo.entidad.MyEvent;
import modelo.entidad.PlaceVisit;
import org.jxmapviewer.viewer.GeoPosition;


public class TabLugares extends javax.swing.JPanel {

    private JTable tabla;
    private MainPanel mainPanel;
    
    public TabLugares(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        initComponents();
    }
    
    
    public void init(SortedMap<LocalDate, List<MyEvent>> lineaDeEventos, List<PlaceVisit> placeVisits){
        initTable(placeVisits);
        panelGraph1.initGraficoLugares(lineaDeEventos);
    }
    
    public void initTable(List<PlaceVisit> placeVisits){
        tabla = new JTable();
        System.out.println(placeVisits.size());
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
        Object[][] data = new Object[tam][7];
        String[] columnas = {"Direccion", "Fecha", "Hora", "Duracion", "Latitud", "Longitud", "Comentarios"};

        
        tabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            data, columnas) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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
        tabla.getColumnModel().getColumn(1).setMaxWidth(100);
        tabla.getColumnModel().getColumn(2).setMinWidth(80);
        tabla.getColumnModel().getColumn(2).setMaxWidth(100);
        tabla.getColumnModel().getColumn(3).setMinWidth(80);
        tabla.getColumnModel().getColumn(3).setMaxWidth(100);
        tabla.getColumnModel().getColumn(4).setMaxWidth(80);
        tabla.getColumnModel().getColumn(5).setMaxWidth(80);
        tabla.getColumnModel().getColumn(6).setMinWidth(240);
        tabla.getColumnModel().getColumn(6).setMaxWidth(480);
        jScrollPane1.setViewportView(tabla);

        ColorCeldaTabla color = new ColorCeldaTabla();
        color.initCelda();
        int i=0;
        int semitam = 0;
        for(String d: direccionesLista){
            color.setColor(direccionesMapa.get(d).size());
//            semitam = semitam + direccionesMapa.get(d).size();
            for(PlaceVisit p : direccionesMapa.get(d)){
                color.setColor2(p.getDuration());
                tabla.setValueAt(p.getAddress(), i, 0);
                tabla.setValueAt(p.getStartTimestamp().toLocalDate().toString(), i, 1);
                tabla.setValueAt(p.getStartTimestamp().toLocalTime().toString(), i, 2);
                tabla.setValueAt(p.getDuration() + " minutos", i, 3);
                tabla.setValueAt(p.getLocation().getLatitude(), i, 4);
                tabla.setValueAt(p.getLocation().getLongitude(), i, 5);
                i++;
            }
        }
        tabla.getColumnModel().getColumn(0).setCellRenderer(color);
        tabla.getColumnModel().getColumn(3).setCellRenderer(color);
        
        updateUI();
    }

    private void buscarDireccion(){
        System.out.println("asdasd");
        int r;
        r = tabla.getSelectedRow();
        if(r>=0){
            GeoPosition position = new GeoPosition(
                    (Double)tabla.getValueAt(r, 4), 
                    (Double)tabla.getValueAt(r, 5));
            mainPanel.buscarGeoPosition(position);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        panelGraph1 = new vista.PanelGraph();

        setPreferredSize(new java.awt.Dimension(1080, 640));

        jButton1.setText("IR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("TABLA", new javax.swing.ImageIcon(getClass().getResource("/icon/tabla.png")), jPanel1); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraph1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraph1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("GRAFICO", new javax.swing.ImageIcon(getClass().getResource("/icon/grafico.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        buscarDireccion();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private vista.PanelGraph panelGraph1;
    // End of variables declaration//GEN-END:variables
}
