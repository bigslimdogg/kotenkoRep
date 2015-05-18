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
                System.out.println("�������� ������� ������:"+
                                "\n1.�������� ��������� ����"+
                                "\n2.�������� ��������� ����"+
                                "\n3.���������� ������"+
                                "\n4.���������/�������� ����������� ����� ����������"+
                                "\n5.�������������� ���������"+
                                "\n6.�������� ���������� �� ��������� ���� � �� �����������"+
                                "\n7.����� ���������"+
                                "\n8.�������"+
                                "\n9.�����"
                );
                int catalogNumber = sc.nextInt();

                if(catalogNumber == 1){
                    int isEnd = 0;
                    do{
                        try{
                            System.out.println("������� �������");
                            Comands command = Comands.valueOf(sc.next());
                            controller.createElem(command, net);
                            System.out.println("��������� � ������� ����(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e + "\n"); break;}

                    }while (isEnd != 1);
                }

                if(catalogNumber == 2){
                    int isEnd = 0;
                    PathElement elemToWork = null;
                    do{
                        try{
                            System.out.printf("������� ����� �������� ��� ��������");
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
                                System.out.println("������ �������� �� ����������\n");
                                break;
                            }
                            System.out.println("������� ������� ��� ������ � ��������� ���������");
                            Comands command = Comands.valueOf(sc.next());
                            controller.deleteElement(command, elemToWork, net);
                            System.out.println("��������� � ������� ����(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e + "\n"); break;}

                    }while (isEnd != 1);
                }

                if(catalogNumber == 3){
                    int isEnd = 0;
                    do{
                        try{
                            System.out.println("������� �������");
                            Comands command = Comands.valueOf(sc.next());
                            System.out.printf("������� ������");
                            String attribute = String.valueOf(sc.next());
                            controller.createData(command, attribute);
                            System.out.println("��������� � ������� ����(1/0)?");
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
                            System.out.println("�������� �������� �� ���� ��� ��������� ��� �������� �� ����������");
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
                                System.out.println("������ �������� �� ����������\n");
                                break;
                            }
                            System.out.println("������� �������");
                            Comands command = Comands.valueOf(sc.next());
                            ActiveElement elemToWork = null;
                            if (elemToWork1 instanceof ActiveElement) {
                                elemToWork = (ActiveElement) elemToWork1;
                            }
                            controller.executeConnectionBetweenElements(command, elemToWork, elemToWork2);
                            System.out.println("��������� � ������� ����(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e+ "\n"); break;}
                    }while(isEnd != 1);
                }

                if(catalogNumber == 5){//����� ����� ��������� �������� �������� �� �� ����� ��������������� �� string � �������� ��� ����� ���� ������!!!!!
                    int isEnd = 0;
                    do{
                        try{
                            PathElement elemToWork = null;
                            System.out.println("�������� ������� �� ���� ��� ������ � ���");
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
                                System.out.println("������ �������� �� ����������\n");
                                break;
                            }

                            System.out.println("������� ������� ��� ������ � ��������� ���������");
                            Comands command = Comands.valueOf(sc.next());
                            System.out.println("������� �������");
                            String attribute = sc.next();
                            controller.executeElem(command, elemToWork, attribute);
                            System.out.println("��������� � ������� ����(1/0)?");
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
                            System.out.println("�������� ������� �� ���� ��� ��������� ���������� � ���");
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
                                System.out.println("������ �������� �� ����������\n");
                                break;
                            }
                            iCon.execute(elemToWork);

                            System.out.println("��������� � ������� ����(1/0)?");
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
                            System.out.println("�������� �������� �� ���� ��� ������ �������� ����� ����");
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
                                System.out.println("������ �������� �� ����������\n");
                                break;
                            }
                            System.out.println("�������� ��� ������ ��������:" +
                                    "\n� ���������� ������ ������������� ����� route_less_units" +
                                    "\n� ���������� ����� �������� route_less_price" +
                                    "\n� ���������� ��������� �� ������� route_less_delay");
                            Comands command3 = Comands.valueOf(sc.next());
                            routeCon.execute(elemToWork1, elemToWork2, net, command3);
                            System.out.println("��������� � ������� ����(1/0)?");
                            isEnd = sc.nextInt();
                        }catch(Exception e){System.out.println(e+ "\n"); break;}
                    }while(isEnd != 1);


                }


                if(catalogNumber == 8){
                    int isEnd = 0;
                    do{
                        System.out.println("\n1.�������� ��������� ����(�������� �������� ���� ��� ����������, ��� �� ��������� ��������� � ����� 2 ����). �������:" +
                                " create_cable, create_pc, create_firewall, create_hub, create_route, create_switch." +
                                "\n2.���������� ����������(������ ����� ��������, ������� ������� ��� ��������� ��������������� ��������, � ����� ������� �������). �������:" +
                                "set_id, set_ip, set_price, set_delay, set_info, set_typeOfCable, set_notAllowedIp, set_unitAmount, set_turnOn." +
                                "\n3.��������� ����������� ����� ����������(�������� ��� �������� �� ������ ��� ��������� ����� ���� ����������)." +
                                "\n4.�������� ���������� �� ��������� ���� � �� �����������(��� ����������� ������ ���� ��������� � �� ������������." +
                                "�������� ����� ��������, ����������� ���������� � ������� �� ������ ������)." +
                                "\n5.����� ���������" +
                                "\n6.�������(���������� �� ������ � �����������)." +
                                "\n7.�����(����� �� ���������)."+
                                "\n");
                        System.out.println("��������� � ������� ����(1/0)?");
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
