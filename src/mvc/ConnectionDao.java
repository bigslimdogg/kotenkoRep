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

    public void createConnection(PathElement el1, PathElement el2) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO connections(first_elem, second_elem) VALUES (?,?)");
        preparedStatement.setInt(1, el1.getID());
        preparedStatement.setInt(2, el2.getID());
        preparedStatement.execute();
    }
    public void deleteConections(PathElement el1, PathElement el2) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM connections WHERE first_elem = ? AND second_elem =?");
        preparedStatement.setInt(1,el1.getID());
        preparedStatement.setInt(2, el2.getID());
        preparedStatement.execute();
    }
    public void readConnections(ActiveElement model, Network net) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT second_elem FROM connections WHERE first_elem = ?");
        preparedStatement.setInt(1, model.getID());
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        for(PathElement elem : net.getPathElements().keySet()){
            if(elem.getID() == rs.getInt("second_elem"))
                model.connect(elem);
        }


    }
}
