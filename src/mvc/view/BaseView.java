/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.util.ArrayList;
import java.util.Scanner;

import mvc.controller.Comands;
import mvc.controller.connection_controller.ConnectionController;
import mvc.controller.creation_controller.CreatePathElementController;
import mvc.controller.pe_controller.PEController;
import mvc.controller.route_info_controller.InfoController;
import mvc.controller.route_info_controller.RouteController;
import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

/**
 *
 * @author Nick
 */
public class BaseView {
    public void show(){
    Network net = new Network();
        
        
      for(;;){
        try{
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
                try{
                System.out.println("введите команду");
                Comands command1 = Comands.valueOf(sc.next());
                net.addElements(createCon.create(command1));
                System.out.println("Вернуться в главное меню(1/0)?");
                isEnd = sc.nextInt();
                }catch(Exception e){System.out.println(e + "\n"); break;}
            }while(isEnd != 1);
        }
        
        if(catalogNumber == 2){//здесь когда передаешь элементу параметр он не хочет преобразовывать из string в числовой или какой либо другой!!!!!
            int isEnd = 0;
            PEController peCon = new PEController();
            do{
                try{
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
                if(elementNumber >= 0 && !arr.isEmpty())
                    elemToWork = arr.get(elementNumber-1);
                else{
                    System.out.println("Такого элемента не существует\n");
                    break;
                }
                    
                
                System.out.println("введите команду для работы с выбранным элементом");
                Comands command2 = Comands.valueOf(sc.next());
                System.out.println("введите атрибут");
                String attribute = sc.next();
                peCon.execute(command2, elemToWork, attribute);
            
                System.out.println("Вернуться в главное меню(1/0)?");
                isEnd = sc.nextInt();
                }catch(Exception e){System.out.println(e+ "\n"); break;}
            }while(isEnd != 1);
        }
        
        
        
        
        if(catalogNumber == 3){
            int isEnd = 0;
            ConnectionController conectCon = new ConnectionController();
            do{
                try{
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
                if(elementNumber1 >= 0 && elementNumber2 >= 0 && !arr.isEmpty()){
                    elemToWork1 = arr.get(elementNumber1-1);
                    elemToWork2 = arr.get(elementNumber2-1);
                }
                else{
                    System.out.println("Такого элемента не существует\n");
                    break;
                }
                conectCon.connectElements((ActiveElement)elemToWork1, elemToWork2);
                System.out.println("Вернуться в главное меню(1/0)?");
                isEnd = sc.nextInt();
                }catch(Exception e){System.out.println(e+ "\n"); break;}
            }while(isEnd != 1);           
            
        }
        
        
        if(catalogNumber == 4){
            int isEnd = 0;
            InfoController iCon = new InfoController();
            do{
                try{
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
                if(elementNumber >= 0 && !arr.isEmpty())
                    elemToWork = arr.get(elementNumber-1);
                else{
                    System.out.println("Такого элемента не существует\n");
                    break;
                }
                iCon.execute(elemToWork);
                
                System.out.println("Вернуться в главное меню(1/0)?");
                isEnd = sc.nextInt();
                }catch(Exception e){System.out.println(e+ "\n"); break;}
            }while(isEnd != 1);  
        }
        
        if(catalogNumber == 5){
            int isEnd = 0;
            RouteController routeCon = new RouteController();
            do{
                try{
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
                if(elementNumber1 >= 0 && elementNumber2 >= 0 && !arr.isEmpty()){
                    elemToWork1 = arr.get(elementNumber1-1);
                    elemToWork2 = arr.get(elementNumber2-1);
                }
                else{
                    System.out.println("Такого элемента не существует\n");
                    break;
                }
                System.out.println("Выберете тип поиска маршрута:" +
                        "\nС наименьшим числом промежуточных узлов route_less_units" +
                        "\nС наименьшей ценой маршрута route_less_price" +
                        "\nС наименьшей задержкой по времени route_less_delay");
                Comands command3 = Comands.valueOf(sc.next());
                routeCon.execute(elemToWork1, elemToWork2, net, command3);
                System.out.println("Вернуться в главное меню(1/0)?");
                isEnd = sc.nextInt();
                }catch(Exception e){System.out.println(e+ "\n"); break;}
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
        
        
        
      }catch(Exception e){System.out.println(e+ "\n");}
      }      
    }
}
