
package mvc.model.PEmodel;

import java.util.Collection;

public interface PathElement {
    
    double getDelay();
    
    int getID();
    
    String getInfo();
    
    int getIP();
    
    double getPrice();
    
    void setDelay(double newDelay);
    
    void setID(int newID);
    
    void setInfo(String newInfo);
    
    void setIP(int newIP);
    
    void setPrice(double newPrice);
    
    Collection<PathElement> getConnections();
    
    
    
}
