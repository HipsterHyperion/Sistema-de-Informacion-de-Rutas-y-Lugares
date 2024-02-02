

package vista;

import control.ControlFrame;


public class PanelBienvenida extends javax.swing.JPanel {

    
    public PanelBienvenida() {
        initComponents();
        init();
        initButton();
    }

    private void init(){
        String cadena;
        cadena = "<html><body>"
                + "ABRIR<br>"
                + "ARCHIVO<br></body>"
                + "</html>";
        botonAbrir.setText(cadena);
        cadena = "<html><body>"
                + "ARCHIVO<br>"
                + "DE PRUEBA<br></body>"
                + "</html>";
        botonPrueba.setText(cadena);
    }
    
    
    private void initButton(){
        
        botonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirActionPerformed(evt);
            }
        });
        
        botonPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPruebaActionPerformed(evt);
            }
        });
    }
    
    
    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {     
        ControlFrame.getInstance().openFile();
    } 
    
    
    private void botonPruebaActionPerformed(java.awt.event.ActionEvent evt) {     
        ControlFrame.getInstance().openFileDefault();
    } 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonAbrir = new javax.swing.JButton();
        botonPrueba = new javax.swing.JButton();

        botonAbrir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botonAbrir.setText("Abrir");
        botonAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        botonPrueba.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botonPrueba.setText("Prueba");
        botonPrueba.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(954, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(botonPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton botonPrueba;
    // End of variables declaration//GEN-END:variables
}
