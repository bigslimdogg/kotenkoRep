package mvc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import mvc.model.models.Hub;
import mvc.model.models.Switch;
import mvc.model.pe_model.PathElement;

import java.sql.*;

/**
 * Created by Nick on 14.05.2015.
 */
public class DBController {

    private final String URL = "jdbc:mysql://localhost:3306/kotenkonetwork";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public void createElem(Comands comand) {

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            if(comand == null)
                throw new NullPointerException("Пустая команда");
            switch (comand){
                case create_cable:
                    statement.execute("INSERT INTO pathelement VALUE()");
                    statement.execute("INSERT INTO cable(id_cable) VALUE(last_insert_id()); ");
                    break;
                case create_firewall:
                    statement.execute("INSERT INTO pathelement VALUE()");
                    statement.execute("INSERT INTO firewall(id_firewall) VALUE(last_insert_id()); ");
                    break;
                case create_hub:
                    statement.execute("INSERT INTO pathelement VALUE()");
                    statement.execute("INSERT INTO hub(id_hub)  VALUE(last_insert_id()); ");
                    break;
                case create_pc:
                    statement.execute("INSERT INTO pathelement VALUE()");
                    statement.execute("INSERT INTO pc(id_pc) VALUE(last_insert_id()); ");
                    break;
                case create_route:
                    statement.execute("INSERT INTO pathelement VALUE()");
                    statement.execute("INSERT INTO route(id_route,turned_on) VALUES(last_insert_id(),TRUE ); ");
                    break;
                case create_switch:
                    statement.execute("INSERT INTO pathelement VALUE()");
                    statement.execute("INSERT INTO switch(id_switch) VALUE(last_insert_id()); ");
                    break;
                default :
                    throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void createData(Comands comand, String attribute){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(comand == null){
                System.out.println("Пустая команда");
            }
            if(attribute == null){
                System.out.println("Вы не ввели название");
            }
            switch (comand){
                case add_cable_type:
                    preparedStatement = connection.prepareStatement("INSERT INTO cable_type(type_name) VALUE (?)");
                    preparedStatement.setString(1, attribute);
                    preparedStatement.execute();
                    break;
                case add_ips:
                    preparedStatement = connection.prepareStatement("INSERT INTO ip(ip_name) VALUE (?)");
                    preparedStatement.setString(1, attribute);
                    preparedStatement.execute();
                    break;
                case add_wrong_ips:
                    preparedStatement = connection.prepareStatement("INSERT INTO wrong_ip(ip_name) VALUE (?)");
                    preparedStatement.setString(1, attribute);
                    preparedStatement.execute();
                    break;
                default: throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void executeElem(Comands comand, PathElement model, String attribute){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(comand == null)
                throw new NullPointerException("Пустая команда");
            if(attribute == null)
                throw new NullPointerException("Пустое значение");
            if(model == null)
                throw new NullPointerException("Элемент не найден");
            switch (comand){
                case set_delay:
                    preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
                    preparedStatement.setDouble(1,Double.valueOf(attribute));
                    preparedStatement.setInt(2,model.getID());
                    preparedStatement.execute();
                    break;
                case set_info:
                    preparedStatement = connection.prepareStatement("UPDATE pathelement SET info = ? WHERE id_elem = ? ;");
                    preparedStatement.setString(1,attribute);
                    preparedStatement.setInt(2,model.getID());
                    preparedStatement.execute();
                    break;
                case set_ip:
                /*
                сюда не забыть проверку на то есть ли в таблице ip адрес совпадающий с тем что хочет ввести пользователь
                и если нет спросить его о том добавить ли в базу такой ip после чего его можно установить в pathelement
                */
                    preparedStatement = connection.prepareStatement("UPDATE pathelement SET ip = ? WHERE id_elem = ? AND ip.ip_name = ?;");
                    preparedStatement.setInt(2,model.getID());
                    preparedStatement.setString(3,attribute);
                    preparedStatement.execute();
                    break;
                case set_price:
                    preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
                    preparedStatement.setDouble(1,Double.valueOf(attribute));
                    preparedStatement.setInt(2,model.getID());
                    preparedStatement.execute();
                    break;
                case set_notAllowedIp:
                //разобравшись с предыдущим
                    break;
                case set_turnOn:
                    preparedStatement = connection.prepareStatement("UPDATE route SET turned_on = ? WHERE id_route = ?");
                    preparedStatement.setBoolean(1,Boolean.valueOf(attribute));
                    preparedStatement.setInt(2, model.getID());
                    break;
                case set_typeOfCable:
                //разобравшись с предыдущим
                    break;
                case set_unitAmount:
                    if(model instanceof Hub){
                        preparedStatement = connection.prepareStatement("UPDATE hub SET units = ? WHERE id_route = ?");
                        preparedStatement.setInt(1, Integer.valueOf(attribute));
                        preparedStatement.setInt(2, model.getID());
                    }
                    if(model instanceof Switch){
                        preparedStatement = connection.prepareStatement("UPDATE Switch SET units = ? WHERE id_route = ?");
                        preparedStatement.setInt(1, Integer.valueOf(attribute));
                        preparedStatement.setInt(2, model.getID());
                    }
                    break;
                default: throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeData(Comands comand, String attribute, int dataId){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(comand == null)
                throw new NullPointerException("Пустая команда");
            if(attribute == null)
                throw new NullPointerException("Пустое значение");
            if(dataId == 0)
                throw new NullPointerException("Не найден элемент списка");
            switch (comand){
                case update_cable_type:
                    preparedStatement = connection.prepareStatement("UPDATE cable_type SET type_name = ? WHERE id_cable_type = ?;");
                    preparedStatement.setString(1,attribute);
                    preparedStatement.setInt(2,dataId);
                    break;
                case update_ips:
                    preparedStatement = connection.prepareStatement("UPDATE ip SET ip_name = ? WHERE id_cable_type = ?;");
                    preparedStatement.setString(1,attribute);
                    preparedStatement.setInt(2,dataId);
                    break;
                case update_wrong_ips:
                    preparedStatement = connection.prepareStatement("UPDATE wrong_ip SET ip_name = ? WHERE id_cable_type = ?;");
                    preparedStatement.setString(1,attribute);
                    preparedStatement.setInt(2,dataId);
                    break;
                default: throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteElement(Comands comand, PathElement model){

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(comand == null)
                throw new NullPointerException("Пустая команда");
            if(model == null)
                throw new NullPointerException("Элемент не найден");
            switch (comand){
                case delete_cable:
                    preparedStatement = connection.prepareStatement("DELETE FROM cable WHERE id_cable = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement.execute();
                    break;
                case delete_firewall:
                    preparedStatement = connection.prepareStatement("DELETE FROM firewall WHERE id_firewall = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement.execute();
                    break;
                case delete_hub:
                    preparedStatement = connection.prepareStatement("DELETE FROM hub WHERE id_hub = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement.execute();
                    break;
                case delete_pc:
                    preparedStatement = connection.prepareStatement("DELETE FROM pc WHERE id_pc = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement.execute();
                    break;
                case delete_route:
                    preparedStatement = connection.prepareStatement("DELETE FROM route WHERE id_route = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement.execute();
                    break;
                case delete_switch:
                    preparedStatement = connection.prepareStatement("DELETE FROM switch WHERE id_switch = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
                    preparedStatement.setInt(1, model.getID());
                    preparedStatement.execute();
                    break;
                default :
                    throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteData(Comands comand, int dataId){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(comand == null)
                throw new NullPointerException("Пустая команда");
            if(dataId == 0)
                throw new NullPointerException("Не найден элемент списка");
            switch (comand){
                case delete_cable_type:
                    preparedStatement = connection.prepareStatement("DELETE FROM cable_type WHERE id_cable_type = ?");
                    preparedStatement.setInt(1,dataId);
                    preparedStatement.execute();
                    break;
                case delete_ips:
                    preparedStatement = connection.prepareStatement("DELETE FROM ip WHERE id_ip = ?");
                    preparedStatement.setInt(1,dataId);
                    preparedStatement.execute();
                    break;
                case delete_wrong_ips:
                    preparedStatement = connection.prepareStatement("DELETE FROM wrong_ip WHERE id_wrong_ip = ?");
                    preparedStatement.setInt(1,dataId);
                    preparedStatement.execute();
                    break;
                default: throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeConnectionBetweenElements(Comands comand, PathElement model1, PathElement model2){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if(comand == null)
                throw new NullPointerException("Пустая команда");
            if(model1 == null)
                throw new NullPointerException("Элемент не найден");
            if(model2 == null)
                throw new NullPointerException("Элемент не найден");
            switch (comand){
                case add_connect:
                    preparedStatement = connection.prepareStatement("INSERT INTO connections(first_elem, second_elem) VALUES (?,?)");
                    preparedStatement.setInt(1,model1.getID());
                    preparedStatement.setInt(2, model2.getID());
                    preparedStatement.execute();
                    break;
                case delete_connect:
                    preparedStatement = connection.prepareStatement("DELETE FROM connections WHERE first_elem = ? AND second_elem =?");
                    preparedStatement.setInt(1,model1.getID());
                    preparedStatement.setInt(2, model2.getID());
                    preparedStatement.execute();
                    break;
                default:throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
