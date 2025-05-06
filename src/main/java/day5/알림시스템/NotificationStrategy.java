package day5.알림시스템;

public interface NotificationStrategy {

    /**
     *
     * @param recipient 수신자 정보(전화번호, 이메일 등)
     * @param subject 메시지 제목
     * @param content 메시지 내용
     * @return 메시지 전송 성공 여부
     */
    boolean sendNotification(String recipient, String subject, String content);
}
