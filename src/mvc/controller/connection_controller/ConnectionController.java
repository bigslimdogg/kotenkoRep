/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller.connection_controller;


import mvc.model.abstract_model.ActiveElement;

import mvc.model.my_exceptions.AlreadyExistException;
import mvc.model.pe_model.PathElement;


public class ConnectionController {

    public void connectElements(ActiveElement el1, PathElement el2) throws Exception{
        if(el1.getID() == el2.getID()){
            throw new AlreadyExistException();
        }
        el1.connect(el2);
    }

}
