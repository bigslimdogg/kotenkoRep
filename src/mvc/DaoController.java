package mvc;

import mvc.model.abstract_model.ActiveElement;
import mvc.model.models.*;
import mvc.model.my_exceptions.DaoException;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.UnknownHostException;
import java.sql.*;

/**
 * Created by Nick on 14.05.2015.
 */
public class DaoController {

    DaoFactory factory = new DaoFactory();

    public void createElem(Comands comand, Network net) throws SQLException, UnknownHostException {
        ModelDao modelDao = factory.getModelDao(factory.getConnection());
        if(comand == null)
            throw new NullPointerException("Пустая команда");
        switch (comand){
            case create_cable:
                modelDao.readCable(modelDao.createCable(), net);
                break;
            case create_firewall:
                modelDao.readFirewall(modelDao.createFirewall(), net);
                break;
            case create_hub:
                modelDao.readHub(modelDao.createHub(), net);
                break;
            case create_pc:
                modelDao.readPc(modelDao.createPc(), net);
                break;
            case create_route:
                modelDao.readRoute(modelDao.createRoute(), net);
                break;
            case create_switch:
                modelDao.readSwitch(modelDao.createSwitch(), net);
                break;
            default :
                throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
    }

    public void createData(Comands comand, String attribute) throws SQLException {
        DataDao dataDao = factory.getDataDao(factory.getConnection());
        if(comand == null) {
            System.out.println("Пустая команда");
        }
        if(attribute == null){
            System.out.println("Вы не ввели название");
        }
        switch (comand){
            case add_cable_type:
                dataDao.addTypeOfCable(attribute);
                break;
            case add_ips:
                dataDao.addIp(attribute);
                break;
            case add_wrong_ips:
                dataDao.addWrongIp(attribute);
                break;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
    }


    public void executeElem(Comands comand, PathElement model, String attribute) throws SQLException {
        ModelDao modelDao = factory.getModelDao(factory.getConnection());
        if(comand == null)
            throw new NullPointerException("Пустая команда");
        if(attribute == null)
            throw new NullPointerException("Пустое значение");
        if(model == null)
            throw new NullPointerException("Элемент не найден");
        switch (comand) {
            case set_delay:
                modelDao.updateDelay(model,Integer.valueOf(attribute));
                model.setDelay(Integer.valueOf(attribute));
                break;
            case set_info:
                modelDao.updateInfo(model,attribute);
                model.setInfo(attribute);
                break;
            case set_ip:
                try{
                    modelDao.updateIp(model,attribute);
                    model.setIP(attribute);
                } catch (DaoException e) {
                    System.out.println("Такого ip нет в базе");
                    break;
                }
                break;
            case set_price:
                modelDao.updatePrice(model,Double.valueOf(attribute));
                model.setPrice(Double.valueOf(attribute));
                break;
            case set_notAllowedIp:
                if(model instanceof Firewall) {
                    Firewall el = (Firewall)model;
                    try {
                        modelDao.updateIp(el, attribute);
                        el.setNotAllowedIP(attribute);
                    } catch (DaoException e) {
                        System.out.println("Такого ip нет в базе");
                        break;
                    }
                    break;
                }
                else
                    break;
            case set_turnOn:
                if(model instanceof Route){
                    Route el = (Route)model;
                    modelDao.updateTurnOn(el,attribute);
                    if(Boolean.valueOf(attribute) == true)
                        el.turnON();
                    else
                        el.turnOFF();
                    break;
                }
                else
                    break;
            case set_typeOfCable:
                if(model instanceof Cable) {
                    Cable el = (Cable)model;
                    try {
                        modelDao.updateIp(el, attribute);
                        el.setType(TypeOfCable.valueOf(attribute));
                    } catch (DaoException e) {
                        System.out.println("Такого типа нет в базе");
                        break;
                    }
                    break;
                }
                else
                    break;
            case set_unitAmount:
                if(model instanceof Switch) {
                    Switch el = (Switch)model;
                    modelDao.updateUnitAmount(el,Integer.valueOf(attribute));
                    el.setUnitAmount(Integer.valueOf(attribute));
                    break;
                }
                if(model instanceof Hub){
                    Hub el = (Hub)model;
                    modelDao.updateUnitAmount(el,Integer.valueOf(attribute));
                    el.setUnitAmount(Integer.valueOf(attribute));
                }
                else
                    break;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + comand);
        }
    }



    public void deleteElement(Comands comand, PathElement model, Network net) throws SQLException {
        ModelDao modelDao = factory.getModelDao(factory.getConnection());
        if(comand == null)
            throw new NullPointerException("Пустая команда");
        if(model == null)
            throw new NullPointerException("Элемент не найден");
        switch (comand){
            case delete_cable:
                modelDao.deleteCable(model);
                net.deleteElements(model);
                break;
            case delete_firewall:
                modelDao.deleteFirewall(model);
                net.deleteElements(model);
                break;
            case delete_hub:
                modelDao.deleteHub(model);
                net.deleteElements(model);
                break;
            case delete_pc:
                modelDao.deletePc(model);
                net.deleteElements(model);
                break;
            case delete_route:
                modelDao.deleteRoute(model);
                net.deleteElements(model);
                break;
            case delete_switch:
                modelDao.deleteSwitch(model);
                net.deleteElements(model);
                break;
            default :
                throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
    }
    public void deleteData(Comands comand, String attribute) throws SQLException {
        DataDao dataDao = factory.getDataDao(factory.getConnection());
        if(comand == null)
            throw new NullPointerException("Пустая команда");
        if(attribute == null)
            throw new NullPointerException("Не найден элемент списка");
        switch (comand){
            case delete_cable_type:
                dataDao.delleteTypeOfCable(attribute);
                break;
            case delete_ips:
                dataDao.delleteIp(attribute);
                break;
            case delete_wrong_ips:
                dataDao.delleteWrongIp(attribute);
                break;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + comand);
            }
    }

    public void executeConnectionBetweenElements(Comands comand, ActiveElement model1, PathElement model2) throws Exception {
        ConnectionDao connectionDao= factory.getConnectionDao(factory.getConnection());
        if(comand == null)
            throw new NullPointerException("Пустая команда");
        if(model1 == null)
            throw new NullPointerException("Элемент не найден");
        if(model2 == null)
            throw new NullPointerException("Элемент не найден");
        switch (comand){
            case add_connect:
                connectionDao.createConnection(model1, model2);
                break;
            case delete_connect:
                if(!model1.getConnections().contains(model2)) {
                    System.out.printf("Соединения не существует");
                    break;
                }
                connectionDao.deleteConections(model1, model2);
                break;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + comand);
        }

    }

    public void readAll(Network net) throws Exception {
        ModelDao modelDao = factory.getModelDao(factory.getConnection());
        ConnectionDao connectionDao= factory.getConnectionDao(factory.getConnection());
        modelDao.readAllModels(net);
        connectionDao.readAllConnections(net);
    }
}
