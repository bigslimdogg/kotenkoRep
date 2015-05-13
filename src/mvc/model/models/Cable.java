/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import java.util.ArrayList;

import mvc.model.abstract_model.PassiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

/**
 *
 * @author Nick
 */
public class Cable extends PassiveElement{
    
    private final double delay = 0;
    private int id;
    private double price;
    private String info;
    private TypeOfCable type;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();
    
    public TypeOfCable getType() {
        return type;
    }

    public void setType(TypeOfCable type) {
        this.type = type;
    }

    public Cable() {
    }
    
    public Cable(int id, double price, String info, TypeOfCable type,Network net) {
        this.id = id;
        this.price = price;
        this.info = info;
        this.type = type;
        net.addElements(this);
    }


    @Override
    public boolean checkCon(PathElement parent) {
        return true;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cable{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", info='").append(info).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
