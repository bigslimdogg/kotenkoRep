/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import mvc.model.models.Cable;
import mvc.model.models.Firewall;
import mvc.model.models.Hub;
import mvc.model.models.Route;
import mvc.model.models.Switch;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.myExceptions.ElementNotFoundException;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 */
public class RouteProviderWithLessUnits implements RouteProvider{

    @Override
    public void getDescription(PathElement el) {
        System.out.println( "name:" + el.getInfo() + " " +
                            "id:" + el.getID() + " " + 
                            "ip:" + el.getIP() + " " +
                            "price:" + el.getPrice() + " " +
                            "delay:" + el.getDelay() + " " );
        
        if(el instanceof Cable){
            Cable e = (Cable)el;
            System.out.println("Type of cable :" + e.getType());
        }
        if(el instanceof Firewall){
            Firewall e = (Firewall)el;
            System.out.println("Not allowed IP's :" + e.getNotAllowedIP());
        }
        if(el instanceof Route){
            Route e = (Route)el;
            System.out.println("Is turned on :" + e.isTurnedOn());
        } 
        if(el instanceof Hub){
            Hub e = (Hub)el;
            System.out.println("Units amount :" + e.getUnitAmount());
        }     
        if(el instanceof Switch){
            Switch e = (Switch)el;
            System.out.println("Units amount :" + e.getUnitAmount());
        }  
    }
    
    public class Root{
        PathElement parentPE = null;
        boolean isUsed = false;
        int destination = 0;
        public Root(){
            
        }
        
    }
    
  
        

        
   
    


    @Override
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
        ArrayList<PathElement> path = new ArrayList<PathElement>();//нужный маршрут от id1 до id2
        
        
        
                
        HashMap<PathElement,RouteProviderWithLessUnits.Root> roots = new HashMap<PathElement,RouteProviderWithLessUnits.Root>();
        ArrayList<PathElement> treatedRoots = new ArrayList<>();
        PathElement start = null;
        PathElement next = null;
        PathElement end = null;
        
        for(PathElement elem : net.getPathElements().keySet()){//заносим элементы из сети в roots и помечаем стартовый и конечный узел
            if(elem.getID() == id1){
                roots.put(elem, new RouteProviderWithLessUnits.Root());
                start = elem;
            }
            else{
                if(elem.getID() == id2){
                    end = elem;
                    roots.put(elem, new RouteProviderWithLessUnits.Root());
                }
                else
                    roots.put(elem, new RouteProviderWithLessUnits.Root());
                
            }
        }
        if(start == null || end == null || start == end){//если их нет то бросаем исключение или они равны
            throw new ElementNotFoundException();
        }
  
        LinkedList<PathElement> q = new LinkedList<PathElement>();
        q.addFirst(start);
        roots.get(start).destination = 0;
        roots.get(start).isUsed = true;
        
        while(!q.isEmpty()){
            next = q.peekFirst();
            q.pollFirst();
            for(PathElement elem : next.getConnections()){
                if(elem.checkCon(next) == false)
                    continue;
                else {
                    if (roots.get(elem).isUsed == false) {
                        roots.get(elem).destination = roots.get(next).destination + 1;
                        q.add(elem);
                        roots.get(elem).isUsed = true;
                    }
                }
            }
        }
       
        for(PathElement child : roots.keySet()){//выясняем родителей каждого узла
            PathElement parent = null;
            for(PathElement elem : child.getConnections()){
                if(elem.checkCon(child) != false){
                    parent = elem;
                    break;
                }
            }
            int minDest = roots.get(parent).destination;
            for (PathElement connectedWithChild : child.getConnections()) {
                if (roots.get(connectedWithChild).destination < minDest) {
                    parent = connectedWithChild;
                    minDest = roots.get(parent).destination;
                }
            }
            roots.get(child).parentPE = parent;
        }
        
        
        next = end;
        path.add(end);
        
        while(next.getID() != id1){
            path.add(roots.get(next).parentPE);
            next = roots.get(next).parentPE;
        }
        return path; 
        }
    }

