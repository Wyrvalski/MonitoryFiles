package main.service;

import main.MonitoryFiles;
import main.entity.Client;
import main.entity.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService {

    public Logger logger = LoggerFactory.getLogger(ClientService.class);
    public List<Client> clients = new ArrayList<>();

    public List<Client> getAllClient(List<Object> allDataInFile) {
        this.clients = allDataInFile.stream().filter(client -> client instanceof Client).
                map(client -> (Client) client).collect(Collectors.toList());
        return clients;
    }

    public Boolean clientExists(String cnpj, List<Client> clients) {
        for (int i = 0; i < this.clients.size(); i++) {
            if (clients.get(i).getCnpj().equals(cnpj)) {
                return true;
            }
        }
        return false;
    }

    public Client createClient(String[] parte, List<Object> allDataInFile, int lineNumber) {
        this.clients = getAllClient(allDataInFile);
        if ( !clientExists(parte[1],clients)){
            return new Client(Integer.parseInt(parte[0]),parte[2],parte[1],parte[3]);
        }
        this.logger.info("Cliente da linha " + lineNumber + " já está cadastrado");
        return null;
    }
}
