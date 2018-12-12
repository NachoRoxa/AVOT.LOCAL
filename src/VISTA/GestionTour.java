/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import Atxy2k.CustomTextField.RestrictedTextField;
import CONEXION.Conexion;
import DAO.AgenteDaoImp;
import DAO.TourDaoImp;
import DTO.Agente;
import DTO.Tour;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Seba
 */
public class GestionTour extends javax.swing.JFrame {

    ArrayList<Tour> listaTour;
    ArrayList<Agente> listaAgente;
    Agente agente;
    Conexion obj = new Conexion();
    DefaultTableModel modelo;
    int flag;

    /**
     * Creates new form GestionTour
     *
     * @param admin
     */
    public GestionTour(int admin) {
        Admin(admin);
        initComponents();
        this.setLocationRelativeTo(null);
        MostrarTours();
        datosComboBox();
        RestrictedTextField restricted = new RestrictedTextField(txtFechaInicio);
        restricted.setLimit(10);
        txtFechaInicio.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                } else {
                    int[] mesesUp = {1,3,5,7,8,10,12};
                    int[] mesesDn = {4,6,9,11};
                    String texto;
                    texto = txtFechaInicio.getText();
                    texto = texto.replace("/", "");
                    String dia;
                    String mes;
                    int ano;
                    int diaInt;
                    int mesInt;
                    boolean bisiesto = false;
                    GregorianCalendar calendar = new GregorianCalendar();
                    switch (texto.length()) {
                        case 8:
                            String[] array = texto.split("");
                            dia = array[0] + array[1];
                            diaInt = Integer.parseInt(dia);
                            mes = array[2] + array[3];
                            mesInt = Integer.parseInt(mes);
                            ano = Integer.parseInt(array[4] + array[5] + array[6] + array[7]);
                            if (calendar.isLeapYear(ano)) {
                                bisiesto = true;
                            }
                            if (Integer.parseInt(mes) > 12) {
                                mes = "12";
                            } else if (Integer.parseInt(mes) < 1) {
                                mes = "01";
                            }
                            if (Integer.parseInt(mes) == 2) {
                                if (bisiesto && Integer.parseInt(dia) > 29) {
                                    dia = "29";
                                } else if (Integer.parseInt(dia) > 28) {
                                    dia = "28";
                                }

                            } else if (IntStream.of(mesesUp).anyMatch(x -> x == mesInt) && mesInt>31 ) {
                                dia = "31"; 
                            } else if (IntStream.of(mesesDn).anyMatch(x -> x == mesInt) && mesInt>30 ) {
                                dia = "30";
                            }
                            txtFechaInicio.setText(dia+"/"+mes+"/"+ano);
                            
                        default:
                            texto = texto.replace("/", "");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }else{
                {
                    int[] mesesUp = {1,3,5,7,8,10,12};
                    int[] mesesDn = {4,6,9,11};
                    String texto;
                    texto = txtFechaInicio.getText();
                    texto = texto.replace("/", "");
                    String dia;
                    String mes;
                    int ano;
                    int diaInt;
                    int mesInt;
                    boolean bisiesto = false;
                    GregorianCalendar calendar = new GregorianCalendar();
                    switch (texto.length()) {
                        case 8:
                            String[] array = texto.split("");
                            dia = array[0] + array[1];
                            diaInt = Integer.parseInt(dia);
                            mes = array[2] + array[3];
                            mesInt = Integer.parseInt(mes);
                            ano = Integer.parseInt(array[4] + array[5] + array[6] + array[7]);
                            if (calendar.isLeapYear(ano)) {
                                bisiesto = true;
                            }
                            if (Integer.parseInt(mes) > 12) {
                                mes = "12";
                            } else if (Integer.parseInt(mes) < 1) {
                                mes = "01";
                            }
                            if (Integer.parseInt(mes) == 2) {
                                if (bisiesto && Integer.parseInt(dia) > 29) {
                                    dia = "29";
                                } else if (Integer.parseInt(dia) > 28) {
                                    dia = "28";
                                }

                            } else if (IntStream.of(mesesUp).anyMatch(x -> x == mesInt) && mesInt>31 ) {
                                dia = "31"; 
                            } else if (IntStream.of(mesesDn).anyMatch(x -> x == mesInt) && mesInt>30 ) {
                                dia = "30";
                            }
                            txtFechaInicio.setText(dia+"/"+mes+"/"+ano);
                            
                        default:
                            texto = texto.replace("/", "");
                    }
                }
                }
            }
        });

    }

    /**
     * *
     * Metodo para ver si el usuario posee perfil de administrador.
     *
     * @param admin
     * @return
     */
    public boolean Admin(int admin) {
        this.flag = admin;
        if (admin == 1) {
            flag = 1;
            return true;
        } else {
            flag = 0;
            return false;
        }
    }

    public void MostrarTours() {
        listaTour = new TourDaoImp().listar();
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("TOTAL");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("NUMERO CONTRATO");
        modelo.addColumn("NOMBRE AGENTE");
        modelo.addColumn("APELLIDO P");
        modelo.addColumn("APELLIDO M");
        modelo.addColumn("CREACION");
        modelo.addColumn("INICIO");
        modelo.addColumn("");
        if (listaTour.size() > 0) {
            for (Tour tour : listaTour) {
                modelo.addRow(new Object[]{
                    tour.getId_tour(),
                    tour.getValor_total(),
                    tour.getDescripcion(),
                    tour.getNumero_contrato(),
                    tour.getAgente().getNombre(),
                    tour.getAgente().getApellido_paterno(),
                    tour.getAgente().getApellido_materno(),
                    tour.getFecha_creacion(),
                    tour.getFecha_inicio(),
                    "ELIMINAR"}
                );
            }
            tablaTours.setModel(modelo);
        }
    }

    public void datosComboBox() {
        listaAgente = new AgenteDaoImp().listar();
        for (Agente agente : listaAgente) {
            cbAgente.addItem(agente.getNombre() + " " + agente.getApellido_paterno());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelTitulo = new javax.swing.JPanel();
        lblAVOT = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTours = new javax.swing.JTable();
        PanelInsertar = new javax.swing.JPanel();
        txtActividad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAgregarActividad = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        cbAgente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        dpFechaInicio = new com.toedter.calendar.JDateChooser();
        dpFechaTermino = new com.toedter.calendar.JDateChooser();
        txtFechaInicio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        PanelTitulo.setBackground(new java.awt.Color(30, 160, 250));

        lblAVOT.setBackground(new java.awt.Color(255, 255, 255));
        lblAVOT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAVOT.setForeground(new java.awt.Color(255, 255, 255));
        lblAVOT.setText("A.V.O.T.");

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTituloLayout = new javax.swing.GroupLayout(PanelTitulo);
        PanelTitulo.setLayout(PanelTituloLayout);
        PanelTituloLayout.setHorizontalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAVOT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInicio)
                .addContainerGap())
        );
        PanelTituloLayout.setVerticalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAVOT)
                    .addComponent(btnInicio))
                .addContainerGap())
        );

        PanelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista  Actividades Gira"));

        tablaTours.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaTours);

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelInsertar.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Actividad Gira"));

        jLabel3.setText("Id");

        jLabel4.setText("Costo");

        jLabel5.setText("Descripcion");

        jLabel6.setText("Fecha Inicio");

        btnAgregarActividad.setText("Agregar");
        btnAgregarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActividadActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel1.setText("Agente");

        cbAgente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Agente" }));

        jLabel2.setText("Fecha Termino");

        javax.swing.GroupLayout PanelInsertarLayout = new javax.swing.GroupLayout(PanelInsertar);
        PanelInsertar.setLayout(PanelInsertarLayout);
        PanelInsertarLayout.setHorizontalGroup(
            PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelInsertarLayout.createSequentialGroup()
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInsertarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelInsertarLayout.createSequentialGroup()
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(btnAgregarActividad))
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInsertarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE))
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addGap(81, 81, 81)))
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCosto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(cbAgente, javax.swing.GroupLayout.Alignment.LEADING, 0, 150, Short.MAX_VALUE)
                            .addComponent(dpFechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dpFechaTermino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaInicio))))
                .addContainerGap())
        );
        PanelInsertarLayout.setVerticalGroup(
            PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelInsertarLayout.createSequentialGroup()
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbAgente)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dpFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dpFechaTermino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnAgregarActividad)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        Admin(flag);
        this.setVisible(false);
        Index x = new Index(flag);
        x.setVisible(true);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAgregarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActividadActionPerformed

    }//GEN-LAST:event_btnAgregarActividadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInsertar;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JButton btnAgregarActividad;
    private javax.swing.JButton btnInicio;
    private javax.swing.JComboBox<String> cbAgente;
    private com.toedter.calendar.JDateChooser dpFechaInicio;
    private com.toedter.calendar.JDateChooser dpFechaTermino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAVOT;
    private javax.swing.JTable tablaTours;
    private javax.swing.JTextField txtActividad;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFechaInicio;
    // End of variables declaration//GEN-END:variables
}
