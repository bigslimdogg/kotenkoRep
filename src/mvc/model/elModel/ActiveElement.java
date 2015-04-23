/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.elModel;

import java.net.InetAddress;
import mvc.model.peModel.PathElement;
import java.util.*;
import mvc.model.connection.Connection;

/**
 *
 * @author Nick
 */
public abstract class ActiveElement implements PathElement{
    
    protected double delay;
    protected int id;
    protected InetAddress ip;
    protected String info;
    protected double price;
    public static List<PathElement> elements;
    

   

    @Override
    public double getDelay() {
        return delay;
    }

  
    @Override
    public void setDelay(double delay) {
        this.delay = delay;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public InetAddress getIP() {
        return ip;
    }

    @Override
    public void setIP(InetAddress ip) {
        this.ip = ip;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

  
    
    
    
    public void connect(PathElement elToConnect)throws Exception{
        
    }

    
}
