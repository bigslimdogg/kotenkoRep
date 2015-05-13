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
import mvc.model.abstract_model.PassiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

/**
 *
 * @author Nick
 */
public class Hub extends PassiveElement{
    
    private final double delay = 0;
    private int id;
    private double price;
    private String info;
    private InetAddress ip;
    private int unitAmount;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();
    
    public Hub(int id, double price, String info, int unitAmount, Network net) {
        this.id = id;
        this.price = price;
        this.info = info;
        this.unitAmount = unitAmount;
        net.addElements(this);
    }

    public Hub() {
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
        final StringBuilder sb = new StringBuilder("Hub{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", info='").append(info).append('\'');
        sb.append(", ip=").append(ip);
        sb.append(", unitAmount=").append(unitAmount);
        sb.append('}');
        return sb.toString();
    }
}
