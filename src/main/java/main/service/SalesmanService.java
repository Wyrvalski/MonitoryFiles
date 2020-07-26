package main.service;

import main.entity.Salesman;

import java.util.List;

public class SalesmanService {

    public Salesman getSalesmanByName(String parte, List<Salesman> salesmen) {
        Salesman salesman = new Salesman();
        for (int i = 0; i < salesmen.size(); i++) {
            if (salesmen.get(i).getName().equals(parte)) {
                salesman = salesmen.get(i);
            }
        }
        return salesman;
    }
}
