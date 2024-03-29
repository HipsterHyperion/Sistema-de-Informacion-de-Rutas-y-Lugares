

package vista;


import java.time.LocalDate;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class TabFechas extends javax.swing.JPanel {

    private boolean activado;
    private MainPanel mainPanel;
    
    
    public TabFechas(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        activado = false;
        initComponents();
        fechasBox.addItemListener((java.awt.event.ItemEvent evt) -> {
            fechasBoxItemStateChanged();
        });
    }
    
    public void init(Set<LocalDate> fechas){
        Object [] o = fechas.toArray();
        System.out.println(o[0]);
        System.out.println(o[o.length-1]);
        initFechasBox(fechas);
    }
    

    
    private void initFechasBox(Set<LocalDate> fechas) {
        activado = false;
        fechasBox.removeAllItems();
        fechasBox.addItem("FECHAS");
        for (LocalDate fecha : fechas) {
            fechasBox.addItem(fecha.toString());
        }
        int wer = fechasBox.getItemCount();
        System.out.println(wer);
        System.out.println(fechasBox.getItemAt(1));
        System.out.println(fechasBox.getItemAt(wer-1));
        System.out.println(fechasBox.getItemAt(wer));
        activado = true;
        updateUI();
    }
    
    private void fechasBoxItemStateChanged() {                                           

        if (activado){
            mainPanel.initPanelEventos();
        }
    }  

    
    public JComboBox<String> getFechasBox() {
        return fechasBox;
    }

    public JPanel getPanelEventos() {
        return panelEventos;
    }
       
    public void resetValores(){
//        fechasBox.setSelectedIndex(0);
        panelEventos.removeAll();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fechasBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelEventos = new javax.swing.JPanel();

        fechasBox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FECHAS" }));
        fechasBox.setPreferredSize(new java.awt.Dimension(250, 22));

        /*
        panelEventos.setPreferredSize(new java.awt.Dimension(250, 400));
        panelEventos.setLayout(new javax.swing.BoxLayout(panelEventos, javax.swing.BoxLayout.LINE_AXIS));
        */
        panelEventos.setLayout(new javax.swing.BoxLayout(panelEventos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelEventos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fechasBox, 0, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fechasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> fechasBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelEventos;
    // End of variables declaration//GEN-END:variables
}
