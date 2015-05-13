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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Firewall{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", info='").append(info).append('\'');
        sb.append(", price=").append(price);
        sb.append(", notAllowedIP=").append(notAllowedIP);
        sb.append('}');
        return sb.toString();
    }
}
