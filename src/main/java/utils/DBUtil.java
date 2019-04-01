package utils;

import com.ibm.db2.jcc.DB2BaseDataSource;
import com.ibm.db2.jcc.DB2Driver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private DBUtil() {
    }


    static {
        com.ibm.db2.jcc.DB2Driver driver = new DB2Driver();
        try {
            DriverManager.registerDriver(driver);
        }
        catch (SQLException e) {
            throw new Error("Laden des Datenbanktreiber nicht möglich");
        }
    }



    public static Connection getConnection(String database) throws SQLException {
        final String url = "jdbc:db2:" + database;
        System.out.println(url);
        //return DriverManager.getConnection(url, "dbp04", "soo3dood");
        return DriverManager.getConnection(url);
    }

    public static Connection getExternalConnection(String database) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "dbp04");
        properties.setProperty("password", "soo3dood");

        final String url = "jdbc:db2://atlas.is.inf.uni-due.de:50004/" + database + ":currentSchema=dbp04;";
        System.out.println(url);
        Connection connection = DriverManager.getConnection(url, properties);
        return connection;
    }

    public static boolean checkDatabaseExistsExternal(String database) {
        // Nur für Demozwecke!
        boolean exists = false;

        try (Connection connection = getExternalConnection(database)) {
            exists = true;
        }
        catch (SQLException e) {
            exists = false;
            e.printStackTrace();
        }

        return exists;
    }


    public static boolean checkDatabaseExists(String database) {
        // Nur für Demozwecke!
        boolean exists = false;

        try (Connection connection = getExternalConnection(database)) {
            exists = true;
        }
        catch (SQLException e) {
            exists = false;
            e.printStackTrace();
        }

        return exists;
    }



}
