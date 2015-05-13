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
public class Firewall extends ActiveElement{
    
    private double delay;
    private int id;
    private InetAddress ip;
    private String info;
    private double price;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();;
   
    private ArrayList<String> notAllowedIP = new ArrayList<String>(){{
                                            add("85.174.76.160");
                                            add("5.139.12.193");
                                            add("176.212.68.19");
                                            add("176.212.64.6");
                                            }};

    public ArrayList<String> getNotAllowedIP() {
        return notAllowedIP;
    }

    public void setNotAllowedIP(String notAllowedIP) {
        this.notAllowedIP.add(notAllowedIP);
    }

    @Override
    public boolean checkCon(PathElement parent) {
        if(!isAddressCorrect(parent.getIP().toString()))
            return false;
        else
            return true;
    }

    public Firewall() {
    }
    
    public Firewall(double delay, int id, String ip, String info, double price, Network net) throws UnknownHostException {
    
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        net.addElements(this);   
    }


    @Override
    public void connect(PathElement elToConnect) throws Exception {
        super.connect(elToConnect);
    }

    public boolean isAddressCorrect(String address){
        if(address == null)
            return false;
        for(String ip : this.notAllowedIP){
            if(ip == null)
                continue;
            if(ip.equals(address))
                return false;   
        }
        return true;
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
        return price;
    }

    @Override
    public InetAddress getIP() {
        return ip;
    }

    @Override
    public String getInfo() {
        return info;
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
        return "Firewall{" +
                "notAllowedIP=" + notAllowedIP +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", ip=" + ip +
                ", id=" + id +
                ", delay=" + delay +
                '}';
    }
}
