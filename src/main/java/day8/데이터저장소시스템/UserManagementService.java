package day8.데이터저장소시스템;

import day8.알림서비스.NotificationService;

public class UserManagementService {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserManagementService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void createUser(User user) {
        userRepository.save(user);
        notificationService.sendNotification("새로운 사용자가 등록되었습니다.");
    }
}
