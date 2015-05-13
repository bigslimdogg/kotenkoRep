/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

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
    public void connect(PathElement elToConnect) throws Exception {
        super.connect(elToConnect);
    }

    public void turnON(){
        
        this.turnedOn = true;
        
    }
    public void turnOFF(){
        
        this.turnedOn = false;
        
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
        return "Route{" +
                "delay=" + delay +
                ", id=" + id +
                ", ip=" + ip +
                ", info='" + info + '\'' +
                ", price=" + price +
                ", turnedOn=" + turnedOn +
                '}';
    }
}
