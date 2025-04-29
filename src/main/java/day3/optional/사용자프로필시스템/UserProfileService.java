package day3.optional.사용자프로필시스템;

import day2.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserProfileService {
    private Map<String, UserProfile> userProfiles = new ConcurrentHashMap<>();

    /**
     *  사용자 ID로 사용자의 프로필을 조회하는 메소드
     */
    public Optional<UserProfile> findUserById(String userId) {
        return Optional.ofNullable(userProfiles.get(userId));
    }

    /**
     *  사용자의 주소를 형식화된 문자열로 반환하는 메소드
     */
    public String getDisplayAddress(String userId) {
        return findUserById(userId)                 // 사용자 조회
                .map(UserProfile::getAddress)       // 주소 객체 추출
                .map(address -> String.format("%s %s %s",   // 주소 포맷팅
                        address.getStreet(),
                        address.getCity(),
                        address.getZipCode()))
                .orElse("주소 정보 없음");        // 주소가 없을 경우 기본값 제공
    }

    /**
     *  사용자의 이메일을 반환하는 메서드
     *  userId
     *  return 사용자 이메일
     *  orElseThrow -> IllegalArgumentException Invalid email for user: userId
     */
    public String getUserEmail(String userId) {
        return findUserById(userId)
                .map(UserProfile::getEmail)
                .filter(email -> email.contains("@"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email for user: " + userId));
    }


    /**
     * 사용자 이메일을 업데이트하는 메서드
     * 사용자가 있어야 이메일을 업데이트할 수있다
     * userId, String newEmail
     * return void
     */
    public void updateUserEmail(String userId, String newEmail) {
        findUserById(userId).ifPresent(user -> {
            user.setEmail(newEmail);
            System.out.println("Email updated: " + newEmail);
        });
    }
}
