package com.practice.Emp.EmpService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class Mail {
    @Autowired
    private JavaMailSender mail;
    public void sendQR(String email,byte[] qr,String slot) throws MessagingException{
        try{
            MimeMessage msg = mail.createMimeMessage();
            MimeMessageHelper hlp = new MimeMessageHelper(msg,true);
            hlp.setTo(email);
            hlp.setSubject("Parking QR code");
            hlp.setText(
                    "Welcome!\n\n" +
                            "✅ Your parking slot number is: " + slot + "\n" +
                            "📌 Scan the attached QR code near the guard when exiting.\n\n"
            );
            hlp.addAttachment("qr-code.png",new ByteArrayResource(qr));
            mail.send(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
