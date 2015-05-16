package mvc;

import mvc.model.abstract_model.PassiveElement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nick on 15.05.2015.
 */
public class DaoFactory {


    private final String URL = "jdbc:mysql://localhost:3306/kotenkonetwork";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    ModelDao getModelDao(Connection connection){
        return new ModelDao(connection);
    }
    

    public DaoFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
