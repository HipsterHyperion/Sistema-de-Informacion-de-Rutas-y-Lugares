
package vista;

import control.ControlFrame;
import java.awt.Component;
import javax.swing.JFrame;

public class MainFrame extends javax.swing.JFrame {
    
    
    private static MainFrame main;
    
    //constructor
    private MainFrame() {
        initComponents();
        init();
    }
    
    //Singleton
    public static MainFrame getInstance(){
        if (main == null) {
            main = new MainFrame();
        }
        return main;
    }
    
    private void init(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        jTabbedPane1.addTab("Bienvenido", new javax.swing.ImageIcon(getClass().getResource("/icon/home.png")), new PanelBienvenida());
    }
    
    
    public void nuevoTab(String title, Component panel){
        
        int tabCount = jTabbedPane1.getTabCount();
        jTabbedPane1.addTab(title, new javax.swing.ImageIcon(getClass().getResource("/icon/brujula.png")), panel);
        jTabbedPane1.setSelectedIndex(tabCount);
    }

    //  InitComponent
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        menuAbrir = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Informacion Geografica");
        setBackground(new java.awt.Color(204, 255, 204));
        setMinimumSize(new java.awt.Dimension(1100, 720));

        jTabbedPane1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(780, 480));

        jMenuFile.setText("File");

        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        jMenuFile.add(menuAbrir);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuBar1.add(jMenuEdit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        // TODO add your handling code here:
        ControlFrame.getInstance().openFile();
    }//GEN-LAST:event_menuAbrirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem menuAbrir;
    // End of variables declaration//GEN-END:variables
}
