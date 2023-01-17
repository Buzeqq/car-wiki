package pl.edu.pg.s184934.filetransfer.file.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.edu.pg.s184934.filetransfer.file.entity.File;
import pl.edu.pg.s184934.filetransfer.file.service.FileService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final FileService fileService;
    private final Environment environment;

    @Autowired
    public InitializedData(FileService fileService, Environment environment) {
        this.fileService = fileService;
        this.environment = environment;
    }

    @PostConstruct
    private synchronized void init() {
        String fileStorage = environment.getProperty("fileTransfer.fileStorage.path");
        System.out.println(fileStorage);

        File mx5 = File.builder()
                .title("Mazda MX-5")
                .author("Admin")
                .filePath(fileStorage + "/mx5.jpg")
                .build();

        fileService.create(mx5);
    }
}
