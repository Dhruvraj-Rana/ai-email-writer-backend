package com.AIEmail.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailController {


    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/Generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailReq emailReq){


        String response= emailService.generateEmailReply(emailReq);
        return ResponseEntity.ok(response);
    }
}
