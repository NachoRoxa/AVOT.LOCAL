/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONEXION.Conexion;
import DTO.Apoderado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author cetecom
 */
public class ApoderadoDaoImp implements ApoderadoDao {

    @Override
    public ArrayList<Apoderado> ListarPorColegio(String colegio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(Apoderado dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql;
            sql = "{call PR_AGREGAR_APODERADO(?,?,?,?,?,?,?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString(1,dto.getRun());
            proc.setString(2,dto.getUser());
            proc.setString(3,dto.getTelefono());
            proc.setString(4,dto.getCorreo());
            proc.setString(5,dto.getNombre());
            proc.setString(6,dto.getApellido());
            proc.setInt(7,dto.getRepresentante());
            proc.executeQuery();
            return true;
        } catch (Exception ex) {
            System.out.println("Ocurrio un problema con el procedure PR_AGREGAR_APODERADO: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Apoderado buscar(Apoderado dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Apoderado dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql;
            sql = "{call PR_BORRAR_APODERADO(?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString(1, dto.getRun());
            proc.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Ocurrio un problema con el procedure PR_GENERARPAGO: " + ex.getMessage());
            return false;
        }
    }

    /*LLAMADO A PROCEDIMIENTO PARA ACTUALIZAR*/
    @Override
    public boolean modificar(Apoderado dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql;
            sql = "{call PR_UPDATE_APODERADO(?,?,?,?,?,?,?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString("IN_RUN", dto.getRun());
            proc.setString("IN_USUARIO", dto.getUser());
            proc.setString("IN_TELEFONO", dto.getTelefono());
            proc.setString("IN_CORREO", dto.getCorreo());
            proc.setString("IN_NOMBRE", dto.getNombre());
            proc.setString("IN_APELLIDO", dto.getApellido());
            proc.setInt("IN_REPRESENTANTE", dto.getRepresentante());
            proc.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Ocurrio un problema con el procedure PR_UPDATE_APODERADO: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean existe(Apoderado dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Apoderado> listar() {
        CONEXION.Conexion obj = new Conexion();
        ArrayList<Apoderado> lista = new ArrayList<>();
        try {
            Connection con = obj.getConnection();
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery("SELECT RUN,NOMBRE,APELLIDO,USUARIO,CORREO,TELEFONO,REPRESENTANTE FROM APODERADOS");
            while (re.next()) {
                Apoderado apoderado = new Apoderado();
                apoderado.setRun(re.getString(1));
                apoderado.setNombre(re.getString(2));
                apoderado.setApellido(re.getString(3));
                apoderado.setUser(re.getString(4));
                apoderado.setCorreo(re.getString(5));
                apoderado.setTelefono(re.getString(6));
                apoderado.setRepresentante(re.getInt(7));
                lista.add(apoderado);
            }

        } catch (Exception e) {
            return lista;
        }
        return lista;

    }

}
