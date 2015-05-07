
package mvc.model.routeProviders;

import mvc.model.network.Network;
import java.util.ArrayList;
import java.util.SortedSet;
import mvc.model.myExceptions.AlreadyExcistException;
import mvc.model.peModel.PathElement;
import mvc.model.peModel.PathElement;


public interface RouteProvider {

    public void getDescription(PathElement el);

    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net)throws Exception;

}
