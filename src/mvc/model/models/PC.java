/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.net.*;
import java.net.UnknownHostException;
import java.util.*;
import mvc.model.connection.Connection;
import mvc.model.elModel.ActiveElement;
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
    private LinkedList<PathElement> connections;
    
    

    public PC(double delay, int id, String info, double price, String ip) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        ActiveElement.elements.add(this);
    }


  
    
    
    @Override
    public void connect(PathElement elToConnect)throws Exception{
        

        
        if(elToConnect instanceof Route){
            Route el = (Route) elToConnect;
            if(el.isTurnedOn() == false)
                throw new AccessException();
        }
        if(elToConnect instanceof Switch){
            Switch el = (Switch) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        if(elToConnect instanceof Firewall){
            Firewall el = (Firewall) elToConnect;
            if(el.isAddressCorrect(this.getIP()) == false)
                throw new AccessException();
        } 
        if(elToConnect instanceof Hub){
            Hub el = (Hub) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        connections.add(elToConnect);
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
    public void setIP(InetAddress newIP) {
        super.setIP(newIP); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InetAddress getIP() {
        return super.getIP(); //To change body of generated methods, choose Tools | Templates.
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
        return super.getPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public String getInfo() {
        return super.getInfo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        return super.getID(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDelay() {
        return super.getDelay(); //To change body of generated methods, choose Tools | Templates.
    }
    
 
    @Override
    public LinkedList<PathElement> getConnections(){
        
    return connections;
    }
    
    
}
