package main;
import main.service.ReadFilesServices;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MonitoryFiles {
    public static void main(String[] args){

        Path inDirectory = createPath("in");
        Path outDirectory = createPath("out");

        while (true) {
            try(WatchService watchService = FileSystems.getDefault().newWatchService()){
                ReadFilesServices readFilesServices = new ReadFilesServices();
                inDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                WatchKey key = watchService.take();
                Thread.sleep(1000);
                for (WatchEvent<?> event : key.pollEvents()) {
                    List<Object> textFile = readFilesServices.readEachFile(inDirectory,event);
                    System.out.println(textFile);
                }

            } catch (IOException ex) {

            } catch (InterruptedException ex) {

            }

        }
    }

    public static Path createPath( String inOut) {
        return (Paths.get(
                System.getProperty("user.home").concat(File.separator).concat("data").
                        concat(File.separator).
                        concat(inOut)
        ));
    }
}
