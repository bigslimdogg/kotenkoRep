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
import mvc.model.elModel.ActiveElement;
import mvc.model.myExceptions.AccessException;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class Route extends ActiveElement{
    
    private double delay;
    private int id;
    private InetAddress ip;
    private String info;
    private double price;
    private boolean turnedOn;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();;

    public Route(double delay, int id, String ip, String info, double price, Network net) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        net.addElements(this);
    }

    public Route() {
    }
    
    @Override
    public boolean checkCon(PathElement parent) {
        if(this.isTurnedOn() == false)
            return false;
        else
            return true;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }
    
        @Override
    public void connect(PathElement elToConnect)throws Exception{
        

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
    
    
    
    public void turnON(){
        
        this.turnedOn = true;
        
    }
    public void turnOFF(){
        
        this.turnedOn = false;
        
    }

    @Override
    public void setPrice(double newPrice) {
        super.setPrice(newPrice); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIP(String newIP)  {
        super.setIP(newIP); //To change body of generated methods, choose Tools | Templates.
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
        return price; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InetAddress getIP() {
        return ip; //To change body of generated methods, choose Tools | Templates.
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
