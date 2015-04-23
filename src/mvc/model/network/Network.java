/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.network;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import mvc.model.connection.Connection;
import mvc.model.elModel.ActiveElement;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class Network {
    


    
    public Set<PathElement> getPathElements() throws AlreadyExcistException{
        
        Set<PathElement> allActiveElements = null;
       
        for(Connection con : ActiveElement.allConnections){
            
            if(allActiveElements.contains(con.el1) || allActiveElements.contains(con.el2))
                continue;
            else{
                allActiveElements.add(con.el1);
                allActiveElements.add(con.el2);
            }

            
        }
        return allActiveElements;
    }
    
}
