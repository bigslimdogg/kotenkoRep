
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import java.util.HashMap;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.myExceptions.ElementNotFoundException;
import mvc.model.peModel.PathElement;


public class RouteProviderWithLessTimeDelay implements RouteProvider{

    @Override
    public String getDescription(PathElement el) {
        String s = el.getInfo() + el.getID() + el.getIP().toString() + el.getPrice() + el.getDelay();
        
        return s;
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
    public PathElement getElemWithMinDelay(PathElement parent, PathElement child){
        double min = child.getDelay();
        PathElement minChild = child;
        for(PathElement elem :parent.getConnections()){
            if(elem.getDelay() < min){
                minChild = elem;
            }
        }
        return minChild;
    }
    
    
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
        ArrayList<PathElement> path = new ArrayList<PathElement>();//нужный маршрут от id1 до id2
        
        
        
                
        HashMap<PathElement,RouteProviderWithLessTimeDelay.Root> roots = new HashMap<PathElement,RouteProviderWithLessTimeDelay.Root>();
        ArrayList<PathElement> treatedRoots = new ArrayList<>();
        PathElement start = null;
        PathElement next = null;
        PathElement end = null;
        
        for(PathElement elem : net.getPathElements().keySet()){//заносим элементы из сети в roots и помечаем стартовый и конечный узел
            if(elem.getID() == id1){
                roots.put(elem, new RouteProviderWithLessTimeDelay.Root(0.0));
                start = elem;
            }
            else{
                if(elem.getID() == id2){
                    end = elem;
                    roots.put(elem, new RouteProviderWithLessTimeDelay.Root());
                }
                else
                    roots.put(elem, new RouteProviderWithLessTimeDelay.Root());
                
            }
        }
        if(start == null || end == null || start == end){//если их нет то бросаем исключение или они равны
            throw new ElementNotFoundException();
        }
  
        
        
        while(treatedRoots.size() != roots.keySet().size()){//цикл работает пока остались необработанные вершины
            if(roots.get(start).isUsed == true){
                //когда соседи стартового узла просмотрены
                next = start.getConnections().get(0);
                start = getElemWithMinDelay(start, next);//берем следующего как соседа start с минимальной ценой             
            }
            for(PathElement elem : start.getConnections()){
                next = getElemWithMinDelay(start, elem);//получили соседа узла с минимальной стоимостью теперь работаем с ним
                if(roots.get(next).price > roots.get(start).price + next.getDelay()){
                    roots.get(next).price = roots.get(start).price + next.getDelay();
                }
            }
            treatedRoots.add(start);//после просмотра всех соседей добавляем в список обработанных уздлв
            roots.get(start).isUsed = true;//помечаем его как посещенную
        }
        
        for(PathElement elem : roots.keySet()){//выясняем родителей каждого узла
            for(PathElement connectedWithElem : elem.getConnections()){
                if(roots.get(elem).price == elem.getDelay() + roots.get(connectedWithElem).price){
                    roots.get(elem).parentPE = connectedWithElem;
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

    
    
    
