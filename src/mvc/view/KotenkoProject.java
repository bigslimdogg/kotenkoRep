
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
        
      BaseView console = new BaseView();
      console.show();

    }
    
}
