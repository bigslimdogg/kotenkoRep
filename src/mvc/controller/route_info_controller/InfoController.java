/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.route_info_controller;

import mvc.model.pe_model.PathElement;
import mvc.model.route_providers.RouteProviderWithLessPrice;

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
