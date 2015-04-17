/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.Models;

import mvc.model.ELmodel.ActiveElement;

/**
 *
 * @author Nick
 */
public class Firewall extends ActiveElement{
    
    private double delay;
    private int id;
    private int ip;
    private String info;
    private double price;
    private int address1;
    private int address2; 

    public int getAddress1() {
        return address1;
    }

    public int getAddress2() {
        return address2;
    }

    public void setAddress1(int address1) {
        this.address1 = address1;
    }

    public void setAddress2(int address2) {
        this.address2 = address2;
    }

    
    @Override
    public void setPrice(double newPrice) {
        super.setPrice(newPrice); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIP(int newIP) {
        super.setIP(newIP); //To change body of generated methods, choose Tools | Templates.
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
    public void setDelay(double newDelay) {
        super.setDelay(newDelay); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPrice() {
        return super.getPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIP() {
        return super.getIP(); //To change body of generated methods, choose Tools | Templates.
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
