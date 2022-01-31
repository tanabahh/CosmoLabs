package itmo.cosmolabs.controller;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import itmo.cosmolabs.model.Process;
import itmo.cosmolabs.model.Unit;
import itmo.cosmolabs.model.UnitType;
import itmo.cosmolabs.repository.ProcessRepository;
import itmo.cosmolabs.repository.UnitRepository;
import itmo.cosmolabs.repository.UnitTypeRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final ProcessRepository processRepository;
    private final UnitTypeRepository unitTypeRepository;
    private final UnitRepository unitRepository;

    private final String dirName = "/Users/t.mozhanova/Documents/ИТМО/Облачка";
    public static final String connection_string = "****";

    UserController(ProcessRepository processRepository,
                   UnitRepository unitRepository,
                   UnitTypeRepository unitTypeRepository) {
        this.processRepository = processRepository;
        this.unitRepository = unitRepository;
        this.unitTypeRepository = unitTypeRepository;
    }

    @CrossOrigin
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @CrossOrigin
    @GetMapping("/process/{name}")
    public Process findProcess(@PathVariable String name) {
        return processRepository.findProcessByName(name);
    }

    @CrossOrigin
    @GetMapping("/unit/{id}")
    public List<Unit> findUnit(@PathVariable Long id) {
        UnitType unitType = unitTypeRepository.findUnitTypeById(id);
        return unitRepository.findByUnitType(unitType);
    }

    @CrossOrigin
    @GetMapping(value = "/download/{processId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable Long processId) throws InterruptedException {
        Process process = processRepository.findProcessById(processId);
        String fileName = process.getDocuments().stream().findFirst().isPresent()
                ? process.getDocuments().stream().findFirst().get().getPathInStorage() : "";

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connection_string).buildClient();

        BlobContainerClient containerClient= blobServiceClient.getBlobContainerClient("cosmolab");
        System.out.println(containerClient.getBlobContainerName());

        BlobClient blob = containerClient.getBlobClient(fileName);

        //creating an object of output stream to recieve the file's content from azure blob.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.download(outputStream);

        //converting it to the inputStream to return
        final byte[] bytes = outputStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers)
                .body(resource);
    }


}
