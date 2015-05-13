/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.route_info_controller;

import java.util.ArrayList;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;
import mvc.model.route_providers.RouteProviderWithLessPrice;
import mvc.model.route_providers.RouteProviderWithLessTimeDelay;
import mvc.model.route_providers.RouteProviderWithLessUnits;

/**
 *
 * @author Nick
 */
public class RouteController {
    
    public ArrayList<PathElement> execute(PathElement el1, PathElement el2, Network net, int i) throws Exception{

        switch (i) {
            case 1: return new RouteProviderWithLessUnits().getRouteID(el1.getID(), el2.getID(), net);


            case 2: return new RouteProviderWithLessPrice().getRouteID(el1.getID(), el2.getID(), net);


            case 3: return new RouteProviderWithLessTimeDelay().getRouteID(el1.getID(), el2.getID(), net);

            default: return null;
        }
    }
    
}
