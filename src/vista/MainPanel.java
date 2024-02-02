

package vista;

import control.ControlPanel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JPanel;
import org.jxmapviewer.viewer.GeoPosition;

public class MainPanel extends javax.swing.JPanel {

    
    private ControlPanel control;
    private TabFechas tabFechas;
    private TabFiltros tabFiltro;
    private TabConfiguracion tabConf;
    private TabLugares tabLug;
    private TabRutas tabRutas;

    
    public MainPanel(ControlPanel control) {
        this.control = control;
        initComponents();
        initTabs();
        mapViewer.init();
        mapViewer.setWaypoints(control.getWaypoints());
    }
    
    
    private void initTabs(){
        tabFechas = new TabFechas(this, control.getFechas());
        tabFiltro = new TabFiltros(this, control.getFechas().getFirst(), control.getFechas().getLast());
        tabRutas = new TabRutas(this, control.getLineaDeEventos(), control.getActivitySegments());
        tabLug = new TabLugares(this, control.getPlaceVisits());
        tabConf = new TabConfiguracion(this);
        tabLeft.addTab("FECHAS", new javax.swing.ImageIcon(getClass().getResource("/icon/fechas.png")), tabFechas);
        tabLeft.addTab("FILTRO", new javax.swing.ImageIcon(getClass().getResource("/icon/filtros.png")), tabFiltro);
        tabMain.addTab("RUTAS", new javax.swing.ImageIcon(getClass().getResource("/icon/rutas.png")), tabRutas);
        tabMain.addTab("LUGARES", new javax.swing.ImageIcon(getClass().getResource("/icon/lugares.png")), tabLug);
        tabMain.addTab("CONFIGURACION", new javax.swing.ImageIcon(getClass().getResource("/icon/conf.png")), tabConf);
    }
    
    
    public PanelMapViewer getjXMapViewer() {
        return mapViewer;
    }

    public JPanel getPanelEventos() {
        return tabFechas.getPanelEventos();
    }

    public TabFiltros getTabFiltro() {
        return tabFiltro;
    }
    
    
    public void initPanelEventos() {
        
        // Define el formato del String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convierte el String en LocalDate
        LocalDate fecha = LocalDate.parse(tabFechas.getFechasBox().getSelectedItem().toString(), formatter);
        control.initPanelEventos(fecha);        
    }
    
    public void stateChanged(){
        control.stateChanged();
    }
    
    public void aplicarFiltros(){
        control.aplicarFiltros();
    }

    public void resetValores(){
//        if(tabFechas!=null){
            tabFechas.resetValores();
            tabFiltro.resetValores();
            control.resetValores();
//        }
    }
    

    private void cambiarTipoDeMapa(){
        mapViewer.cambiarTileFactory(comboMapType.getSelectedIndex());
    }
    
    public void buscarGeoPosition(GeoPosition position){
        tabMain.setSelectedIndex(0);
        mapViewer.setAddressLocation(position);
    }
    
    
    public void agregarDatos(){
        control.agregarDatosDefault();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabMain = new javax.swing.JTabbedPane();
        mapViewer = new vista.PanelMapViewer();
        comboMapType = new javax.swing.JComboBox<>();
        tabLeft = new javax.swing.JTabbedPane();

        setPreferredSize(new java.awt.Dimension(1080, 640));

        tabMain.setToolTipText("");

        comboMapType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" }));
        comboMapType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMapTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout mapViewerLayout = new javax.swing.GroupLayout(mapViewer);
        mapViewer.setLayout(mapViewerLayout);
        mapViewerLayout.setHorizontalGroup(
            mapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapViewerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mapViewerLayout.setVerticalGroup(
            mapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapViewerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabMain.addTab("MAPA", new javax.swing.ImageIcon(getClass().getResource("/icon/mapa.png")), mapViewer); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(tabLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain)
            .addComponent(tabLeft)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void comboMapTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMapTypeItemStateChanged
        cambiarTipoDeMapa();
    }//GEN-LAST:event_comboMapTypeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboMapType;
    private vista.PanelMapViewer mapViewer;
    private javax.swing.JTabbedPane tabLeft;
    private javax.swing.JTabbedPane tabMain;
    // End of variables declaration//GEN-END:variables
}
