package pe.model.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. Model connects to Database
            conn = DbUtils.getConnection();

            if (conn != null) {// connection available
                // 2. Model executes query on data in Database
                // 2.1. Create an SQL String
                String sql = "select username "
                        + "from Registration "
                        + "where username = ? "
                        + "and password = ?";

                // 2.2. Load SQL String to memory (Statement Object)
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                // 2.3. Execute Query
                rs = stm.executeQuery();

                // 3. Model load data from Database
                if (rs.next()) {
                    // If username and password exist
                    result = true;
                }

                // 4. Model processes and returns results (if necessary)
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }
}
