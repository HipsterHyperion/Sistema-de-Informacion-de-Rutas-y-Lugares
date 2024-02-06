
package vista;

import modelo.entidad.PlaceVisit;
import modelo.entidad.ActivitySegment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import modelo.entidad.MyEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelGraph extends javax.swing.JPanel {

    
    public PanelGraph() {
        initComponents();
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
    }
    
    
     public void initGraficoRutas(SortedMap<LocalDate, List<MyEvent>> lineaDeEventos){
        
        jPanel1.removeAll();
        Map<String, DefaultCategoryDataset> datosMap = new HashMap<>();
        String fechazo;
        List<String> listaFechazo = new ArrayList<>();
        for(LocalDate fecha : lineaDeEventos.keySet()){
            System.out.println(lineaDeEventos.get(fecha).size());
            fechazo = fecha.getMonth().toString()+" "+ fecha.getYear();
            if(datosMap.get(fechazo)==null){
                listaFechazo.add(fechazo);
                datosMap.put(fechazo,  new DefaultCategoryDataset());
                datosMap.get(fechazo).setValue((Number)0, 
                        fecha.atStartOfDay().toLocalTime(), 
                        fecha.getDayOfMonth());
            }
            for(MyEvent activitySegment : lineaDeEventos.get(fecha)){
                if(activitySegment instanceof ActivitySegment){
                    LocalDateTime fechaHora;
                    fechaHora = activitySegment.getStartTimestamp();
                    datosMap.get(fechazo).setValue((Number)((ActivitySegment) activitySegment).getDistanceMeters(), 
                            fechaHora.toLocalTime(), 
                            fechaHora.getDayOfMonth());
                }
            }
        }
        
        for(String m :  listaFechazo){
            if(datosMap.get(m).getRowCount()>1){
                JFreeChart grafico;
                grafico = ChartFactory.createStackedBarChart(m,
                        "DIAS",
                        "METROS",
                        datosMap.get(m));
                grafico.removeLegend();

                ChartPanel chart = new ChartPanel(grafico);
                chart.setMouseWheelEnabled(false);

                jPanel1.add(chart);
            }
        }
        
        updateUI();
    }
    
    
    
    
    public void initGraficoLugares(SortedMap<LocalDate, List<MyEvent>> lineaDeEventos){
        
        jPanel1.removeAll();
        Map<String, DefaultCategoryDataset> datosMap = new HashMap<>();
        String fechazo;
        List<String> listaFechazo = new ArrayList<>();
        for(LocalDate fecha : lineaDeEventos.keySet()){
            System.out.println(lineaDeEventos.get(fecha).size());
            fechazo = fecha.getMonth().toString()+" "+ fecha.getYear();
            if(datosMap.get(fechazo)==null){
                listaFechazo.add(fechazo);
                datosMap.put(fechazo,  new DefaultCategoryDataset());
                datosMap.get(fechazo).setValue((Number)0, 
                        fecha.atStartOfDay().toLocalTime(), 
                        fecha.getDayOfMonth());
            }
            for(MyEvent placeVisit : lineaDeEventos.get(fecha)){
                if(placeVisit instanceof PlaceVisit){
                    LocalDateTime fechaHora;
                    fechaHora = placeVisit.getStartTimestamp();
                    datosMap.get(fechazo).setValue((Number)placeVisit.getDuration(), 
                            fechaHora.toLocalTime(), 
                            fechaHora.getDayOfMonth());
                }
            }
        }
        
        for(String m :  listaFechazo){
            if(datosMap.get(m).getRowCount()>1){
                JFreeChart grafico;
                grafico = ChartFactory.createStackedBarChart(m,
                        "DIAS",
                        "MINUTOS",
                        datosMap.get(m));
                grafico.removeLegend();

                ChartPanel chart = new ChartPanel(grafico);
                chart.setMouseWheelEnabled(false);

                jPanel1.add(chart);
            }
        }
        
        updateUI();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(720, 560));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
