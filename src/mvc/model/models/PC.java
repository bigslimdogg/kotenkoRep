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
