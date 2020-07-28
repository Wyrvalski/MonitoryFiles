package main.service;

import main.entity.Client;
import main.entity.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService {

    List<Client> clients = new ArrayList<>();

    public List<Client> getAllClient(List<Object> allDataInFile) {
        this.clients = allDataInFile.stream().filter(client -> client instanceof Client).
                map(client -> (Client) client).collect(Collectors.toList());
        return clients;
    }
}
