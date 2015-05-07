/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.connectionController;


import mvc.controller.peController.PEController;
import mvc.model.elModel.ActiveElement;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;

/**
 *
 * @author Nick
 * @param <El1>
 * @param <El2>
 */
public class ConnectionController {
    
    public void connectElements(ActiveElement el1, PathElement el2) throws Exception{
        if(el1 == el2){
            throw new AlreadyExcistException();
        }
        el1.connect(el2);
    }
    
}
