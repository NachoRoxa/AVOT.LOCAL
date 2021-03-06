/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import CONEXION.Conexion;
import DTO.Agente;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 *
 * @author NachoRoxa
 */
public class AgenteDaoImp implements BaseDao<Agente> {

    @Override
    public boolean insertar(Agente dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql;
            sql = "{call PR_AGREGAR_AGENTE(?,?,?,?,?,?,?,?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString(1, dto.getRun());
            proc.setString(2, dto.getUser());
            proc.setString(3, dto.getPasswd());
            proc.setString(4, dto.getNombre());
            proc.setString(5, dto.getApellido_paterno());
            proc.setString(6, dto.getApellido_materno());
            proc.setInt(7, dto.getAdministrador());
            proc.setInt(8, dto.getEstado());
            proc.executeQuery();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un problema con el procedimiento, intente mas tarde o comuniquese con el Administrador de Sistema.",
                     "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public Agente buscar(Agente dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Agente dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql = "{call PR_BORRAR_AGENTE(?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString(1, dto.getRun());
            proc.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Ocurrio un problema con el procedure PR_BORRAR_AGENTE: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Agente dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql;
            sql = "{call PR_UPDATE_AGENTE(?,?,?,?,?,?,?,?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString(1, dto.getRun());
            proc.setString(2, dto.getUser());
            proc.setString(3, dto.getPasswd());
            proc.setString(4, dto.getNombre());
            proc.setString(5, dto.getApellido_paterno());
            proc.setString(6, dto.getApellido_materno());
            proc.setInt(7, dto.getAdministrador());
            proc.setInt(8, dto.getEstado());
            proc.executeQuery();
            return true;
        } catch (Exception ex) {
            System.out.println("Ocurrio un problema con el procedure PR_AGREGAR_AGENTE: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean existe(Agente dto) {
        CONEXION.Conexion obj = new Conexion();
        Agente ag = new Agente();
        String usuario = ag.getUser();
        String pass = ag.getPasswd();
        String query = "select usuario, passwd from agente where usuario = " + usuario + " and passwd= " + pass + ";";
        try {
            Connection con = obj.getConnection();
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(query);

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList listar() {
        CONEXION.Conexion obj = new Conexion();
        ArrayList<Agente> lista = new ArrayList<>();
        try {
            Connection con = obj.getConnection();
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery("select run,usuario,passwd,nombre,ap"
                    + "ellido_paterno,apellido_materno,administrador,estado fro"
                    + "m agentes");
            while (re.next()) {
                Agente agente = new Agente();
                agente.setRun(re.getString(1));
                agente.setUser(re.getString(2));
                agente.setPasswd(re.getString(3));
                agente.setNombre(re.getString(4));
                agente.setApellido_paterno(re.getString(5));
                agente.setApellido_materno(re.getString(6));
                agente.setAdministrador(re.getInt(7));
                agente.setEstado(re.getInt(8));
                lista.add(agente);
            }
        } catch (SQLException e) {
            return lista;
        }
        return lista;
    }
}
