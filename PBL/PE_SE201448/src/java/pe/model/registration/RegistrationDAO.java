package pe.model.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

public class RegistrationDAO implements Serializable {
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return this.accounts;
    }

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

    public void searchLastName(String searchValue) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. Model connects to Database
            conn = DbUtils.getConnection();

            if (conn != null) {// connection available
                // 2. Model executes query on data in Database
                // 2.1. Create an SQL String
                String sql = "select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "where lastname like ?";

                // 2.2. Load SQL String to memory (Statement Object)
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                // 2.3. Execute Query
                rs = stm.executeQuery();

                // 3. Model load data from Database
                while (rs.next()) {
                    // 4. Model processes and returns results (if necessary)
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO account = new RegistrationDTO(username, password, fullname, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    } // accounts are not available

                    this.accounts.add(account);
                } // end row traversal in ResultSet
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
    }

    public boolean deleteAccount(String username) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            // 1. Model connects to Database
            conn = DbUtils.getConnection();

            if (conn != null) {// connection available
                // 2. Model executes query on data in Database
                // 2.1. Create an SQL String
                String sql = "delete from Registration "
                        + "where username = ?";

                // 2.2. Load SQL String to memory (Statement Object)
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);

                // 2.3. Execute Query
                int affectedRows = stm.executeUpdate();

                // 3. Model load data from Database
                if (affectedRows > 0) {
                    result = true;
                }

                // 4. Model processes and returns results (if necessary)
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public boolean updateAccount(String username, String password, boolean role)throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            // 1. Models connects to database
            conn = DbUtils.getConnection();

            if (conn != null) {// connection available
                // 2. Model executes query on data in database
                // 2.1. Create an sQL String
                String sql = "update Registration "
                        + "set password = ?, isAdmin = ? "
                        + "where username = ?";

                // 2.2. Load SQL string to memory (Statement object)
                stm = conn.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);

                // 2.3. Execute Query
                int affectedRows = stm.executeUpdate();

                // 3. Model loads data from database
                if (affectedRows > 0) {
                    result = true;
                }

                // 4. Model processes data and returns result (if necessary)
            }
        } finally {
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
