package mvc;

import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nick on 16.05.2015.
 */
public class ConnectionDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    ConnectionDao(Connection connection){
        this.connection = connection;
    }

    public void createConnection(ActiveElement el1, PathElement el2) throws Exception {
        if(el1 == null || el2 == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("INSERT INTO connections(first_elem, second_elem) VALUES (?,?)");
        preparedStatement.setInt(1, el1.getID());
        preparedStatement.setInt(2, el2.getID());
        preparedStatement.execute();
        el1.connect(el2);
    }
    public void deleteConections(ActiveElement el1, PathElement el2) throws SQLException {
        if(el2 == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM connections WHERE second_elem =?");
        preparedStatement.setInt(1,el2.getID());
        preparedStatement.execute();
        el1.disConnect(el2);
    }

}
