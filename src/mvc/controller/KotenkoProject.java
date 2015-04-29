
package mvc.controller;

import java.net.*;
import javafx.scene.Node;
import mvc.model.models.*;
import mvc.model.models.PC;
import mvc.model.models.Route;
import mvc.model.myExceptions.AccessException;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;
import mvc.model.routeProviders.RouteProviderWithLessPrice;



public class KotenkoProject {

    public static void main(String[] args) throws UnknownHostException, Exception  {
        
        /*System.out.println("Выберете каталог команд:"+
                "\n1.Создание элементов сети"+
                "\n2.Управление элементами"+
                "\n3.Установка подключений между элементами"+
                "\n4.Поиск маршрутов"
                );
        int i = System.in.available();
        System.out.println(i);*/
        
        
        PC p1 =new PC(12, 1, "176.212.68.19", "my PC1", 175);
        PC p2 =new PC(12, 2, "5.139.12.193", "my PC2", 100);
        PC p3 =new PC(12, 3, "85.174.76.160", "my PC3", 30);
        PC p4 =new PC(12, 4, "176.212.64.6", "my PC4", 20);
        
        Network net = new Network();
        
        p1.connect(p2, net);
        p1.connect(p3, net);
        p2.connect(p4, net);
        p3.connect(p4, net);
        
        
        RouteProviderWithLessPrice rp = new RouteProviderWithLessPrice();
        System.out.println(rp.getRouteID(1, 4, net));
        
      
    }
    
}
