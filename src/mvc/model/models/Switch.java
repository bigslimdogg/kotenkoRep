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



    public int getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(int unitAmount) {
        this.unitAmount = unitAmount;
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
