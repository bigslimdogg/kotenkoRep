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



    public void turnON(){
        
        this.turnedOn = true;
        
    }
    public void turnOFF(){
        
        this.turnedOn = false;
        
    }

    @Override
    public ArrayList<PathElement> getConnections() {
        return connections;
    }



    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    public InetAddress getIP() {
        return ip;
    }

    public void setIP(String ip) {
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ActiveElement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public double getDelay() {
        return delay;
    }

    @Override
    public void setDelay(double delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", info='").append(info).append('\'');
        sb.append(", price=").append(price);
        sb.append(", turnedOn=").append(turnedOn);
        sb.append('}');
        return sb.toString();
    }
}
