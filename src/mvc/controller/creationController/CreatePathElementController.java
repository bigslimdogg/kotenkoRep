/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.creationController;

import mvc.model.models.Cable;
import mvc.model.models.Firewall;
import mvc.model.models.Hub;
import mvc.model.models.PC;
import mvc.model.models.Route;
import mvc.model.models.Switch;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class CreatePathElementController{
    
    /*public enum O {
        CREATE_CABLE
        ,CREATE_PC
        ,CREATE_FIREWALL
        ,CREATE_HUB
        ,CREATE_ROUTE
        ,CREATE_SWIRCH
    } */  
    
    public PathElement create(String operation){
        if(operation == null)
            throw new NullPointerException("Пустой параметр model");
        switch(operation){
            case "create_cable": return new Cable();
            case "create_pc": return new PC();
            case "create_firewall": return new Firewall();
            case "create_hub": return new Hub();
            case "create_route": return new Route();
            case "create_switch": return new Switch();
            default:
                throw new IllegalArgumentException("Неизвестная операция: " +
					operation);            
                                    
        }
    }
    
}
