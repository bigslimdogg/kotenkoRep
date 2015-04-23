/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public abstract class RouteProviderWithLessPrice implements RouteProvider{

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ArrayList<PathElement> getRouteID(int id1, int id2, double price, Network net) throws AlreadyExcistException {
        
        ArrayList<PathElement> elemWithLessPrice =  null;

        for(PathElement elem : net.getPathElements()){
            if(elem.getID() <= id1 && elem.getID() >= id2){
                if(elem.getPrice() <= price)
                    elemWithLessPrice.add(elem);
            }
        }
        return elemWithLessPrice;
    }

    
    public ArrayList<PathElement> getRouteIP(int ip1, int ip2, Network net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
