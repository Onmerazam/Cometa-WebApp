package onmera.cometa.controller;


import com.fasterxml.jackson.annotation.JsonView;
import onmera.cometa.domain.Message;
import onmera.cometa.domain.Views;
import onmera.cometa.dto.EventType;
import onmera.cometa.dto.ObjectType;
import onmera.cometa.repository.MessageRepo;
import onmera.cometa.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Value("${upload.path}")
    private String uploadPath;
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;


    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }


    @PostMapping(consumes = {"multipart/form-data"})
    public Message create(@RequestPart("properties") Message message, @RequestParam(name = "files", required = false) MultipartFile files[] ) throws IOException {

        if (files != null) {
            List<String> setOfImages = new ArrayList<>();
            for (MultipartFile file : files) {

                if (file != null && !file.getOriginalFilename().isEmpty()) {

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    String uuidFile = UUID.randomUUID().toString();
                    String resultFileName = uuidFile + "." + file.getOriginalFilename();
                    (setOfImages).add(resultFileName);
                    file.transferTo(new File(uploadPath + "/" + resultFileName));
                }
            }
            message.setImages(setOfImages);
        }
        Message updatedMessage = messageRepo.save(message);
        wsSender.accept(EventType.CREATE, updatedMessage);
        return updatedMessage;
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updatedMessage = messageRepo.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updatedMessage);
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }


}
