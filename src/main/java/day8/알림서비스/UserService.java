package day8.알림서비스;

public class UserService {
    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerUser(String username) {
        System.out.println("사용자 등록: " + username);

        notificationService.sendNotification("새로운 사용자가 등록되었습니다. " + username);
    }
}
