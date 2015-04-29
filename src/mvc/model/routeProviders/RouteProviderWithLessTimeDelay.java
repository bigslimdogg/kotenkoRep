
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.myExceptions.ElementNotFoundException;
import mvc.model.peModel.PathElement;


public class RouteProviderWithLessTimeDelay implements RouteProvider{


    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
        
        int k = 0;
        
        ArrayList<PathElement> path = new ArrayList<PathElement>();
        
            for(PathElement elem : net.getPathElements()){
                if(elem.getID() == id1){
                    path.add(elem);
                    break;
                }
            }
        if(path.isEmpty() == true){
            throw new ElementNotFoundException();
        }


        PathElement elemStart = path.get(0);
        
        for(PathElement elem : elemStart.getConnections()){
            if(elem.getID() == id2){
                k++;
            }
        } 
        if(k == 0 || elemStart.getConnections().isEmpty() == true){
            throw new ElementNotFoundException();
        }
        
        PathElement elemNext = elemStart.getConnections().get(0);
        
        while(elemNext.getID() != id2){
            
            for(int i = 0; i < elemStart.getConnections().size(); i++){
                
                double min = elemNext.getDelay();
            
                for(PathElement elem : elemStart.getConnections()){
                    if(elem.getPrice()< min){
                        min = elem.getDelay();
                        elemNext = elem;
                    }
                }
                path.add(elemNext);  
            }
            elemStart = elemNext;
        }
        
        
        return path;
    }

    
    @Override
    public String getDescription() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
    

}
