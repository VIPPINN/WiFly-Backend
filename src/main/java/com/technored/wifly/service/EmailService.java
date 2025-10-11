package com.technored.wifly.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${cors.allowed-origins[0]}")
    private String frontendUrl;

    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("WiFly - Reset Your Password");
        
        String resetUrl = frontendUrl + "/reset-password?token=" + resetToken;
        String emailBody = "Hello,\n\n" +
                "You have requested to reset your password for your WiFly account.\n\n" +
                "Please click the link below to reset your password:\n" +
                resetUrl + "\n\n" +
                "If you did not request this password reset, please ignore this email.\n\n" +
                "Best regards,\n" +
                "WiFly Team";

        message.setText(emailBody);
        
        try {
            mailSender.send(message);
        } catch (Exception e) {
            // In development, just log the error
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    public void sendWelcomeEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Welcome to WiFly!");
        
        String emailBody = "Hello " + username + ",\n\n" +
                "Welcome to WiFly! Your account has been successfully created.\n\n" +
                "You can now manage your WiFi networks and get support through our app.\n\n" +
                "Best regards,\n" +
                "WiFly Team";

        message.setText(emailBody);
        
        try {
            mailSender.send(message);
        } catch (Exception e) {
            // In development, just log the error
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
