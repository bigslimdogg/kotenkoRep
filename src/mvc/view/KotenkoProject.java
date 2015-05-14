
package mvc.view;

import java.net.*;

import mvc.model.models.PC;
import mvc.model.network.Network;
import mvc.model.route_providers.RouteProviderWithLessUnits;



public class KotenkoProject {

    public static void main(String[] args) throws UnknownHostException, Exception  {
        
      //BaseView console = new BaseView();
      //console.show();
      /*//Проверка алгоритмов поиска!!
      Network net = new Network();
      PC p1 = new PC(25, 1,"85.174.76.160" , "PC1", 25, net);System.out.println(p1);
      PC p2 = new PC(1, 2, "85.174.76.160", "PC2", 1, net);System.out.println(p2);
      PC p3 = new PC(100, 3,"85.174.76.160" , "PC3", 100, net);System.out.println(p3);
      PC p4 = new PC(2, 4,"85.174.76.160" , "PC4", 2, net);System.out.println(p4);
      PC p5 = new PC(10, 5,"85.174.76.160" , "PC5", 10, net);System.out.println(p5);
      PC p6 = new PC(6, 6,"85.174.76.160" , "PC5", 6, net);System.out.println(p6);
      
      p1.connect(p2);
      p1.connect(p3);
      p2.connect(p4);
      p4.connect(p5);
      p3.connect(p5);
      p6.connect(p3);
      

      
      RouteProviderWithLessUnits r1 = new RouteProviderWithLessUnits();
      RouteProviderWithLessPrice r2 = new RouteProviderWithLessPrice();
      RouteProviderWithLessTimeDelay r3 = new RouteProviderWithLessTimeDelay();
      
      System.out.println(r1.getRouteID(1, 5, net));
      System.out.println(r2.getRouteID(1, 5, net));
      System.out.println(r3.getRouteID(1, 5, net));
     */
      Network net = new Network();
      PC p1 = new PC(25, 1,"85.174.76.160" , "PC1", 25, net);System.out.println(p1);
      PC p2 = new PC(1, 2, "85.174.76.160", "PC2", 1, net);System.out.println(p2);

      System.out.println(p1.toString());
      System.out.println(p1.getId());

    }
    
}
