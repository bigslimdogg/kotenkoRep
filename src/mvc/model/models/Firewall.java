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
import mvc.model.connection.Connection;
import mvc.model.elModel.ActiveElement;
import mvc.model.myExceptions.AccessException;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class Firewall extends ActiveElement{
    
    private double delay;
    private int id;
    private InetAddress ip;
    private String info;
    private double price;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();;

    public Firewall(double delay, int id, String ip, String info, double price) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
           
    }

  
    
        public void connect(PathElement elToConnect, Network net)throws Exception{
        
        if(elToConnect == null){
            throw new NullPointerException();
        }
        
        for(PathElement elem : connections)
            if(elem == elToConnect)
            {
                throw new AlreadyExcistException();
            }

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

        if(this.isAddressCorrect(elToConnect.getIP()) == false)
                throw new AccessException();
        
        if(elToConnect instanceof Hub){
            Hub el = (Hub) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        connections.add(elToConnect);
        elToConnect.getConnections().add(this);
        net.addElements(elToConnect);
        net.addElements(this); 
    }


    public boolean isAddressCorrect(InetAddress address){
        return true;
    }
    
    @Override
    public void setPrice(double newPrice) {
        super.setPrice(newPrice); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIP(String ip) {
        super.setIP(ip); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void setInfo(String newInfo) {
        super.setInfo(newInfo); //To change body of generated methods, choose Tools | Templates.
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
    public InetAddress getIP() {
        return super.getIP(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfo() {
        return super.getInfo(); //To change body of generated methods, choose Tools | Templates.
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
