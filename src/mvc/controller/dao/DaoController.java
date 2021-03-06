package mvc.controller.dao;

import mvc.model.dao.ConnectionDao;
import mvc.model.dao.DaoFactory;
import mvc.model.dao.DataDao;
import mvc.model.dao.ModelDao;
import mvc.controller.Comands;
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



    public void createElem(Comands comand, Network net) throws SQLException, UnknownHostException {
        ModelDao modelDao = DaoFactory.getModelDao();
        if(comand == null)
            throw new NullPointerException("������ �������");
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
                throw new IllegalArgumentException("����������� ��������: " + comand);
            }
    }

    public void createData(Comands comand, String attribute) throws SQLException {
        DataDao dataDao = DaoFactory.getDataDao();
        if(comand == null) {
            System.out.println("������ �������");
        }
        if(attribute == null){
            System.out.println("�� �� ����� ��������");
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
                throw new IllegalArgumentException("����������� ��������: " + comand);
            }
    }


    public void executeElem(Comands comand, PathElement model, String attribute) throws SQLException, DaoException {
        ModelDao modelDao = DaoFactory.getModelDao();
        if(comand == null)
            throw new NullPointerException("������ �������");
        if(attribute == null)
            throw new NullPointerException("������ ��������");
        if(model == null)
            throw new NullPointerException("������� �� ������");
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
                    System.out.println("������ ip ��� � ����");
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
                        System.out.println("������ ip ��� � ����");
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
                        System.out.println("������ ���� ��� � ����");
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
                throw new IllegalArgumentException("����������� ��������: " + comand);
        }
    }



    public void deleteElement(Comands comand, PathElement model, Network net) throws SQLException {
        ModelDao modelDao = DaoFactory.getModelDao();
        if(comand == null)
            throw new NullPointerException("������ �������");
        if(model == null)
            throw new NullPointerException("������� �� ������");
        switch (comand){
            case delete_cable:
                modelDao.deleteCable(model);
                if(net.getPathElements().containsKey(model))
                    net.deleteElements(model);
                break;
            case delete_firewall:
                modelDao.deleteFirewall(model);
                if(net.getPathElements().containsKey(model))
                    net.deleteElements(model);
                break;
            case delete_hub:
                modelDao.deleteHub(model);
                if(net.getPathElements().containsKey(model))
                    net.deleteElements(model);
                break;
            case delete_pc:
                modelDao.deletePc(model);
                if(net.getPathElements().containsKey(model))
                    net.deleteElements(model);
                break;
            case delete_route:
                modelDao.deleteRoute(model);
                if(net.getPathElements().containsKey(model))
                    net.deleteElements(model);
                break;
            case delete_switch:
                modelDao.deleteSwitch(model);
                if(net.getPathElements().containsKey(model))
                    net.deleteElements(model);
                break;
            default :
                throw new IllegalArgumentException("����������� ��������: " + comand);
        }
    }
    public void deleteData(Comands comand, String attribute) throws SQLException {
        DataDao dataDao = DaoFactory.getDataDao();
        if(comand == null)
            throw new NullPointerException("������ �������");
        if(attribute == null)
            throw new NullPointerException("�� ������ ������� ������");
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
                throw new IllegalArgumentException("����������� ��������: " + comand);
            }
    }

    public void executeConnectionBetweenElements(Comands comand, ActiveElement model1, PathElement model2) throws Exception {
        ConnectionDao connectionDao= DaoFactory.getConnectionDao();
        if(comand == null)
            throw new NullPointerException("������ �������");
        if(model1 == null)
            throw new NullPointerException("������� �� ������");
        if(model2 == null)
            throw new NullPointerException("������� �� ������");
        switch (comand){
            case add_connect:
                connectionDao.createConnection(model1, model2);
                break;
            case delete_connect:
                if(!model1.getConnections().contains(model2)) {
                    System.out.printf("���������� �� ����������");
                    break;
                }
                connectionDao.deleteConnections(model1, model2);
                break;
            default:
                throw new IllegalArgumentException("����������� ��������: " + comand);
        }
    }

    public void readAll(Network net) throws Exception {
        ModelDao modelDao = DaoFactory.getModelDao();
        ConnectionDao connectionDao= DaoFactory.getConnectionDao();
        modelDao.readAllModels(net);
        connectionDao.readAllConnections(net);

    }
}
