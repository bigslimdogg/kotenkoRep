package mvc.view;

import mvc.Comands;
import mvc.DaoController;
import mvc.controller.route_info_controller.InfoController;
import mvc.controller.route_info_controller.RouteController;
import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nick on 18.05.2015.
 */
public class DaoView {

    public void show() {
        Network net = new Network();
        DaoController controller = new DaoController();


        for(;;){
            try{
                controller.readAll(net);
                Scanner sc = new Scanner( System.in );
                System.out.println("Выберете каталог команд:"+
                                "\n1.Создание элементов сети"+
                                "\n2.Удаление элементов сети"+
                                "\n3.Добавление данных"+
                                "\n4.Установка/удаление подключений между элементами"+
                                "\n5.Редактирование элементов"+
                                "\n6.Просмотр информации об элементах сети и их соединениях"+
                                "\n7.Поиск маршрутов"+
                                "\n8.Справка"+
                                "\n9.Выйти"
                );
                int catalogNumber = sc.nextInt();

                if(catalogNumber == 1){
                    int isEnd = 0;
                    do{
                        try{
                            System.out.println("введите команду");
                            Comands command = Comands.valueOf(sc.next());
                            controller.createElem(command, net);
                            System.out.println("Вернуться в главное меню(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e + "\n"); break;}

                    }while (isEnd != 1);
                }

                if(catalogNumber == 2){
                    int isEnd = 0;
                    PathElement elemToWork = null;
                    do{
                        try{
                            System.out.printf("введите номер элемента для удаления");
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
                            Comands command = Comands.valueOf(sc.next());
                            controller.deleteElement(command, elemToWork, net);
                            System.out.println("Вернуться в главное меню(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e + "\n"); break;}

                    }while (isEnd != 1);
                }

                if(catalogNumber == 3){
                    int isEnd = 0;
                    do{
                        try{
                            System.out.println("введите команду");
                            Comands command = Comands.valueOf(sc.next());
                            System.out.printf("введите данные");
                            String attribute = String.valueOf(sc.next());
                            controller.createData(command, attribute);
                            System.out.println("Вернуться в главное меню(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e + "\n"); break;}

                    }while (isEnd != 1);
                }

                if(catalogNumber == 4){
                    int isEnd = 0;
                    do{
                        try {
                            PathElement elemToWork1 = null;
                            PathElement elemToWork2 = null;
                            System.out.println("выберете элементы из сети для установки или удаления их соединения");
                            ArrayList<PathElement> arr = new ArrayList<PathElement>();
                            int i = 0;
                            for (PathElement elem : net.getPathElements().keySet()) {
                                i++;
                                System.out.println(i + ") " + elem);
                                arr.add(elem);
                            }
                            int elementNumber1 = sc.nextInt();
                            int elementNumber2 = sc.nextInt();
                            if (elementNumber1 >= 0 && elementNumber2 >= 0 && !arr.isEmpty()) {
                                elemToWork1 = arr.get(elementNumber1 - 1);
                                elemToWork2 = arr.get(elementNumber2 - 1);
                            } else {
                                System.out.println("Такого элемента не существует\n");
                                break;
                            }
                            System.out.println("введите команду");
                            Comands command = Comands.valueOf(sc.next());
                            ActiveElement elemToWork = null;
                            if (elemToWork1 instanceof ActiveElement) {
                                elemToWork = (ActiveElement) elemToWork1;
                            }
                            controller.executeConnectionBetweenElements(command, elemToWork, elemToWork2);
                            System.out.println("Вернуться в главное меню(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e+ "\n"); break;}
                    }while(isEnd != 1);
                }

                if(catalogNumber == 5){//здесь когда передаешь элементу параметр он не хочет преобразовывать из string в числовой или какой либо другой!!!!!
                    int isEnd = 0;
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
                            Comands command = Comands.valueOf(sc.next());
                            System.out.println("введите атрибут");
                            String attribute = sc.next();
                            controller.executeElem(command, elemToWork, attribute);
                            System.out.println("Вернуться в главное меню(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e+ "\n"); break;}
                    }while(isEnd != 1);
                }

                if(catalogNumber == 6){
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

                if(catalogNumber == 7){
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


                if(catalogNumber == 8){
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


                if(catalogNumber == 9){
                    break;
                }





            }catch (Exception e){
                System.out.println(e + "\n");}
        }
    }
}
