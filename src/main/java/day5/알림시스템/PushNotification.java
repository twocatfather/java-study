package day5.알림시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PushNotification implements NotificationStrategy{
    private String appId;       // 앱 식별자
    private String apiKey;      // 푸시 서비스 API 키
    private String appPlatform; // 앱 플랫폼 (IOS, ANDROID)

    @Override
    public boolean sendNotification(String recipient, String subject, String content) {
        System.out.println("푸시 알림 전송: ");
        System.out.println("앱 ID: " + appId);
        System.out.println("플랫폼: " + appPlatform);
        System.out.println("수신자: " + recipient);
        System.out.println("제목: " + subject);
        System.out.println("내용: " + content);
        return true;
    }
}
