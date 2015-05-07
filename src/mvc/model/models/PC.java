/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.net.*;
import java.net.UnknownHostException;
import java.util.*;
import mvc.model.elModel.ActiveElement;
import mvc.model.elModel.PassiveElement;
import mvc.model.peModel.PathElement;
import mvc.model.*;
import mvc.model.myExceptions.AccessException;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.network.*;



public class PC extends ActiveElement {
    
    private double delay;
    private int id;
    private InetAddress ip;
    private String info;
    private double price;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();

    public PC(double delay, int id, String ip, String info, double price, Network net) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip = InetAddress.getByName(ip);
        this.info = info;
        this.price = price;
        net.addElements(this);
    }

    public PC() {
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

    @Override
    public void setPrice(double newPrice) {
        super.setPrice(newPrice); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public void setInfo(String newInfo) {
        super.setInfo(newInfo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIP(String newIP) {
        super.setIP(newIP); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InetAddress getIP() {
        return ip; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setID(int newID) {
        super.setID(newID); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDelay(double newDelay) {
        super.setDelay(newDelay); //To change body of generated methods, choose Tools | Templates.
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
