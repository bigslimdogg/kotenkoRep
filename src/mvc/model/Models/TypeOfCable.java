/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.Models;

/**
 *
 * @author Nick
 */
public enum TypeOfCable {
    
    UTP{void option(){};}
    ,FTP{void option(){};}
    ,STP{void option(){};}
    ,S_FTP{void option(){};}
    ,U_STP{void option(){};}
    ,SF_UTP{void option(){};}
    ,COAXIAL{void option(){};}
    ,OPTICAL{void option(){};};
    
    abstract void option();
    
    
    
}
