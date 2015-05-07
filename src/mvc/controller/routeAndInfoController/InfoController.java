/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.routeAndInfoController;

import mvc.model.peModel.PathElement;
import mvc.model.routeProviders.RouteProvider;
import mvc.model.routeProviders.RouteProviderWithLessPrice;

/**
 *
 * @author Nick
 */
public class InfoController {
    
    public void execute(PathElement elemForInfo){
        if(elemForInfo != null)
            new RouteProviderWithLessPrice().getDescription(elemForInfo);
    }
    
}
