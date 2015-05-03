/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.network;

import java.net.InetAddress;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;
import mvc.model.elModel.ActiveElement;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;
import mvc.model.routeProviders.RouteProvider;
import mvc.model.routeProviders.RouteProviderWithLessPrice;

/**
 *
 * @author Nick
 */
public class Network {
    

    private HashMap<PathElement,ArrayList<PathElement>> elements = new HashMap<PathElement,ArrayList<PathElement>>();

    
    public Network() {
    }

    public void addElements(PathElement elToAdd) {

            elements.put(elToAdd, elToAdd.getConnections());
    }

 
    
    


    
    public HashMap<PathElement,ArrayList<PathElement>>  getPathElements(){
        
        return elements;
    }
    

    
}
