package day3.로그분석기;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 로그 항목을 나타내는 클래스
 * 타임스탬프, 로그 레벨, 메시지, 소스 정보등을 포함
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    private LocalDateTime timestamp;
    private String level;
    private String message;
    private String source;
}
