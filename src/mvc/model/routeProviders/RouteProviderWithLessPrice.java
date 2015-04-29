/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.routeProviders;



import mvc.model.network.Network;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.myExceptions.ElementNotFoundException;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;
import java.util.*;

/**
 *
 * @author Nick
 */
public class RouteProviderWithLessPrice implements RouteProvider{

 
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    

 
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
        ArrayList<PathElement> path = new ArrayList<PathElement>();//нужный маршрут от id1 до id2
        HashMap<PathElement,ArrayList<PathElement>> allPathes = new HashMap<PathElement,ArrayList<PathElement>>();//таблицу узлов и их кратчайших маршрутов
        
        
        int i = 0;
                
        HashMap<PathElement,Double> roots = new HashMap<PathElement,Double>();
        ArrayList<PathElement> visitedRoots = new ArrayList<>();
        ArrayList<PathElement> notVisitedRoots = new ArrayList<>();
        ArrayList<PathElement> deleted = new ArrayList<>();//узлы которые в алгоритме полностью изучены


        for(PathElement elem : net.getPathElements()){
            if(elem.getID() == id1){
                roots.put(elem, 0.0);
                i = net.getPathElements().indexOf(elem);
            }
            else{
                roots.put(elem, Double.POSITIVE_INFINITY/2);
            }
        }
        
  
        PathElement start = net.getPathElements().get(i);
        allPathes.put(start, new ArrayList<PathElement>());
        PathElement elemNext = start.getConnections().get(0);//берем следующий узел как самый первый в связях 
        
        while(roots.keySet().size() !=  deleted.size()){
            
            
            for(PathElement elem : start.getConnections()){//выбираем непосещенных соседей
                if(!visitedRoots.contains(elem) && !deleted.contains(elem)){
                    notVisitedRoots.add(elem);
                }
            }
            if(notVisitedRoots.isEmpty()){
                deleted.add(start);
                visitedRoots.clear();
                start = elemNext;
                allPathes.put(start, new ArrayList<PathElement>());//добавляем стартовый узел в таблицу узлов и их кратчайших маршрутов
                for(PathElement elem : start.getConnections()){
                    if(!visitedRoots.contains(elem) && !deleted.contains(elem)){
                        notVisitedRoots.add(elem);
                }
                }
            }
            
            double min = elemNext.getPrice();//минимальная цена - цена следующего
            for(PathElement elem : notVisitedRoots){//проверить является ли следующий с минимальной ценой из всех связей
                if(elem.getPrice() < min && !deleted.contains(elem)){//если есть в связях элемент с меньшей ценой 
                    elem=elemNext;//берем его
                    min = elem.getPrice();//и его цена теперь минимум
                }
            }
            visitedRoots.add(elemNext);//работаем со следующим еще не посещенным узлом с наим. стоимостью, предварительно занеся его в посещенные
            if(roots.get(start)+elemNext.getPrice() < roots.get(elemNext)){
                roots.put(elemNext, roots.get(start)+elemNext.getPrice());
                allPathes.get(start).add(elemNext);//NPE!!!! добавляем узел в кратчайший маршрут для стартового узла

                }        
            notVisitedRoots.clear();    
        }
        
        for(PathElement elem : allPathes.keySet()){
            if(elem.getID() == id2)
                path.addAll(allPathes.get(elem));//заносим в искомый путь путь соответсвующий нужному узлу из таблицы кратчайших путей для всех узлов
        }
        
        
        return path; 
        }
    

}
