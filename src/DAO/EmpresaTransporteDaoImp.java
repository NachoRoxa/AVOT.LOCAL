/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONEXION.Conexion;
import DTO.EmpresaTransporte;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Seba
 */
public class EmpresaTransporteDaoImp implements BaseDao<EmpresaTransporte>{

    @Override
    public boolean insertar(EmpresaTransporte dto) {
        CONEXION.Conexion obj = new Conexion();
        try {
            Connection con = obj.getConnection();
            String sql;
            sql = "{call PR_AGREGAR_EMP_TRANSPORTE(?,?)}";
            CallableStatement proc = con.prepareCall(sql);
            proc.setString(1,dto.getNombre_empresa());
            proc.setString(2,dto.getTipo_transporte());
            proc.executeQuery();
            return true;
        } catch (Exception ex) {
            System.out.println("Ocurrio un problema con el procedure PR_AGREGAR_EMP_TRANSPORTE: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public EmpresaTransporte buscar(EmpresaTransporte dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(EmpresaTransporte dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(EmpresaTransporte dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(EmpresaTransporte dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        CONEXION.Conexion obj = new Conexion();
        ArrayList<EmpresaTransporte> lista = new ArrayList<>();
        try {
            Connection con = obj.getConnection();
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery("select id_transporte,nombre_empresa"
                    + ",tipo_transporte from empresas_transporte");
            while (re.next()) {
                EmpresaTransporte empresaTransporte = new EmpresaTransporte();
                empresaTransporte.setId_transporte(re.getInt(1));
                empresaTransporte.setNombre_empresa(re.getString(2));
                empresaTransporte.setTipo_transporte(re.getString(3));
                lista.add(empresaTransporte);
            }
        } catch (Exception e) {
            return lista;
        }
        return lista;
    }
}
