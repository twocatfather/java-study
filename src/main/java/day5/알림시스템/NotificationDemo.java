package day5.알림시스템;

public class NotificationDemo {
    public static void main(String[] args) {
        // 이메일 알림 전략 생성
        NotificationStrategy emailStrategy = new EmailNotification(
                "smtp.gmail.com", 587, "sender@example.com", "password123");

        // SMS 알림 전략 생성
        NotificationStrategy smsStrategy = new SMSNotification(
                "your-api-key", "010-1234-5678");

        // 푸시 알림 전략 생성
        NotificationStrategy pushStrategy = new PushNotification(
                "com.example.app", "firebase-api-key", "Android");

        // 알림 관리자 생성 (기본 전략으로 이메일 사용)
        NotificationManager notificationManager = new NotificationManager(emailStrategy);

        // 다양한 알림 유형에 대한 전략 등록
        notificationManager.registerStrategy("marketing", emailStrategy);
        notificationManager.registerStrategy("urgent", smsStrategy);
        notificationManager.registerStrategy("update", pushStrategy);

        System.out.println("=== 기본 알림 전송 (이메일) ===");
        notificationManager.notify(
                "user@example.com",
                "환영합니다!",
                "저희 서비스에 가입해주셔서 감사합니다.");

        System.out.println("\n=== 마케팅 알림 전송 (이메일) ===");
        notificationManager.notify(
                "marketing",
                "user@example.com",
                "특별 할인 이벤트",
                "이번 주말 20% 할인 쿠폰이 발급되었습니다.");

        System.out.println("\n=== 긴급 알림 전송 (SMS) ===");
        notificationManager.notify(
                "urgent",
                "010-9876-5432",
                "알림", // SMS는 제목을 사용하지 않지만 인터페이스 일관성을 위해 전달
                "비밀번호 변경이 감지되었습니다. 본인이 아닐 경우 고객센터로 연락주세요.");

        System.out.println("\n=== 업데이트 알림 전송 (푸시) ===");
        notificationManager.notify(
                "update",
                "device-token-123",
                "새 버전 출시",
                "앱 버전 2.0이 출시되었습니다. 지금 업데이트하세요.");

        System.out.println("\n=== 중요 공지사항 (모든 채널로 전송) ===");
        notificationManager.notifyAllChannels(
                "user-identifier", // 실제로는 각 채널에 맞는 식별자로 변환 필요
                "서비스 점검 안내",
                "내일 오전 2시부터 4시까지 서버 점검이 있을 예정입니다.");

        // 기본 전략 변경 (SMS로 변경)
        System.out.println("\n=== 기본 전략 변경 (SMS로 변경) ===");
        notificationManager.setDefaultStrategy(smsStrategy);
        notificationManager.notify(
                "010-9876-5432",
                "알림",
                "SMS가 이제 기본 알림 방법입니다.");
    }
}
