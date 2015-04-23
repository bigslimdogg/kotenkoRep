
package mvc.controller;

import java.net.*;
import mvc.model.models.*;
import mvc.model.myExceptions.AccessException;

public class KotenkoProject {

    public static void main(String[] args) throws Exception {
        
    
        PC p = new PC(1, 1, null, 1,"154.100.101.11");
        System.out.println(p.getIP());
        p.setIP(InetAddress.getByName("154.100.101.11"));
        System.out.println(p.getIP());
    
        Route r = new Route(0, 3, null, "asaaaaa", 1);
        //System.out.println(r.isTurnedOn());       
        r.turnON();
        


        //NPE
        /*try{
        p.connect(r);//NPE
        }catch(AccessException e){
            System.out.println("!!!");
        }*/
    }
    
}
