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
    public void connect(PathElement elToConnect) throws Exception {
        super.connect(elToConnect);
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

    @Override
    public String toString() {
        return "Switch{" +
                "delay=" + delay +
                ", id=" + id +
                ", ip=" + ip +
                ", info='" + info + '\'' +
                ", price=" + price +
                ", unitAmount=" + unitAmount +
                '}';
    }
}
