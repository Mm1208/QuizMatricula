
package DAO;

import Parameters.Menssage_Error;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class Service {
     protected static Connection connection;

    public Service() {
        connection = null;
    }

    protected static void connection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "admin");
    }

    protected static void disconnect() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    protected void general_method(Function<CallableStatement, CallableStatement> function) {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(Menssage_Error.GLOBAL_EXEPTION.getValue());
        } catch (SQLException e) {
            throw new RuntimeException(Menssage_Error.NO_DATA_EXEPTION.getValue());
        }
        CallableStatement pstmt = null;
        try {
            pstmt = function.apply(pstmt);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new RuntimeException(Menssage_Error.INVALID_STATUTES.getValue());
            }
        }
    }
}
