
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;


public abstract class RouteProviderWithLessTimeDelay implements RouteProvider{

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<PathElement> getRouteID(int id1, int id2, double TimeDelay, Network net) throws AlreadyExcistException {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public ArrayList<PathElement> getRouteIP(int ip1, int ip2, Network net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

}
