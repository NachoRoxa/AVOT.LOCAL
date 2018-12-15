/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import DAO.ActividadGiraDaoImp;
import DAO.ContratoDao;
import DAO.EstadiaDaoImp;
import DAO.SeguroDaoImp;
import DAO.TourDaoImp;
import DAO.ViajeDaoImp;
import DTO.Alumno;
import DTO.Apoderado;
import DTO.Colegio;
import DTO.GenerarContratoPDF;
import DTO.Tour;
import VISTA.CONTROLES.ItemCombo;
import com.itextpdf.text.pdf.AcroFields;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Seba
 */
public class GeneradorContratoPDF extends javax.swing.JFrame {

    ArrayList<Tour> listaTour;
    Tour tour;
    Colegio colegio;
    Apoderado apoderadojefe;
    int flag;

    /**
     * Creates new form Generador
     *
     * @param admin
     */
    public GeneradorContratoPDF(int admin) {

        Admin(admin);
        initComponents();
        datosCombobox();
        this.setLocationRelativeTo(null);
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
        jPanel1 = new javax.swing.JPanel();
        btnGenerarContrato = new javax.swing.JButton();
        cbTourContrato = new javax.swing.JComboBox<>();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 346, Short.MAX_VALUE)
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

        btnGenerarContrato.setText("Generar Contrato");
        btnGenerarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarContratoActionPerformed(evt);
            }
        });

        cbTourContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Tour" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGenerarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTourContrato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cbTourContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(btnGenerarContrato)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarContratoActionPerformed

        tour = listaTour.get(cbTourContrato.getSelectedIndex()-1);
        tour.setActividades(new ActividadGiraDaoImp().listarActividadesTour(tour.getId_tour()));
        tour.setEstadias(new EstadiaDaoImp().listarEstadiasTour(tour.getId_tour()));
        tour.setSeguros(new SeguroDaoImp().listarSegurosTour(tour.getId_tour()));
        tour.setViajes(new ViajeDaoImp().listarViajesTour(tour.getId_tour()));
        colegio = new Colegio();
        colegio = new ContratoDao().getColegiosContrato(tour.getId_tour());
        for (Alumno alumno : colegio.getCurso().getAlumnos()) {
            if (alumno.getApoderado().getRepresentante() > 0) {
                apoderadojefe = alumno.getApoderado();
                break;
            }
        }
        colegio.getCurso().setActividades(new ContratoDao().getActividadesColegio(colegio.getCurso().getId_curso()));

        if (colegio.getCurso().getAlumnos().size() > 0) {
            new GenerarContratoPDF().generarPDF(tour, colegio, apoderadojefe);
            JOptionPane.showMessageDialog(null, "PDF Creado Correctamente.");
        }
    }//GEN-LAST:event_btnGenerarContratoActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        Admin(flag);
        this.setVisible(false);
        Index x = new Index(flag);
        x.setVisible(true);
    }//GEN-LAST:event_btnInicioActionPerformed

    public void datosCombobox() {
        
        listaTour = new TourDaoImp().listar();
        if (listaTour.size() > 0) {
            for (Tour tour : listaTour) {
                cbTourContrato.addItem(tour.getDescripcion());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JButton btnGenerarContrato;
    private javax.swing.JButton btnInicio;
    private javax.swing.JComboBox<String> cbTourContrato;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAVOT;
    // End of variables declaration//GEN-END:variables

}
