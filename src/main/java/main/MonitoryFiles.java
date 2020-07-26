package main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.service.ReadFilesServices;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MonitoryFiles {
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(MonitoryFiles.class);
        Path inDirectory = createPath("in");
        Path outDirectory = createPath("out");

        while (true) {
            try(WatchService watchService = FileSystems.getDefault().newWatchService()){
                ReadFilesServices readFilesServices = new ReadFilesServices();
                inDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                WatchKey key = watchService.take();
                Thread.sleep(1000);
                logger.info("Gerando relatorio dos arquivos... ");
                for (WatchEvent<?> event : key.pollEvents()) {
                    List<Object> textFile = new ArrayList<>();
                    if (event.context().toString().endsWith(".dat")) {
                        logger.info("Relatório do arquivo " + event.context() + " sendo gerado ...");
                         textFile = readFilesServices.readEachFile(inDirectory,event);
                    } else {
                        logger.warn("O arquivo " + event.context().toString() + " não termina com a extensão .dat");
                    }
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            } catch (InterruptedException ex) {
                logger.error(ex.getMessage());
                throw new RuntimeException(ex.getMessage());
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
