package day7.과제.basecode;

public enum LogLevel {
    DEBUG(1),    // 디버깅 정보
    INFO(2),     // 일반 정보
    WARNING(3),  // 경고
    ERROR(4);    // 오류

    private final int value;

    LogLevel(int value) {
        this.value = value;
    }

    /**
     * 로그 레벨의 숫자 값을 반환합니다.
     * @return 로그 레벨 값
     */
    public int getValue() {
        return value;
    }
}
