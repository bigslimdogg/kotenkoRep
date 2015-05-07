
package mvc.view;

import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Node;
import mvc.controller.connectionController.ConnectionController;

import mvc.controller.creationController.CreatePathElementController;
import mvc.controller.peController.PEController;
import mvc.controller.routeAndInfoController.InfoController;
import mvc.controller.routeAndInfoController.RouteController;
import mvc.model.elModel.ActiveElement;

import mvc.model.models.*;
import mvc.model.models.PC;
import mvc.model.models.Route;
import mvc.model.myExceptions.AccessException;
import mvc.model.network.Network;
import mvc.model.peModel.PathElement;
import mvc.model.routeProviders.RouteProviderWithLessPrice;
import mvc.model.routeProviders.RouteProviderWithLessTimeDelay;
import mvc.model.routeProviders.RouteProviderWithLessUnits;



public class KotenkoProject {

    public static void main(String[] args) throws UnknownHostException, Exception  {
        
      Network net = new Network();
        
        
      for(;;){
        Scanner sc = new Scanner( System.in );
        System.out.println("Выберете каталог команд:"+
                "\n1.Создание элементов сети"+
                "\n2.Управление элементами"+
                "\n3.Установка подключений между элементами"+
                "\n4.Просмотр информации об элементах сети и их соединениях"+
                "\n5.Поиск маршрутов"+
                "\n6.Справка"+
                "\n7.Выйти"
                );
        int catalogNumber = sc.nextInt();
        
        
        if(catalogNumber == 1){
            int isEnd = 0;
            CreatePathElementController createCon = new CreatePathElementController();
            do{
            System.out.println("введите команду");
            String command1 = sc.next();
            net.addElements(createCon.create(command1));
            
            
            System.out.println("Вернуться в главное меню(1/0)?");
            isEnd = sc.nextInt();
            }while(isEnd != 1);
        }
        
        if(catalogNumber == 2){
            
            PEController peCon = new PEController();
            
            PathElement elemToWork = null;
            System.out.println("выберете элемент из сети для работы с ним");
            ArrayList<PathElement> arr = new ArrayList();
            int i = 0;
            for(PathElement elem : net.getPathElements().keySet()){
                i++;
                System.out.println(i+ ") " + elem);
                arr.add(elem);
            }
            int elementNumber = sc.nextInt();
            if(elementNumber >= 0)
                elemToWork = arr.get(elementNumber-1);
            int isEnd = 0;
            do{
            System.out.println("введите команду для работы с выбранным элементом");
            String command2 = sc.next();
            System.out.println("введите атрибут");
            String attribute = sc.nextLine();
            peCon.execute(command2, elemToWork, attribute);
            
            System.out.println("Вернуться в главное меню(1/0)?");
            isEnd = sc.nextInt();
            }while(isEnd != 1);
        }
        
        
        
        
        if(catalogNumber == 3){
            int isEnd = 0;
            ConnectionController conectCon = new ConnectionController();
            do{
            PathElement elemToWork1 = null;
            PathElement elemToWork2 = null;
            System.out.println("выберете элементы из сети для установки соединения");
            ArrayList<PathElement> arr = new ArrayList<PathElement>();
            int i = 0;
            for(PathElement elem : net.getPathElements().keySet()){
                i++;
                System.out.println(i+ ") " + elem);
                arr.add(elem);
            }
            int elementNumber1 = sc.nextInt();
            int elementNumber2 = sc.nextInt();
            if(elementNumber1 >= 0 && elementNumber2 >= 0){
                elemToWork1 = arr.get(elementNumber1-1);
                elemToWork2 = arr.get(elementNumber2-1);
            }
            
            conectCon.connectElements((ActiveElement)elemToWork1, elemToWork2);
            
            
            
            System.out.println("Вернуться в главное меню(1/0)?");
            isEnd = sc.nextInt();
            }while(isEnd != 1);           
            
        }
        
        
        if(catalogNumber == 4){
            int isEnd = 0;
            InfoController iCon = new InfoController();
            do{
                for(PathElement elem : net.getPathElements().keySet()){
                    System.out.println(elem + "connected with:" + elem.getConnections());
                }
                
                PathElement elemToWork = null;
                System.out.println("выберете элемент из сети для получения информации о нем");
                ArrayList<PathElement> arr = new ArrayList();
                int i = 0;
                for(PathElement elem : net.getPathElements().keySet()){
                    i++;
                    System.out.println(i+ ") " + elem);
                    arr.add(elem);
                }
                int elementNumber = sc.nextInt();
                if(elementNumber >= 0)
                elemToWork = arr.get(elementNumber-1);
                
                iCon.execute(elemToWork);
                
                System.out.println("Вернуться в главное меню(1/0)?");
                isEnd = sc.nextInt();
            }while(isEnd != 1);  
        }
        
        if(catalogNumber == 5){
            int isEnd = 0;
            RouteController routeCon = new RouteController();
            do{
                PathElement elemToWork1 = null;
                PathElement elemToWork2 = null;
                System.out.println("выберете элементы из сети для поиска маршрута между ними");
                ArrayList<PathElement> arr = new ArrayList<PathElement>();
                int i = 0;
                for(PathElement elem : net.getPathElements().keySet()){
                i++;
                System.out.println(i+ ") " + elem);
                arr.add(elem);
                }
                int elementNumber1 = sc.nextInt();
                int elementNumber2 = sc.nextInt();
                if(elementNumber1 >= 0 && elementNumber2 >= 0){
                    elemToWork1 = arr.get(elementNumber1-1);
                    elemToWork2 = arr.get(elementNumber2-1);
                }
            
                System.out.println("Выберете тип поиска маршрута:" +
                        "\n1.С наименьшим числом промежуточных узлов" +
                        "\n2.С наименьшей ценой маршрута" +
                        "\n3.С наименьшей задержкой по времени" );
                int typeOfSearch = sc.nextInt();
            
            routeCon.execute(elemToWork1, elemToWork2, net, typeOfSearch);
            
            
            
            System.out.println("Вернуться в главное меню(1/0)?");
            isEnd = sc.nextInt();
            }while(isEnd != 1);             
            
            
        }
        
        
        if(catalogNumber == 6){
            int isEnd = 0;
            do{            
            System.out.println("\n1.Создание элементов сети(создание элемента сети без параметров, для их установки перейдите в пункт 2 меню). Команды:" +
            " create_cable, create_pc, create_firewall, create_hub, create_route, create_switch." +
            "\n2.Управление элементами(выбрав номер элемента, введите команду для установки соотвествующего атрибута, а затем введите атрибут). Команды:" +
            "set_id, set_ip, set_price, set_delay, set_info, set_typeOfCable, set_notAllowedIp, set_unitAmount, set_turnOn." +
            "\n3.Установка подключений между элементами(выберете два элемента из списка для установки между ними соединения)." +
            "\n4.Просмотр информации об элементах сети и их соединениях(Вам представлен список всех элементов с их соединениями." +
            "Выберете номер элемента, развернутую информацию о котором вы хотите узнать)." +
            "\n5.Поиск маршрутов" +
            "\n6.Справка(Инструкция по работе с приложением)." +
            "\n7.Выйти(Выход из программы)."+
            "\n");
            System.out.println("Вернуться в главное меню(1/0)?");
            isEnd = sc.nextInt();
            }while(isEnd != 1);              
        }
        
        
        if(catalogNumber == 7){
            break;
        }
        
        
        
      }
        
        


         
    }
    
}
