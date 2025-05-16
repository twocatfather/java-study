package day8.알림서비스;

public class SMSNotification implements NotificationService{
    private final SMSClient smsClient;

    public SMSNotification(SMSClient smsClient) {
        this.smsClient = smsClient;
    }

    @Override
    public void sendNotification(String message) {
        smsClient.sendSMS("010-1234-5678", message);
    }
}
