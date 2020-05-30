package DAO;

import Logic.Ciclo;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Parameters.CRUD_Ciclo;
import Parameters.Menssage_Error;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class DAO_Ciclo extends Service {

    private static final DAO_Ciclo dao = new DAO_Ciclo();

    public DAO_Ciclo() {
    }

    public static DAO_Ciclo getDAO() {
        return dao;
    }

    public void insert(Ciclo object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Ciclo.INSERT.getValue());
                pstmt.setInt(1, object.getAnno());
                pstmt.setInt(2, object.getNume());
                pstmt.setDate(3, object.getFech_inic());
                pstmt.setDate(4, object.getFech_fina());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_INSERTED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public void update(Ciclo object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Ciclo.UPDATE.getValue());
                pstmt.setInt(1, object.getId());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_UPDATED.getValue());
                }
                pstmt.setInt(1, object.getAnno());
                pstmt.setInt(2, object.getNume());
                pstmt.setDate(3, object.getFech_inic());
                pstmt.setDate(4, object.getFech_fina());
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public Ciclo query(Ciclo p_object) {
        Ciclo object = new Ciclo();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Ciclo.QUERY.getValue());
                pstmt.setInt(2, p_object.getId());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    object.setId(rs.getInt(1));
                    object.setAnno(rs.getInt(2));
                    object.setNume(rs.getInt(3));
                    object.setFech_inic(rs.getDate(4));
                    object.setFech_fina(rs.getDate(5));
                }
                if(object.getId() == -1){
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_FOUND.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        return object;
    }

    public List<Ciclo> list() {
        List<Ciclo> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Ciclo.LIST.getValue());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Ciclo object = new Ciclo();
                    object.setId(rs.getInt(1));
                    object.setAnno(rs.getInt(2));
                    object.setNume(rs.getInt(3));
                    object.setFech_inic(rs.getDate(4));
                    object.setFech_fina(rs.getDate(5));
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        return list;
    }

    public void delete(Ciclo object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Ciclo.DELETE.getValue());
                pstmt.setInt(1, object.getId());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_DELETED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }
}
