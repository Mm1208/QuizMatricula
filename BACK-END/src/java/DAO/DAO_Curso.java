package DAO;

import static DAO.Service.connection;
import static DAO.Service.disconnect;
import Logic.Carrera;
import Logic.Ciclo;
import Logic.Curso;
import Logic.Estudiante;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Parameters.CRUD_Curso;
import Parameters.Menssage_Error;
import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class DAO_Curso extends Service {

    private static final DAO_Curso dao = new DAO_Curso();

    public DAO_Curso() {
    }

    public static DAO_Curso getDAO() {
        return dao;
    }

    public void insert(Curso object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.INSERT.getValue());
                pstmt.setString(1, object.getCodigo());
                pstmt.setString(2, object.getNombre());
                pstmt.setInt(3, object.getCreditos());
                pstmt.setInt(4, object.getHora_semana());
                pstmt.setInt(5, object.getAnno());
                pstmt.setInt(6, object.getCiclo().getId());
                pstmt.setInt(7, object.getCarrera().getId());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_INSERTED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public void update(Curso object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.UPDATE.getValue());
                pstmt.setInt(1, object.getId());
                pstmt.setString(2, object.getCodigo());
                pstmt.setString(3, object.getNombre());
                pstmt.setInt(4, object.getCreditos());
                pstmt.setInt(5, object.getHora_semana());
                pstmt.setInt(6, object.getAnno());
                pstmt.setInt(7, object.getCiclo().getId());
                pstmt.setInt(8, object.getCarrera().getId());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_UPDATED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public Curso query(Curso p_object) {
        Curso object = new Curso();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.QUERY.getValue());
                pstmt.setInt(2, p_object.getId());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setCreditos(rs.getInt(4));
                    object.setHora_semana(rs.getInt(5));
                    object.setAnno(rs.getInt(6));
                    object.setCiclo(
                            new Ciclo(rs.getInt(7))
                    );
                    object.setCarrera(
                            new Carrera(rs.getInt(8))
                    );
                }
                if (object.getId() == -1) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_FOUND.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        object.setCiclo(
                DAO_Ciclo.getDAO().query(object.getCiclo())
        );
        object.setCarrera(DAO_Carrera.getDAO().query(
                object.getCarrera())
        );
        return object;
    }

    public ArrayList<Curso> queryCodigo(Curso codigo) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Curso curso = null;
        ArrayList<Curso> detalles = new ArrayList<Curso>();
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(CRUD_Curso.QUERYCODIGO.getValue());
            pstmt.setString(2, codigo.getCodigo());
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso = new Curso(
                        rs.getInt("Curs_id_PK"),
                        rs.getString("Curs_codi"),
                        rs.getString("Curs_nomb"),
                        rs.getInt("Curs_cred"),
                        rs.getInt("Curs_hora_sema"),
                        rs.getInt("Curs_anno"),
                        new Ciclo(rs.getInt("Cicl_id_FK")),
                        new Carrera(rs.getInt("Carr_id_FK"))
                );
                detalles.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (detalles.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return detalles;
    }

    public ArrayList<Curso> queryNombre(Curso nombre) throws GlobalException, NoDataException {

        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Curso curso = null;
        ArrayList<Curso> detalles = new ArrayList<Curso>();
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(CRUD_Curso.QUERYNOMBRE.getValue());
            pstmt.setString(2, nombre.getNombre());
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso = new Curso(
                        rs.getInt("Curs_id_PK"),
                        rs.getString("Curs_codi"),
                        rs.getString("Curs_nomb"),
                        rs.getInt("Curs_cred"),
                        rs.getInt("Curs_hora_sema"),
                        rs.getInt("Curs_anno"),
                        new Ciclo(rs.getInt("Cicl_id_FK")),
                        new Carrera(rs.getInt("Carr_id_FK"))
                );
                detalles.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (detalles.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return detalles;
    }

    public ArrayList<Curso> queryCarreraCurso(Curso nombre) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Curso curso = null;
        ArrayList<Curso> detalles = new ArrayList<Curso>();
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(CRUD_Curso.QUERYCARRERA.getValue());
            pstmt.setInt(2, Integer.parseInt(nombre.getCarrera().getCodigo()));
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso = new Curso(
                        rs.getInt("Curs_id_PK"),
                        rs.getString("Curs_codi"),
                        rs.getString("Curs_nomb"),
                        rs.getInt("Curs_cred"),
                        rs.getInt("Curs_hora_sema"),
                        rs.getInt("Curs_anno"),
                        new Ciclo(rs.getInt("Cicl_id_FK")),
                        new Carrera(rs.getInt("Carr_id_FK"))
                );
                detalles.add(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (detalles.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return detalles;
    }

    public ArrayList<Curso> queryCarrera() {

        ArrayList<Curso> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.LIST.getValue());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Curso object = new Curso();
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setCreditos(rs.getInt(4));
                    object.setHora_semana(rs.getInt(5));
                    object.setAnno(rs.getInt(6));
                    object.setCiclo(
                            new Ciclo(rs.getInt(7))
                    );
                    object.setCarrera(
                            new Carrera(rs.getInt(8))
                    );
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        return list;
    }

    public List<Curso> list() {
        List<Curso> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.LIST.getValue());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Curso object = new Curso();
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setCreditos(rs.getInt(4));
                    object.setHora_semana(rs.getInt(5));
                    object.setAnno(rs.getInt(6));
                    object.setCiclo(
                            new Ciclo(rs.getInt(7))
                    );
                    object.setCarrera(
                            new Carrera(rs.getInt(8))
                    );
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        list.forEach((object) -> {
            object.setCiclo(
                    DAO_Ciclo.getDAO().query(object.getCiclo())
            );
            object.setCarrera(
                    DAO_Carrera.getDAO().query(object.getCarrera())
            );
        });
        return list;
    }

    public void delete(Curso object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.DELETE.getValue());
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

    public List<Curso> query_to_estudiante(Estudiante p_object) {
        List<Curso> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Curso.LIST_TO_ESTUDIANTE.getValue());
                pstmt.setInt(2, p_object.getId());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Curso object = new Curso();
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setCreditos(rs.getInt(4));
                    object.setHora_semana(rs.getInt(5));
                    object.setAnno(rs.getInt(6));
                    object.setCiclo(
                            new Ciclo(rs.getInt(7))
                    );
                    object.setCarrera(
                            new Carrera(rs.getInt(8))
                    );
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        return list;
    }
}
