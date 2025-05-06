package day5.알림시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification implements NotificationStrategy{
    private String smtpServer;      // SMTP 서버주소
    private int port;               // SMTP 서버포트
    private String senderEmail;     // 발신자 이메일
    private String senderPassword;  // 발신자 비밀번호

    @Override
    public boolean sendNotification(String recipient, String subject, String content) {
        System.out.println("이메일 알림 전송: ");
        System.out.println("수신자: " + recipient);
        System.out.println("제목: " + subject);
        System.out.println("내용: " + content);
        System.out.println("SMTP 서버: " + smtpServer + ":" + port);
        return true;
    }


}
