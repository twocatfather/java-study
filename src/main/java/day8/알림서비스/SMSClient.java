package day8.알림서비스;

public class SMSClient {

    public void sendSMS(String phoneNumber, String message) {
        System.out.println("SMS sent to " + phoneNumber + " with Message = " + message);
    }
}
