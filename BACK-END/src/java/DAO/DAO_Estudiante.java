package DAO;

import static DAO.Service.connection;
import Logic.Curso;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Logic.Estudiante;
import Parameters.CRUD_Estudiante;
import Parameters.Menssage_Error;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class DAO_Estudiante extends Service {

    private static final DAO_Estudiante dao = new DAO_Estudiante();

    public DAO_Estudiante() {
    }

    public static DAO_Estudiante getDAO() {
        return dao;
    }

    public void insert(Estudiante object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Estudiante.INSERT.getValue());
                pstmt.setString(1, object.getNombre());
                pstmt.setString(2, object.getApe_1());
                pstmt.setString(3, object.getApe_2());
                pstmt.setInt(4, object.getEdad());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_INSERTED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        matching_curso_with_estudiante(object);
    }

    public void update(Estudiante object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Estudiante.UPDATE.getValue());
                pstmt.setString(1, object.getNombre());
                pstmt.setString(2, object.getApe_1());
                pstmt.setString(3, object.getApe_2());
                pstmt.setInt(4, object.getEdad());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_UPDATED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        matching_curso_with_estudiante(object);
        mismatching_curso_with_estudiante(object);
    }

    public Estudiante query(Estudiante p_object) {
        Estudiante object = new Estudiante();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Estudiante.QUERY.getValue());
                pstmt.setInt(2, p_object.getId());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    object.setId(rs.getInt(1));
                    object.setNombre(rs.getString(2));
                    object.setApe_1(rs.getString(3));
                    object.setApe_2(rs.getString(4));
                    object.setEdad(rs.getInt(5));
                }
                if (object.getId() == -1) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_FOUND.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        object.setCursos(
                DAO_Curso.getDAO().query_to_estudiante(object)
        );
        return object;
    }

    public List<Estudiante> list() {
        List<Estudiante> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Estudiante.LIST.getValue());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Estudiante object = new Estudiante();
                    object.setId(rs.getInt(1));
                    object.setNombre(rs.getString(2));
                    object.setApe_1(rs.getString(3));
                    object.setApe_2(rs.getString(4));
                    object.setEdad(rs.getInt(5));
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        list.forEach((e) -> {
            e.setCursos(
                    DAO_Curso.getDAO().query_to_estudiante(e)
            );
        });
        return list;
    }

    public void delete(Estudiante p_object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Estudiante.DELETE.getValue());
                pstmt.setInt(1, p_object.getId());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_DELETED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public void matching_curso_with_estudiante(Estudiante p_object) {
        for (Curso curso : p_object.getCursos()) {
            general_method((CallableStatement pstmt) -> {
                try {
                    pstmt = connection.prepareCall(CRUD_Estudiante.INSERT.getValue());
                    pstmt.setInt(1, p_object.getId());
                    pstmt.setInt(2, curso.getId());
                    if (pstmt.execute()) {
                        throw new RuntimeException(Menssage_Error.OBJECT_NOT_INSERTED.getValue());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex.getMessage());
                }
                return pstmt;
            });
        }
    }

    public void mismatching_curso_with_estudiante(Estudiante p_object) {
        for (Curso curso : p_object.getCursos()) {
            general_method((CallableStatement pstmt) -> {
                try {
                    pstmt = connection.prepareCall(CRUD_Estudiante.DELETE.getValue());
                    pstmt.setInt(1, p_object.getId());
                    pstmt.setInt(2, curso.getId());
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
}
