/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.routeAndInfoController;

import java.util.ArrayList;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;
import mvc.model.routeProviders.RouteProviderWithLessPrice;
import mvc.model.routeProviders.RouteProviderWithLessTimeDelay;
import mvc.model.routeProviders.RouteProviderWithLessUnits;

/**
 *
 * @author Nick
 */
public class RouteController {
    
    public ArrayList<PathElement> execute(PathElement el1, PathElement el2, Network net, int i) throws Exception{
        
        if(i == 1){
            return new RouteProviderWithLessUnits().getRouteID(el1.getID(), el2.getID(), net);
        }
        
        if(i == 2){
            return new RouteProviderWithLessPrice().getRouteID(el1.getID(), el2.getID(), net);
        }
        if(i == 3){
            return new RouteProviderWithLessTimeDelay().getRouteID(el1.getID(), el2.getID(), net);
        } 
        else
            return null;
    }
    
}
