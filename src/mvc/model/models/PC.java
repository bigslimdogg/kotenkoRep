/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.net.*;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.abstract_model.ActiveElement;
import mvc.model.pe_model.PathElement;

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
    public void connect(PathElement elToConnect) throws Exception {
        super.connect(elToConnect);
    }

    @Override
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

  
    @Override
    public void setInfo(String newInfo) {
        this.info = newInfo;
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

    @Override
    public String toString() {
        return "PC{" +
                "delay=" + delay +
                ", id=" + id +
                ", ip=" + ip +
                ", info='" + info + '\'' +
                ", price=" + price +
                '}';
    }
}
