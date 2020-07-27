package main.service;

import main.entity.Client;
import main.entity.Sale;

import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public List<Client> getAllClient(List<Object> allDataInFile) {
        List<Client> clients = new ArrayList<>();
        for (int i =0; i < allDataInFile.size(); i++) {
            if (allDataInFile.get(i) instanceof Client) {
                clients.add((Client) allDataInFile.get(i));
            }
        }
        return clients;
    }
}
