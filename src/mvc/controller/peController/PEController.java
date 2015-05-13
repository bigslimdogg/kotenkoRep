/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.peController;



import mvc.model.models.Cable;
import mvc.model.models.Firewall;
import mvc.model.models.Hub;
import mvc.model.models.Route;
import mvc.model.models.Switch;
import mvc.model.models.TypeOfCable;
import mvc.model.myExceptions.NotAllowedException;
import mvc.model.peModel.PathElement;

/**

 */
public class PEController{
    
    /*public enum O {
        SET_ID
        ,SET_IP
        ,SET_PRICE
        ,SET_DELAY
        ,SET_INFO
        ,SET_TYPEODCABLE
        ,SET_UNITAMOUNT
        ,SET_NOTALLOWEDIP
        ,SET_TURNON
    } */  
    
    
    

    
    public void execute(String operation, PathElement model, String attribute) {
	if (operation == null)
            throw new NullPointerException("Пустой параметр operation");
	if (model == null)
            throw new NullPointerException("Пустой параметр model");
	if (attribute == null)
            throw new NullPointerException("Пустой параметр attribute");
        switch (operation){
            case "set_id":
                Integer id = Integer.valueOf(attribute);
		model.setID(id);
		break;
            case "set_ip":
                model.setIP(attribute);
                break;
            case "set_price":
                double price = Double.valueOf(attribute);
                model.setPrice(price);
                break;
            case "set_delay":
                double delay = Double.valueOf(attribute);
                model.setDelay(delay);
                break;
            case "set_info":
                model.setInfo(attribute);
                break;
            case "set_typeOfCable":
                if(model instanceof Cable){
                    Cable el = (Cable)model;
                    
                    //el.setType((TypeOfCable)attribute);
                }
                else
                    throw new IllegalArgumentException("Неизвестная операция: " +
					operation); 
                break;
            case "set_notAllowedIp":
                if(model instanceof Firewall){
                   Firewall el = (Firewall)model;
                   el.setNotAllowedIP(attribute.toString());
                }
                else
                    throw new IllegalArgumentException("Неизвестная операция: " +
					operation); 
                break; 
            case "set_unitAmount":
                if(model instanceof Hub){
                   int units = Integer.valueOf(attribute);
                   Hub el = (Hub)model;
                   el.setUnitAmount(units);
                }
                if(model instanceof Switch){
                   int units = Integer.valueOf(attribute);
                   Switch el = (Switch)model;
                   el.setUnitAmount(units);
                }
                else
                    throw new IllegalArgumentException("Неизвестная операция: " +
					operation); 
                break;                 
            case "set_turnOn":
                if(model instanceof Route){
                    Route el = (Route)model;
                    boolean isTurn = Boolean.valueOf(attribute);
                    if(isTurn == false)
                        el.turnON();
                    if(isTurn == false)
                        el.turnOFF();
                    else
                        throw new IllegalArgumentException("Неизвестная операция: " +
					operation); 
                }
                else
                    throw new IllegalArgumentException("Неизвестная операция: " +
			operation); 
            default:
                throw new IllegalArgumentException("Неизвестная операция: " +
					operation);            
        }


    }
    
}
