
package mvc.model.peModel;

import java.net.InetAddress;
import java.util.*;
import java.util.Set;
import mvc.model.connection.Connection;

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
    
    
    
}
