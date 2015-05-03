/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.elModel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import mvc.model.peModel.PathElement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import mvc.model.network.Network;

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
    protected ArrayList<PathElement> checkedConnections= new ArrayList<PathElement>();
    
    public ActiveElement() {
    }
  
    
    

   

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
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public InetAddress getIP() {
        return this.ip;
    }

    @Override
    public void setIP(String ip) {
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ActiveElement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

  
    
    
    
    public void connect(PathElement elToConnect, Network net)throws Exception{
        
    }

    
}
