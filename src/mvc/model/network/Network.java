/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.network;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    
    private List<PathElement> elements;

    public Network() {
    }

    public void addElements(PathElement elToAdd) {
        this.elements.add(elToAdd);
    }

    public List<PathElement> getElements() {
        return elements;
    }
    
    
    


    
    public HashMap<PathElement,PathElement> getPathElements(){
        HashMap<PathElement,PathElement> elemWithConnection = null;
        for(PathElement elem : this.getElements()){
            if(elem.getConnections() != null){
                for(PathElement elemToConnected : elem.getConnections()){
                    elemWithConnection.put(elem, elemToConnected);
                }
            }
        }
        return elemWithConnection;
    }
    
}
