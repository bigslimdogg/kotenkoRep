/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public abstract class RouteProviderWithLessUnits implements RouteProvider{

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ArrayList<PathElement> getRouteIP(int ip1, int ip2, Network net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
