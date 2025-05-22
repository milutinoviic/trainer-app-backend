package uns.ac.rs.trainerappbackend.service;


public interface EmailService {

    void sendEmail(String to, String subject, String text);


}
