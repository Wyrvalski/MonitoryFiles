package main.service;

import main.entity.Salesman;

import java.util.ArrayList;
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

    public static List<Salesman> getAllSalesman(List<Object> allDataInFile) {
        List<Salesman> salesmen = new ArrayList<>();
        for (int i =0; i < allDataInFile.size(); i++) {
            if (allDataInFile.get(i) instanceof Salesman) {
                salesmen.add((Salesman) allDataInFile.get(i));
            }
        }
        return salesmen;
    }
}
