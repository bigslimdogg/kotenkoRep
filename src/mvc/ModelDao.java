package mvc;

import mvc.model.models.*;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.List;

/**
 * Created by Nick on 16.05.2015.
 */
public class ModelDao {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    ModelDao(Connection connection){
        this.connection = connection;
    }

    public void createPc()throws SQLException {
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO pc(id_pc) VALUE(last_insert_id()); ");
    }
    public void createCable()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO cable(id_cable) VALUE(last_insert_id()); ");
    }
    public void createHub()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO hub(id_hub)  VALUE(last_insert_id()); ");
    }
    public void createFirewall()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO firewall(id_firewall) VALUE(last_insert_id()); ");
    }
    public void createSwitch()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO switch(id_switch) VALUE(last_insert_id()); ");
    }
    public void createRoute()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO route(id_route,turned_on) VALUES(last_insert_id(),TRUE ); ");
    }

    public void deletePc(PathElement model)throws SQLException{
        preparedStatement = connection.prepareStatement("DELETE FROM pc WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteCable(PathElement model)throws SQLException{
        preparedStatement = connection.prepareStatement("DELETE FROM cable WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteFirewall(PathElement model)throws SQLException{
        preparedStatement = connection.prepareStatement("DELETE FROM firewall WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteHub(PathElement model)throws SQLException{
        preparedStatement = connection.prepareStatement("DELETE FROM hub WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteRoute(PathElement model)throws SQLException{
        preparedStatement = connection.prepareStatement("DELETE FROM route WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteSwitch(PathElement model)throws SQLException{
        preparedStatement = connection.prepareStatement("DELETE FROM switch WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void updateIp(PC model, String attribute)throws SQLException {
                        /*
                сюда не забыть проверку на то есть ли в таблице ip адрес совпадающий с тем что хочет ввести пользователь
                и если нет спросить его о том добавить ли в базу такой ip после чего его можно установить в pathelement
                */
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET ip = ? WHERE id_elem = ? AND ip.ip_name = ?;");
        preparedStatement.setInt(2, model.getID());
        preparedStatement.setString(3, attribute);
        preparedStatement.execute();
    }

    public void updateInfo(PathElement model, String attribute)throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET info = ? WHERE id_elem = ? ;");
        preparedStatement.setString(1, attribute);
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }

    public void updateDelay(PathElement model, double attribute)throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
        preparedStatement.setDouble(1, Double.valueOf(attribute));
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }

    public void updatePrice(PathElement model , double attribute)throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
        preparedStatement.setDouble(1,Double.valueOf(attribute));
        preparedStatement.setInt(2,model.getID());
        preparedStatement.execute();
    }

    public void updateNotAllowedIp(PathElement model, String attribute)throws SQLException{
        if(model instanceof Firewall){
            //разобравшись с предыдущим
        }
    }
    public void updateTurnOn(PathElement model, String attribute)throws SQLException{
        if(model instanceof Route){
            preparedStatement = connection.prepareStatement("UPDATE route SET turned_on = ? WHERE id_route = ?");
            preparedStatement.setBoolean(1, Boolean.valueOf(attribute));
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }
    public void updateUnitAmount(PathElement model, int attribute)throws SQLException{
        if(model instanceof Hub){
            preparedStatement = connection.prepareStatement("UPDATE hub SET units = ? WHERE id_route = ?");
            preparedStatement.setInt(1, Integer.valueOf(attribute));
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
        if(model instanceof Switch){
            preparedStatement = connection.prepareStatement("UPDATE Switch SET units = ? WHERE id_route = ?");
            preparedStatement.setInt(1, Integer.valueOf(attribute));
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }
    public Cable readCable(int key, Network net)throws SQLException{
        preparedStatement = connection.prepareStatement("select id_elem, price,info, cable_type.type_name  from pathelement join cable on id_cable = ? join cable_type on id_cable = id_cable_type");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Cable cable = new Cable(rs.getInt("id_elem"),rs.getDouble("price"), rs.getString("info"), TypeOfCable.valueOf(rs.getString("type_name")), net );
        return cable;
    }
    public Firewall readFirewall(int key, Network net) throws SQLException, UnknownHostException {
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay, wrong_ip.ip_name" +
                "  from pathelement join firewall on id_firewall = ? join wrong_ip on firewall.wrong_ip = wrong_ip.id_wrong_ip JOIN ip ON pathelement.ip = ip.id_ip;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Firewall firewall = new Firewall(rs.getInt("id_elem"),rs.getDouble("delay"), rs.getString("ip_name"),rs.getString("Info"), rs.getDouble("price"),net);
        firewall.setNotAllowedIP(rs.getString("wrong_ip"));
        return firewall;
    }
    public PC readPc(int key, Network net) throws SQLException, UnknownHostException {
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay  from pathelement join pc on id_pc = ? " +
                "JOIN ip ON pathelement.ip = ip.id_ip");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        PC pc= new PC(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), net);
        return pc;
    }
    public Hub readHub(int key, Network net)throws SQLException{
        preparedStatement = connection.prepareStatement("select id_elem, price,info, hub.units from pathelement join hub on id_hub = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Hub hub = new Hub(rs.getInt("id_elem"),rs.getDouble("price"), rs.getString("info"), rs.getInt("units"), net);
        return hub;
    }
    public Route readRoute(int key, Network net) throws SQLException, UnknownHostException {
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay, route.turned_on " +
                "  from pathelement join route on id_route = ? JOIN ip ON pathelement.ip = ip.id_ip;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Route route = new Route(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), net);
        if(rs.getBoolean("turned_on") == true)
            route.turnON();
        return route;
    }
    public Switch readSwitch(int key, Network net) throws SQLException, UnknownHostException {
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay, switch.units" +
                "  from pathelement join switch on id_switch = ? JOIN ip ON pathelement.ip = ip.id_ip;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        Switch sw = new Switch(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"),rs.getInt("units"), net);
        return sw;
    }



}
