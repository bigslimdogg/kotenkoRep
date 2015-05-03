
package mvc.model.peModel;

import mvc.model.elModel.PassiveElement;

import java.net.InetAddress;
import java.util.*;


public interface PathElement {
    
    double getDelay();
    
    int getID();
    
    String getInfo();
    
    InetAddress getIP();
    
    double getPrice();
    
    void setDelay(double newDelay);
    
    void setID(int newID);
    
    void setInfo(String newInfo);
    
    void setIP(String ip);
    
    void setPrice(double newPrice);
    
    ArrayList<PathElement> getConnections();

    boolean checkCon(PathElement parent);

}
