package day5.알림시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SMSNotification implements NotificationStrategy{
    private String apiKey;          // SMS 서비스 API 키
    private String senderNumber;    // 발신자 전화번호


    @Override
    public boolean sendNotification(String recipient, String subject, String content) {
        System.out.println("SMS 알림 전송: ");
        System.out.println("발신자: " + senderNumber);
        System.out.println("수신자: " + recipient);
        System.out.println("내용: " + content);
        return true;
    }
}
