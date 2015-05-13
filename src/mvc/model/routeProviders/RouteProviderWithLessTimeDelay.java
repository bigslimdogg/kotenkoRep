
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import java.util.HashMap;
import mvc.model.models.Cable;
import mvc.model.models.Firewall;
import mvc.model.models.Hub;
import mvc.model.models.Route;
import mvc.model.models.Switch;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.myExceptions.ElementNotFoundException;
import mvc.model.peModel.PathElement;


public class RouteProviderWithLessTimeDelay implements RouteProvider{

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
        double price = Double.POSITIVE_INFINITY;
        PathElement parentPE = null;
        boolean isUsed = false;

        public Root(double price) {
            this.price = price;
        }
        public Root(){
            
        }
        
    }
    public PathElement getElemWithMinDelay(PathElement parent, ArrayList<PathElement> arr){
        
        if(!arr.isEmpty()){
            double min = arr.get(0).getDelay();
            PathElement minChild = arr.get(0);
            for(PathElement elem : arr){
                if(elem.checkCon(parent) == true){
                    if(elem.getDelay() < min){
                    minChild = elem;
                    }
                }
            }
            return minChild;
        }
        else
            return null;
    }
    
    
@Override
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
        ArrayList<PathElement> path = new ArrayList<PathElement>();//нужный маршрут от id1 до id2



                
        HashMap<PathElement,Root> roots = new HashMap<PathElement,Root>();
        PathElement start = null;
        PathElement next = null;
        PathElement end = null;
        
        for(PathElement elem : net.getPathElements().keySet()){//заносим элементы из сети в roots и помечаем стартовый и конечный узел
            if(elem.getID() == id1){
                roots.put(elem, new Root(0.0));
                start = elem;
            }
            else{
                if(elem.getID() == id2){
                    end = elem;
                    roots.put(elem, new Root());
                }
                else
                    roots.put(elem, new Root());
                
            }
        }
        if(start == null || end == null || start == end){//если их нет то бросаем исключение или они равны
            throw new ElementNotFoundException();
        }
  

        
        for(;;){//цикл работает пока остались необработанные вершины
            if(roots.get(start).isUsed == true){
               ArrayList<PathElement> arrOfUnusedNeighb = new ArrayList<>();
              
               
               for(PathElement elem : start.getConnections()){
                   if(roots.get(elem).isUsed == false){
                        arrOfUnusedNeighb.add(elem);
                   }

               }
               start = getElemWithMinDelay(start, arrOfUnusedNeighb);
               if(start == null){
                   ArrayList<PathElement> arrOfUnused = new ArrayList<>();
                   for(PathElement elem : roots.keySet()){
                       if(roots.get(elem).isUsed == false)
                           arrOfUnused.add(elem);
                   }
                   if(arrOfUnused.isEmpty())
                       break;
                   else
                       start = arrOfUnused.get(0);
               }
                   
            }
            for(PathElement elem : start.getConnections()){
                if(elem.checkCon(start) == true){
                        next = elem;
                        if(roots.get(next).price > roots.get(start).price + next.getDelay()){
                            roots.get(next).price = roots.get(start).price + next.getDelay();
                        }
                }
            }
           
            
            roots.get(start).isUsed = true;//помечаем его как посещенную
        }
        
        
        
        
        
        
        
        for(PathElement elem : roots.keySet()){//выясняем родителей каждого узла
            for(PathElement connectedWithElem : elem.getConnections()){
                if(connectedWithElem.checkCon(elem) == true){
                    if(roots.get(elem).price == elem.getDelay() + roots.get(connectedWithElem).price){
                        roots.get(elem).parentPE = connectedWithElem;
                    }
                }
            }
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

    
    
    
