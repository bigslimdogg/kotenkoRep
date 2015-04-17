
package mvc.controller;

import java.util.ArrayList;
import mvc.model.PEmodel.PathElement;
import mvc.model.PEmodel.PathElement;


public interface RouteProvider {

    
    String getDwscription();
    
    ArrayList<PathElement> getRouteID(int id1, int id2, Network net);
    
    ArrayList<PathElement>  getRouteIP(int ip1, int ip2, Network net);
    
}
