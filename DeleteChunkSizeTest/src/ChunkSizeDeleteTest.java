import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A sample tool to test chunk size .
 */

public class ChunkSizeDeleteTest {
    static Connection conn;

    public static void main(String[] args) throws SQLException {
        // Create JDBC connection

        buildConnection(args[0], args[1], args[2], args[3]);

        int[] chunkSize = {256, 512, 1000, 5000, 50000, 100000, 512000};
        int j = 0;
        while (j < chunkSize.length) {

            System.out.println("the round :" + j);

            // Duplicate the session table.
            duplicateSessionTable();

            // Insert 1M data into it.
            int n = Integer.parseInt(args[4]);
            for (int i = 0; i < n; i++) {
                insertRowData();
            }

            long start = System.currentTimeMillis();
            // Delete rows with chunk size
            deleteRows(chunkSize[j]);
            long end = System.currentTimeMillis();
            System.out.println("The chunck Size " + chunkSize[j] + " takes time of " +
                    (end - start) + "ms");

            // Drop duplicated table
            dropTable();
            j++;

        }

        // Close the connection
        conn.close();


    }

    /***
     * Create JDBC connection with SQL drivers
     */
    public static void buildConnection(String driver, String url, String username, String pwd) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, pwd);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /***
     *  Duplicate the session table.
     * @throws SQLException Table can not be created.
     */
    public static void duplicateSessionTable() throws SQLException {
        Statement st = null;
        String sqlQuery = "CREATE TABLE  IDN_AUTH_SESSION_STORE_DUPLICATE AS SELECT * FROM  IDN_AUTH_SESSION_STORE;";
        try {
            st = conn.createStatement();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        st.execute(sqlQuery);

    }

    /***
     *  Insert data to the table by duplicating.
     * @throws SQLException
     */
    public static void insertRowData() throws SQLException {
        Statement st = null;
        String sqlQuery = "INSERT INTO IDN_AUTH_SESSION_STORE_DUPLICATE(SESSION_ID, SESSION_TYPE, OPERATION, SESSION_OBJECT, TIME_CREATED, TENANT_ID)  SELECT * FROM  IDN_AUTH_SESSION_STORE;";
        try {
            st = conn.createStatement();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        st.execute(sqlQuery);

    }

    /***
     * Delete rows in the duplicate table.
     * @param LIMIT chunk size
     * @throws SQLException
     */
    public static void deleteRows(int LIMIT) throws SQLException {
        Statement deleteSt = null;
        String sqlDeleteQuery = "DELETE FROM IDN_AUTH_SESSION_STORE_DUPLICATE WHERE  TENANT_ID=-1 LIMIT " + LIMIT + ";";
        try {
            deleteSt = conn.createStatement();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        deleteSt.execute(sqlDeleteQuery);
    }

    /***
     * Finally drop duplicated Table.
     * @throws SQLException
     */
    public static void dropTable() throws SQLException {
        Statement deleteSt = null;
        String sqlDeleteQuery = "DROP TABLE IDN_AUTH_SESSION_STORE_DUPLICATE;";
        try {
            deleteSt = conn.createStatement();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        deleteSt.execute(sqlDeleteQuery);
    }


}
