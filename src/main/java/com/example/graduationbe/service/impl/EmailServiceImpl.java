package com.example.graduationbe.service.impl;

import com.example.graduationbe.config.JwtAuthenticationFilter;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender javaMailSender;

    private final UserRepository userRepository;
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleEmail() {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            String username = JwtAuthenticationFilter.USER_CURRENT;
            User user = userRepository.findByUsername(username);
            mailMessage.setFrom(sender);
            mailMessage.setRecipients(MimeMessage.RecipientType.TO, user.getEmail());
            mailMessage.setContent(" <div style=\"text-align: center\">\n" +
                    "    Xin chào " + user.getFullName() +
                    "    <p>Bạn đã đặt hàng thành công!!!</p>\n" +
                    "    \"<img src=\"https://plus24h.com/upload/editor/images/icon-dat-hang-thanh-cong-09.jpg\" class=\"img-fluid\"\n" +
                    "          width=\"300px\" height=\"300px\">\n" +
                    "  </div>", "text/html; charset=utf-8");
            mailMessage.setSubject("Placed Order");
            this.javaMailSender.send(mailMessage);
            return "Mail send successfully";
        } catch (Exception ex) {
            return "Error while sending mail";
        }
    }

//    @Override
//    public String sendMailWithAttachment(Mail mail) {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//        try {
//            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            String username = JwtAuthenticationFilter.USER_CURRENT;
//            User user = userRepository.findByUsername(username);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(user.getEmail());
//            mimeMessageHelper.setText(mail.getMsgBody());
//            mimeMessageHelper.setSubject(
//                    mail.getSubject());
//
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File("C:/SendMail/images.jpg"));
//
//            mimeMessageHelper.addAttachment(
//                    "images.jpg", file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        } catch (Exception ex) {
//            return "Error while sending mail!!!";
//        }
//    }
}
