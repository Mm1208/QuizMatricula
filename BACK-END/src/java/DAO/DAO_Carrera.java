package DAO;

import static DAO.Service.connection;
import static DAO.Service.disconnect;
import Logic.Carrera;
import Logic.Ciclo;
import Logic.Carrera;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Parameters.CRUD_Carrera;
import Parameters.Menssage_Error;
import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class DAO_Carrera extends Service {

    private static final DAO_Carrera dao = new DAO_Carrera();

    public DAO_Carrera() {
    }

    public static DAO_Carrera getDAO() {
        return dao;
    }

    public void insert(Carrera object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Carrera.INSERT.getValue());
                pstmt.setString(1, object.getCodigo());
                pstmt.setString(2, object.getNombre());
                pstmt.setString(3, object.getTitulo());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_INSERTED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public void update(Carrera object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Carrera.UPDATE.getValue());
                pstmt.setInt(1, object.getId());
                pstmt.setString(2, object.getCodigo());
                pstmt.setString(3, object.getNombre());
                pstmt.setString(4, object.getTitulo());
                if (pstmt.execute()) {
                    throw new RuntimeException(Menssage_Error.OBJECT_NOT_UPDATED.getValue());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
    }

    public Carrera query(Carrera p_object) {
        Carrera object = new Carrera();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Carrera.QUERY.getValue());
                pstmt.setInt(2, p_object.getId());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setTitulo(rs.getString(4));      
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

      public ArrayList<Carrera> queryCodigo(Carrera codigo) throws GlobalException, NoDataException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Carrera carrera = null;
        ArrayList<Carrera> detalles = new ArrayList<Carrera>();
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(CRUD_Carrera.QUERYCODIGO.getValue());
            pstmt.setString(2, codigo.getCodigo());
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = new Carrera(rs.getInt("Carr_id_PK"), rs.getString("Carr_codi"), rs.getString("Carr_nomb"), rs.getString("Carr_titu"));
                detalles.add(carrera);
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

    public ArrayList<Carrera> queryNombre(Carrera nombre) throws GlobalException, NoDataException {
           System.out.print("FFFFFFFFFFF");
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Carrera carrera = null;
        ArrayList<Carrera> detalles = new ArrayList<Carrera>();
        CallableStatement pstmt = null;
        try {
            pstmt = connection.prepareCall(CRUD_Carrera.QUERYNOMBRE.getValue());
            pstmt.setString(2, nombre.getNombre());
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = new Carrera(rs.getInt("Carr_id_PK"), rs.getString("Carr_codi"), rs.getString("Carr_nomb"), rs.getString("Carr_titu"));
                detalles.add(carrera);
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

    public ArrayList<Carrera> queryCarrera() {
        ArrayList<Carrera> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Carrera.LIST.getValue());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Carrera object = new Carrera();
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setTitulo(rs.getString(4));
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        return list;
    }
    public List<Carrera> list() {
        List<Carrera> list = new ArrayList();
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Carrera.LIST.getValue());
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                ResultSet rs = (ResultSet) pstmt.getObject(1);
                while (rs.next()) {
                    Carrera object = new Carrera();
                    object.setId(rs.getInt(1));
                    object.setCodigo(rs.getString(2));
                    object.setNombre(rs.getString(3));
                    object.setTitulo(rs.getString(4));
                    list.add(object);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return pstmt;
        });
        return list;
    }

    public void delete(Carrera object) {
        general_method((CallableStatement pstmt) -> {
            try {
                pstmt = connection.prepareCall(CRUD_Carrera.DELETE.getValue());
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
