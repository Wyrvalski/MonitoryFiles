package main.service;

public class ItemService {

    public String getItemsInLine(String line) {
        int posFinal = line.indexOf("]");
        int posInicial = line.indexOf("[");
        return line.substring(posInicial + 1, posFinal);
    }
}
