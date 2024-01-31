
package vista;

import modelo.entidad.PlaceVisit;
import modelo.entidad.ActivitySegment;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelGraph extends javax.swing.JPanel {

    
    public PanelGraph() {
        initComponents();
    }
    
    public void init(List<ActivitySegment> activitySegments, 
        List<PlaceVisit> placeVisits){
        jPanel1.setLayout(new BorderLayout());
        initGraficoRutas(activitySegments);
        initGraficoLugares(placeVisits);
        updateUI();
        
    }
    
    
    private void initGraficoRutas(List<ActivitySegment> activitySegments){
        
        DefaultCategoryDataset datos = new DefaultCategoryDataset();        
        for( ActivitySegment act : activitySegments){
            datos.setValue((Number)act.getDistanceMeters(), 
                    act.getStartTimestamp().toLocalTime(), 
                    act.getStartTimestamp().getDayOfMonth());
        }
        
        JFreeChart grafico = ChartFactory.createStackedBarChart("RUTAS",
                "DIAS",
                "METROS",
                datos);
        grafico.removeLegend();
                
        ChartPanel panel = new ChartPanel(grafico);
        panel.setMouseWheelEnabled(false);
        
//        int tam = activitySegments.size();
//        int extra = 0;
//        if(((CategoryPlot)grafico.getPlot()).getRangeAxis().getUpperBound()>14000){
//            
//            double aux  = ((CategoryPlot)grafico.getPlot()).getRangeAxis().getUpperBound()/1400;
//            extra = (int)(aux-10)*18;
//            System.out.println(extra);
//        }        
//        panel.setPreferredSize(new Dimension(tam*5,360+extra));

        jPanel1.add(panel,BorderLayout.NORTH);
        
    }
    
    private void initGraficoLugares(List<PlaceVisit> placeVisits){
        
        LocalDateTime fechaHora1;
        LocalDateTime fechaHora2;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for( PlaceVisit plv : placeVisits){
            
            fechaHora1 = plv.getStartTimestamp();
            fechaHora2 = plv.getEndTimestamp();
            if(fechaHora1.getDayOfMonth()== fechaHora2.getDayOfMonth()){
                datos.setValue((Number)ChronoUnit.MINUTES.between(fechaHora1, fechaHora2), 
                        fechaHora1.toLocalTime(), 
                        fechaHora1.getDayOfMonth());
            }
            else
            {
                LocalDateTime aux = fechaHora1.toLocalDate().plusDays(1).atStartOfDay();
                datos.setValue((Number)ChronoUnit.MINUTES.between(fechaHora1, aux), 
                        fechaHora1.toLocalTime(), 
                        fechaHora1.getDayOfMonth());
                
                datos.setValue((Number)ChronoUnit.MINUTES.between(aux, fechaHora2), 
                        aux.toLocalTime(), 
                        aux.getDayOfMonth());
            }
        }
        JFreeChart grafico = ChartFactory.createStackedBarChart("LUGARES",
                "DIAS",
                "MINUTOS",
                datos);
        grafico.removeLegend();

        ChartPanel chart = new ChartPanel(grafico);
        chart.setMouseWheelEnabled(false);
        
//        int tam = placeVisits.size();
//        chart.setPreferredSize(new Dimension(tam2*5,360));
        
        jPanel1.add(chart);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(1000, 560));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Graficos"));
        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
