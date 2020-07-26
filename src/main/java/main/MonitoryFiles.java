package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class MonitoryFiles {
    public static void main(String[] args){
        Path inDirectory = Paths.get(System.getProperty("user.home").concat(File.separator).concat("data").
                concat(File.separator).
                concat("in"));
        System.out.println(inDirectory);

        while (true) {
            try(WatchService watchService = FileSystems.getDefault().newWatchService()){
                inDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                WatchKey key = watchService.take();
                Thread.sleep(1000);
            } catch (IOException ex) {

            } catch (InterruptedException ex) {

            }

        }
    }
}
