package day8.알림서비스;

public class EmailNotification implements NotificationService{

    private final EmailClient emailClient;

    // 생성자 주입 방식
    public EmailNotification(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    @Override
    public void sendNotification(String message) {
        emailClient.sendEmail("user@test.com", message);
    }
}
