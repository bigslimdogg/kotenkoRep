package mvc;

import mvc.model.models.*;
import mvc.model.my_exceptions.DaoException;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

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

    public int createPc()throws SQLException {
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO pc(id_pc) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt("id_pc");
    }
    public int createCable()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO cable(id_cable) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt("id_cable");
    }
    public int createHub()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO hub(id_hub)  VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt("id_hub");
    }
    public int createFirewall()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO firewall(id_firewall) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt("id_firewall");
    }
    public int createSwitch()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO switch(id_switch) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt("id_switch");
    }
    public int createRoute()throws SQLException{
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO route(id_route,turned_on) VALUES(last_insert_id(),TRUE ); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt("id_route");
    }

    public void deletePc(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM pc WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteCable(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM cable WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteFirewall(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM firewall WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteHub(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM hub WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteRoute(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM route WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteSwitch(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM switch WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void updateIp(PathElement model, String attribute) throws SQLException, DaoException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("SELECT id_ip FROM ip WHERE ip_name = ?");
        preparedStatement.setString(1,attribute);
        ResultSet rs = preparedStatement.executeQuery();
        int ipNumber = rs.getInt("id_ip");
        if(rs.wasNull()){
            throw new DaoException();
        }
        else {
            preparedStatement = connection.prepareStatement("UPDATE pathelement SET ip = ? WHERE id_elem = ?");
            preparedStatement.setInt(1, ipNumber);
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }

    public void updateInfo(PathElement model, String attribute)throws SQLException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET info = ? WHERE id_elem = ? ;");
        preparedStatement.setString(1, attribute);
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }

    public void updateDelay(PathElement model, double attribute)throws SQLException {
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
        preparedStatement.setDouble(1, Double.valueOf(attribute));
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }

    public void updatePrice(PathElement model , double attribute)throws SQLException {
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
        preparedStatement.setDouble(1, Double.valueOf(attribute));
        preparedStatement.setInt(2,model.getID());
        preparedStatement.execute();
    }

    public void updateNotAllowedIp(PathElement model, String attribute) throws SQLException, DaoException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("SELECT id_wrong_ip FROM wrong_ip WHERE ip_name = ?");
        preparedStatement.setString(1, attribute);
        ResultSet rs = preparedStatement.executeQuery();
        int ipNumber = rs.getInt("id_wrong_ip");
        if(rs.wasNull()){
            throw new DaoException();
        }
        else {
            preparedStatement = connection.prepareStatement("UPDATE firewall SET wrong_ip = ? WHERE id_firewall = ?");
            preparedStatement.setInt(1, ipNumber);
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }
    public void updateTurnOn(PathElement model, String attribute)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE route SET turned_on = ? WHERE id_route = ?");
        preparedStatement.setBoolean(1, Boolean.valueOf(attribute));
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }
    public void updateUnitAmount(PathElement model, int attribute)throws SQLException{
        if(model == null)
            throw new NullPointerException();
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
    public void updateTypeOfCable(PathElement model, String attribute) throws SQLException, DaoException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("SELECT id_cable_type FROM cable_type WHERE type_name = ?");
        preparedStatement.setString(1,attribute);
        ResultSet rs = preparedStatement.executeQuery();
        int ipNumber = rs.getInt("id_cable_type");
        if(rs.wasNull()){
            throw new DaoException();
        }
        else{
            preparedStatement = connection.prepareStatement("UPDATE cable SET cable_type = ? WHERE id_cable = ?");
            preparedStatement.setInt(1, ipNumber);
            preparedStatement.setInt(2,model.getID());
            preparedStatement.execute();
        }
    }
    public Cable readCable(int key, Network net)throws SQLException{
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_elem, price,info, cable_type.type_name  from pathelement join cable on id_cable = ? join cable_type on id_cable = id_cable_type");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        try {
            Cable cable = new Cable(rs.getInt("id_elem"), rs.getDouble("price"), rs.getString("info"), TypeOfCable.valueOf(rs.getString("type_name")), net);
            return cable;
        }catch (NullPointerException e){
            Cable cable = new Cable();
            net.addElements(cable);
            cable.setID(rs.getInt("id_elem"));
            return cable;
        }
    }
    public Firewall readFirewall(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay " +
                "  from pathelement join firewall on id_firewall = ? JOIN ip ON pathelement.ip = ip.id_ip;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        try {
            Firewall firewall = new Firewall(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("Info"), rs.getDouble("price"), net);
            preparedStatement = connection.prepareStatement("SELECT wrong_ip.ip_name FROM wrong_ip WHERE wrong_ip.id_wrong_ip = ?");
            preparedStatement.setInt(1, key);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                firewall.setNotAllowedIP(rs.getString("wrong_ip"));
            }
            return firewall;
        }catch (NullPointerException e){
            Firewall firewall = new Firewall();
            net.addElements(firewall);
            firewall.setID(rs.getInt("id_elem"));
            return firewall;
        }
    }
    public PC readPc(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay  from pathelement join pc on id_pc = ? " +
                "JOIN ip ON pathelement.ip = ip.id_ip");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        try {
            PC pc = new PC(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), net);
            return pc;
        }catch (NullPointerException e){
            PC pc = new PC();
            net.addElements(pc);
            pc.setID(rs.getInt("id_elem"));
            return pc;
        }
    }
    public Hub readHub(int key, Network net)throws SQLException{
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_elem, price,info, hub.units from pathelement join hub on id_hub = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        try {
            Hub hub = new Hub(rs.getInt("id_elem"), rs.getDouble("price"), rs.getString("info"), rs.getInt("units"), net);
            return hub;
        }catch (NullPointerException e){
            Hub hub = new Hub();
            net.addElements(hub);
            hub.setID(rs.getInt("id_elem"));
            return hub;
        }
    }
    public Route readRoute(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay, route.turned_on " +
                "  from pathelement join route on id_route = ? JOIN ip ON pathelement.ip = ip.id_ip;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        try {
            Route route = new Route(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), net);
            if (rs.getBoolean("turned_on") == true)
                route.turnON();
            return route;
        }catch (NullPointerException e){
            Route route = new Route();
            net.addElements(route);
            route.setID(rs.getInt("id_elem"));
            return route;
        }
    }
    public Switch readSwitch(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_elem, price,info, ip.ip_name, delay, switch.units" +
                "  from pathelement join switch on id_switch = ? JOIN ip ON pathelement.ip = ip.id_ip;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        try {
            Switch sw = new Switch(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), rs.getInt("units"), net);
            return sw;
        }catch (NullPointerException e){
            Switch sw = new Switch();
            net.addElements(sw);
            sw.setID(rs.getInt("id_elem"));
            return sw;
        }
    }

    public void readAll(Network net) throws SQLException, UnknownHostException {
        ResultSet rs = statement.executeQuery("SELECT id_cable FROM cable");
        while(rs.next()){
            readCable(rs.getInt("id_cable"),net);
        }
        rs = statement.executeQuery("SELECT id_firewall FROM firewall");
        while(rs.next()){
            readFirewall(rs.getInt("id_firewall"), net);
        }
        rs = statement.executeQuery("SELECT id_pc FROM pc");
        while(rs.next()){
            readPc(rs.getInt("id_pc"), net);
        }
        rs = statement.executeQuery("SELECT id_hub FROM hub");
        while(rs.next()){
            readHub(rs.getInt("id_hub"),net);
        }
        rs = statement.executeQuery("SELECT id_route FROM route");
        while(rs.next()){
            readRoute(rs.getInt("id_route"),net);
        }
        rs = statement.executeQuery("SELECT id_switch FROM switch");
        while(rs.next()){
            readSwitch(rs.getInt("id_switch"),net);
        }
    }


}
