/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import CONEXION.Conexion;
import DAO.EstadiaDaoImp;
import DTO.Estadia;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Atxy2k.CustomTextField.RestrictedTextField;
import DTO.Tour;
import VISTA.CONTROLES.ButtonColumn;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Seba
 */
public class GestionEstadia extends javax.swing.JFrame {

    ArrayList<Estadia> listaEstadia;
    Conexion obj = new Conexion();
    DefaultTableModel modelo;
    int flag;
    Estadia estadia;

    /**
     * Creates new form GestionEstadia
     *
     * @param admin
     */
    public GestionEstadia(int admin) {
        Admin(admin);
        initComponents();
        this.setLocationRelativeTo(null);
        MostrarEstadias();
        RestrictedTextField restricted1 = new RestrictedTextField(txtCapacidad);
        restricted1.setOnlyNums(rootPaneCheckingEnabled);
        RestrictedTextField restricted2 = new RestrictedTextField(txtCostoPorDia);
        restricted2.setOnlyNums(rootPaneCheckingEnabled);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
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

    public void LimpiarFormulario() {
        txtNombre.setText(null);
        txtCapacidad.setText(null);
        txtCostoPorDia.setText(null);
        txtDireccion.setText(null);
        RBActivo.setSelected(false);
        RBInactivo.setSelected(false);
    }

    public void ResetBotones() {
        btnAgregarEstadia.setVisible(true);

    }

    public void MostrarEstadias() {
        listaEstadia = new EstadiaDaoImp().listar();
        modelo = new DefaultTableModel();
        modelo.addColumn("");
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("COSTO");
        modelo.addColumn("ESTADO");
        modelo.addColumn("CAPACIDAD");
        modelo.addColumn("");
        if (listaEstadia.size() > 0) {
            for (Estadia estadia : listaEstadia) {
                String estado;
                if (estadia.getEstado() == 0) {
                    estado = "INACTIVO";
                } else {
                    estado = "ACTIVO";
                }
                modelo.addRow(new Object[]{
                    "EDITAR",
                    estadia.getId_estadia(),
                    estadia.getNombre(),
                    estadia.getDireccion(),
                    estadia.getCosto_por_dia(),
                    estado,
                    estadia.getCapacidad(),
                    "ELIMINAR"}
                );
            }
            tablaEstadias.setModel(modelo);

            Action borrar = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int fila = Integer.valueOf(e.getActionCommand());
                    estadia = new Estadia();
                    estadia = listaEstadia.get(fila);
                    new EstadiaDaoImp().eliminar(estadia);
                    LimpiarFormulario();
                    tablaEstadias.clearSelection();
                    MostrarEstadias();
                    ResetBotones();
                }
            };

            ButtonColumn buttonEliminar = new ButtonColumn(tablaEstadias, borrar, 7);
            buttonEliminar.setMnemonic(KeyEvent.VK_D);

            Action editar = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int fila = Integer.valueOf(e.getActionCommand());
                    estadia = listaEstadia.get(fila);
                    txtNombre.setText(estadia.getNombre());
                    txtDireccion.setText(estadia.getDireccion());
                    txtCapacidad.setText(String.valueOf(estadia.getCapacidad()));
                    txtCostoPorDia.setText(String.valueOf(estadia.getCosto_por_dia()));
                    btnAgregarEstadia.setVisible(false);
                    btnCancelar.setVisible(true);
                    btnGuardar.setVisible(true);
                }
            };

            ButtonColumn buttonEditar = new ButtonColumn(tablaEstadias, editar, 0);
            buttonEditar.setMnemonic(KeyEvent.VK_D);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        PanelTitulo = new javax.swing.JPanel();
        lblAVOT = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstadias = new javax.swing.JTable();
        PanelInsertar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCostoPorDia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAgregarEstadia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        RBActivo = new javax.swing.JRadioButton();
        RBInactivo = new javax.swing.JRadioButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1090, 505));

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

        PanelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista  Estadias"));

        tablaEstadias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaEstadias);

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelInsertar.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Estadia "));

        jLabel2.setText("Costo por dia");

        jLabel5.setText("Nombre");

        jLabel6.setText("Estado");

        btnAgregarEstadia.setText("Agregar");
        btnAgregarEstadia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEstadiaActionPerformed(evt);
            }
        });

        jLabel1.setText("Direccion");

        jLabel7.setText("Capacidad");

        buttonGroup1.add(RBActivo);
        RBActivo.setText("Activo");

        buttonGroup1.add(RBInactivo);
        RBInactivo.setText("Inactivo");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInsertarLayout = new javax.swing.GroupLayout(PanelInsertar);
        PanelInsertar.setLayout(PanelInsertarLayout);
        PanelInsertarLayout.setHorizontalGroup(
            PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertarLayout.createSequentialGroup()
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInsertarLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnAgregarEstadia))
                    .addGroup(PanelInsertarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar))
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCostoPorDia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(75, 75, 75)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelInsertarLayout.createSequentialGroup()
                                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelInsertarLayout.createSequentialGroup()
                                        .addComponent(RBActivo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(RBInactivo))
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        PanelInsertarLayout.setVerticalGroup(
            PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInsertarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoPorDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(45, 45, 45)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(45, 45, 45)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(RBActivo)
                    .addComponent(RBInactivo))
                .addGap(45, 45, 45)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarEstadia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnAgregarEstadiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEstadiaActionPerformed
        Estadia estadia = new Estadia();
        if (txtNombre.getText().trim().isEmpty()) {
            this.setVisible(true);
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un Nombre");
        } else if (txtCostoPorDia.getText().trim().isEmpty()) {
            this.setVisible(true);
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un Costo por dia");
        } else if (txtDireccion.getText().trim().isEmpty()) {
            this.setVisible(true);
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una Direccion");
        } else if (txtCapacidad.getText().trim().isEmpty()) {
            this.setVisible(true);
            JOptionPane.showMessageDialog(null, "Por favor, ingrese Capasidad de alojamiento");
        } else {
            estadia.setNombre(txtNombre.getText());
            estadia.setCosto_por_dia(Integer.valueOf(txtCostoPorDia.getText()));
            estadia.setDireccion(txtDireccion.getText());
            if (RBActivo.isSelected()) {
                estadia.setEstado(1);
            } else {
                estadia.setEstado(0);
            }
            estadia.setCapacidad(Integer.valueOf(txtCapacidad.getText()));
            new EstadiaDaoImp().insertar(estadia);
            txtNombre.setText(null);
            txtCostoPorDia.setText(null);
            txtDireccion.setText(null);
            txtCapacidad.setText(null);
            buttonGroup1.clearSelection();
            tablaEstadias.clearSelection();
            MostrarEstadias();
        }
    }//GEN-LAST:event_btnAgregarEstadiaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ResetBotones();
        LimpiarFormulario();
        tablaEstadias.clearSelection();
        MostrarEstadias();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        estadia.setNombre(txtNombre.getText());
        estadia.setDireccion(txtDireccion.getText());
        estadia.setCosto_por_dia(Integer.parseInt(txtCostoPorDia.getText()));
        estadia.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
        if (RBActivo.isSelected()) {
            estadia.setEstado(1);
        } else {
            estadia.setEstado(0);
        }
        if (RBInactivo.isSelected()) {
            estadia.setEstado(0);
        } else {
            estadia.setEstado(1);
        }
        new EstadiaDaoImp().modificar(estadia);
        ResetBotones();
        LimpiarFormulario();
        tablaEstadias.clearSelection();
        MostrarEstadias();
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInsertar;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JRadioButton RBActivo;
    private javax.swing.JRadioButton RBInactivo;
    private javax.swing.JButton btnAgregarEstadia;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInicio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAVOT;
    private javax.swing.JTable tablaEstadias;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCostoPorDia;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
