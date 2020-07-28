package main;
import main.service.WriteFilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.service.ReadFilesServices;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MonitoryFiles {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(MonitoryFiles.class);
        ReadFilesServices readFilesServices = new ReadFilesServices();
        Path inDirectory = createPath("in");
        Path outDirectory = createPath("out");
        List<Object> textFile;
        while (true) {
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                inDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                WatchKey key = watchService.take();

                logger.info("Gerando relatorio dos arquivos... ");

                watchService.poll(5, TimeUnit.SECONDS);

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.context().toString().endsWith(".dat")) {
                        textFile = readFilesServices.mountObjects(inDirectory,event.context().toString());
                        WriteFilesService writeFilesService = new WriteFilesService(textFile);
                        writeFilesService.writeOnFile(outDirectory,event);
                    } else {
                        logger.warn("O arquivo " + event.context().toString() + " não termina com a extensão .dat");
                    }
                }
                logger.info("Relatorio gerado com sucesso no arquivo Realátorio.done.dat");
                key.reset();
            } catch (IOException | InterruptedException ex) {
                logger.error(ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    public static Path createPath( String inOut) {
        Path path = Paths.get(
                System.getProperty("user.home").concat(File.separator).concat("data").
                        concat(File.separator).
                        concat(inOut));
        File diretorio = new File(path.toString());
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        return path;
    }
}
