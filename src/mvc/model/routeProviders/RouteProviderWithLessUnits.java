/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public abstract class RouteProviderWithLessUnits implements RouteProvider{



    public static ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    
    
}
