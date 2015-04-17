/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.ArrayList;
import mvc.model.PEmodel.PathElement;
import mvc.model.PEmodel.PathElement;

/**
 *
 * @author Nick
 */
public abstract class RouteProviderWithLessPrice implements RouteProvider{

    @Override
    public String getDwscription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PathElement> getRouteIP(int ip1, int ip2, Network net) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
