

package vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import javax.swing.JTable;
import modelo.entidad.ActivitySegment;
import modelo.entidad.MyEvent;


public class TabRutas extends javax.swing.JPanel {

    
    private JTable tabla;
    private MainPanel mainPanel;
    private int metrosMax;
    
    
    public TabRutas(MainPanel mainPanel, SortedMap<LocalDate, List<MyEvent>> eventos, List<ActivitySegment> lista ) {
        this.mainPanel = mainPanel;
        metrosMax = 0;
        initComponents();
        initTable(eventos);
        panelGraph1.initGraficoRutas(lista);
    }

    
    public void initTable(SortedMap<LocalDate, List<MyEvent>> eventos){
        tabla = new JTable();
        
        int tam = eventos.keySet().size();
        Object[][] data = new Object[tam][4];
        String[] columnas = {"Fecha", "Actividad Total", "Metros Total", "Comentarios"};
        
        
        tabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            data, columnas) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        Map<String, List<ActivitySegment>> actividades = new HashMap<>();
        List<String> direccionesLista = new ArrayList<>();
        int metrosMax = 0;
        int i = 0;
        for(LocalDate l: eventos.keySet()){
            int cantidadActividad=0;
            int metrosTotales =0;
            for(MyEvent m: eventos.get(l)){
                if(m instanceof ActivitySegment){
                    metrosTotales = metrosTotales + ((ActivitySegment) m).getDistance();
                    cantidadActividad++;
                }
            }
            tabla.setValueAt(l, i, 0);
            tabla.setValueAt(cantidadActividad, i, 1);
            tabla.setValueAt(metrosTotales, i, 2);
            i++;
        }
        
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.getColumnModel().getColumn(0).setMinWidth(100);
        tabla.getColumnModel().getColumn(0).setMaxWidth(120);
        tabla.getColumnModel().getColumn(1).setMinWidth(100);
        tabla.getColumnModel().getColumn(1).setMaxWidth(120);
        tabla.getColumnModel().getColumn(2).setMinWidth(100);
        tabla.getColumnModel().getColumn(2).setMaxWidth(120);
        tabla.getColumnModel().getColumn(3).setMinWidth(240);
        tabla.getColumnModel().getColumn(3).setMaxWidth(480);
        jScrollPane1.setViewportView(tabla);

//        ColorCeldaTabla color = new ColorCeldaTabla();
//        tabla.getColumnModel().getColumn(0).setCellRenderer(color);
        
        updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        panelGraph1 = new vista.PanelGraph();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setPreferredSize(new java.awt.Dimension(780, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("TABLA", new javax.swing.ImageIcon(getClass().getResource("/icon/tabla.png")), jPanel1); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
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
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private vista.PanelGraph panelGraph1;
    // End of variables declaration//GEN-END:variables
}
