/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.util.ArrayList;

import mvc.model.abstract_model.PassiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

/**
 *
 * @author Nick
 */
public class Cable extends PassiveElement{
    
    private final double delay = 0;
    private int id;
    private double price;
    private String info;
    private TypeOfCable type;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();
    
    public TypeOfCable getType() {
        return type;
    }

    public void setType(TypeOfCable type) {
        this.type = type;
    }

    public Cable() {
    }
    
    public Cable(int id, double price, String info, TypeOfCable type,Network net) {
        this.id = id;
        this.price = price;
        this.info = info;
        this.type = type;
        net.addElements(this);
    }


    @Override
    public boolean checkCon(PathElement parent) {
        return true;
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
        return "Cable{" +
                "delay=" + delay +
                ", id=" + id +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", type=" + type +
                '}';
    }
}
