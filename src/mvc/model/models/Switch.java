/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.elModel.ActiveElement;
import mvc.model.myExceptions.AccessException;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class Switch extends ActiveElement{
    
    private double delay;
    private int id;
    private InetAddress ip;
    private String info;
    private double price;
    private int unitAmount;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();;

    public Switch(double delay, int id, String ip, String info, double price, int unitAmount, Network net) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        this.unitAmount = unitAmount;
        net.addElements(this);
    }

    public Switch() {
    }
    
    @Override
    public boolean checkCon(PathElement parent) {
        return true;
    }

    @Override
    public void connect(PathElement elToConnect)throws Exception{
        
        if(elToConnect == null){
            throw new NullPointerException();
        }        
        for(PathElement elem : connections)
            if(elem == elToConnect)
            {
                throw new AlreadyExcistException();
            }        


        if(this.getUnitAmount() < 1)
            throw new AccessException();

        if(elToConnect instanceof Switch){
            Switch el = (Switch) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        if(elToConnect instanceof Hub){
            Hub el = (Hub) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        connections.add(elToConnect);
        elToConnect.getConnections().add(this);
    }
    public int getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(int unitAmount) {
        this.unitAmount = unitAmount;
    }

    @Override
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public void setIP(String ip) {
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Firewall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public InetAddress getIP() {
        return ip;
    }

    @Override
    public void setInfo(String newInfo) {
        this.info = newInfo;
    }

    @Override
    public void setID(int newID) {
        this.id = newID;
    }

    @Override
    public void setDelay(double newDelay) {
        this.delay = newDelay;
    }

    @Override
    public double getPrice() {
        return price; //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String getInfo() {
        return info; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDelay() {
        return delay; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<PathElement> getConnections(){
    return connections;
    }    
    
}
