

package vista;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class TabFiltros extends javax.swing.JPanel {

    private boolean filtroActivado;
    private MainPanel mainPanel;
    private Date min;
    private Date max;
    
    
    public TabFiltros(MainPanel mainPanel ) {
    
        this.filtroActivado = false;
        this.mainPanel = mainPanel;
        initComponents();
        String cadena;
        cadena = "<html><body>"
                + "APLICAR<br>"
                + "FILTROS<br></body>"
                + "</html>";
        botonFiltro.setText(cadena);
        cadena = "<html><body>"
                + "ELIMINAR<br>"
                + "FILTROS<br></body>"
                + "</html>";
        botonReset.setText(cadena);
        
        checkRutas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroCheckers();
            }
        });
        
        checkLugares.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroCheckers();
            }
        });
    }
        
    private void filtroCheckers(){
        mainPanel.filtroCheckers(!checkRutas.isSelected(), !checkLugares.isSelected());
    }
    
    
    public void init(LocalDate min, LocalDate max){
        this.min = toDate(min);
        this.max = toDate(max);
        limitarFecha(this.min, this.max);
        fechaStart.setDate(this.min);
        fechaEnd.setDate(this.max);
    }
    
    
    public Date toDate(LocalDate localDate) {
	//default time zone
	ZoneId defaultZoneId = ZoneId.systemDefault();
        
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }
    
    
    public LocalDate fromDateToLocalDate1(Date date) {
        return date.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
    
    
    public LocalDate fromDateToLocalDate2(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public void limitarFecha(Date min, Date max){
        fechaStart.setMinSelectableDate(min);
        fechaStart.setMaxSelectableDate(max);
        fechaEnd.setMinSelectableDate(min);
        fechaEnd.setMaxSelectableDate(max);
    }


    public void limitarFecha(LocalDate min, LocalDate max){
        fechaStart.setMinSelectableDate(toDate(min));
        fechaStart.setMaxSelectableDate(toDate(max));
        fechaEnd.setMinSelectableDate(toDate(min));
        fechaEnd.setMaxSelectableDate(toDate(max));
    }

    
    private void filtrar(){
        this.filtroActivado = true;
        mainPanel.aplicarFiltros();
    }
    
    public void resetValores(){
        this.filtroActivado = false;
        fechaStart.setDate(min);
        fechaEnd.setDate(max);
        horaBoxStart.setSelectedIndex(0);
        horaBoxEnd.setSelectedIndex(0);
        checkRutas.setSelected(false);
        checkLugares.setSelected(false);
    }

    public boolean isFiltroActivado() {
        return filtroActivado;
    }

    public void setFiltroActivado(boolean filtroActivado) {
        this.filtroActivado = filtroActivado;
    }

    public boolean getCheckRutas() {
        return checkRutas.isSelected();
    }

    public boolean getCheckLugares() {
        return checkLugares.isSelected();
    }

    public LocalDate getFechaEnd() {
        return fromDateToLocalDate1(fechaEnd.getDate());
    }

    public LocalDate getFechaStart() {
        return fromDateToLocalDate1(fechaStart.getDate());
    }

    public String getHoraBoxEnd() {
        return horaBoxEnd.getSelectedItem().toString();
    }

    public String getHoraBoxStart() {
        return horaBoxStart.getSelectedItem().toString();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonFiltro = new javax.swing.JButton();
        botonReset = new javax.swing.JButton();
        checkLugares = new javax.swing.JToggleButton();
        checkRutas = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fechaStart = new com.toedter.calendar.JDateChooser();
        fechaEnd = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        horaBoxStart = new javax.swing.JComboBox<>();
        horaBoxEnd = new javax.swing.JComboBox<>();

        botonFiltro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botonFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/filtro1.png"))); // NOI18N
        botonFiltro.setText("jButton1");
        botonFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFiltroActionPerformed(evt);
            }
        });

        botonReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/filtro2.png"))); // NOI18N
        botonReset.setText("jButton1");
        botonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResetActionPerformed(evt);
            }
        });

        checkLugares.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        checkLugares.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pin.png"))); // NOI18N
        checkLugares.setText("Lugares");

        checkRutas.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        checkRutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/flag_verde.png"))); // NOI18N
        checkRutas.setText("Rutas");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ENTRE FECHAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fechaStart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        fechaEnd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fechaStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENTRE HORAS");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        horaBoxStart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        horaBoxStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        horaBoxEnd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        horaBoxEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "xxx", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(horaBoxStart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(horaBoxEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horaBoxEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaBoxStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkRutas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkLugares, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkLugares, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
            
    private void botonFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFiltroActionPerformed
        filtrar();
    }//GEN-LAST:event_botonFiltroActionPerformed

    private void botonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonResetActionPerformed
        mainPanel.resetValores();
    }//GEN-LAST:event_botonResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonFiltro;
    private javax.swing.JButton botonReset;
    private javax.swing.JToggleButton checkLugares;
    private javax.swing.JToggleButton checkRutas;
    private com.toedter.calendar.JDateChooser fechaEnd;
    private com.toedter.calendar.JDateChooser fechaStart;
    private javax.swing.JComboBox<String> horaBoxEnd;
    private javax.swing.JComboBox<String> horaBoxStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
