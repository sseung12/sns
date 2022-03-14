package code.sns.api;


import code.sns.domain.Item;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
public class TestApiController {

    @PostMapping("/upload")
    public ResponseEntity upload(@ModelAttribute Item item) throws IOException {

        MultipartFile file = item.getFile();

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String path = "/Users/seungsu/front/";
        String realPath = path + file.getOriginalFilename();
        System.out.println(realPath);
        file.transferTo(new File(realPath));
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/download")
    public ResponseEntity<Object> download() throws Exception{
        String path = "/Users/seungsu/front/당근.jpeg";

        Path filePath = Paths.get(path);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}