/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.util.Set;
import mvc.model.connection.Connection;
import mvc.model.elModel.PassiveElement;

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

    public TypeOfCable getType() {
        return type;
    }

    public void setType(TypeOfCable type) {
        this.type = type;
    }

    public Cable(int id, double price, String info, TypeOfCable type) {
        this.id = id;
        this.price = price;
        this.info = info;
        this.type = type;
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
    
    
    
    
}
