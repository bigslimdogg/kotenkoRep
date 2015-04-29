
package mvc.model.elModel;

import java.net.InetAddress;
import mvc.model.peModel.PathElement;
import java.util.*;
import mvc.model.connection.Connection;


public abstract class PassiveElement implements PathElement{
    
    protected final double delay = 0;
    protected int id;
    protected double price;
    protected String info;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
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
    public ArrayList<PathElement> getConnections() {
        return null;
    }

    @Override
    public double getDelay() {
        return this.delay;
    }

    @Override
    public InetAddress getIP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDelay(double newDelay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIP(String ip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
