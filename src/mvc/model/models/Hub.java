/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.util.ArrayList;
import java.util.LinkedList;
import mvc.model.elModel.PassiveElement;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class Hub extends PassiveElement{
    
    private final double delay = 0;
    private int id;
    private double price;
    private String info;
    private int unitAmount;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();
    
    public Hub(int id, double price, String info, int unitAmount) {
        this.id = id;
        this.price = price;
        this.info = info;
        this.unitAmount = unitAmount;

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
    public void setPrice(double newPrice) {
        super.setPrice(newPrice); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInfo(String newInfo) {
        super.setInfo(newInfo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setID(int newID) {
        super.setID(newID); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPrice() {
        return super.getPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfo() {
        return super.getInfo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        return super.getID(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDelay() {
        return super.getDelay(); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public ArrayList<PathElement> getConnections(){
        return connections;
    }   
    
}
