import org.junit.jupiter.api.Test;
import day1.개선된컨벤션.UserDto;
import day1.잘못된컨벤션.userDTO;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PerformanceTest {
    @Test
    void compareNullCheckPerformance() {
        userDTO oldStyle = new userDTO();
        long startTimeOld = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
            try {
                oldStyle.Setusername(null);
            } catch (Exception e) {
                // 예외무시
            }
        }

        long endTimeOld = System.nanoTime();

        // 개선된코드
        UserDto newStyle = new UserDto();
        long startTimeNew = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            try {
                newStyle.setUsername(null);
            } catch (Exception e) {
                // 예외 무시
            }
        }
        long endTimeNew = System.nanoTime();

        System.out.printf("Old Style execution time: %d ms%n", (endTimeOld - startTimeOld) / 1000000);
        System.out.printf("New Style execution time: %d ms%n", (endTimeNew - startTimeNew) / 1000000);
    }

    @Test
    void codeQualityMetrics() {
        int old = calculateComplexity(userDTO.class);
        int newDto = calculateComplexity(UserDto.class);

        assertThat(newDto)
                .withFailMessage("New style should have lower complexity. Old: %d, New %d",
                        old, newDto)
                .isLessThanOrEqualTo(old);
    }

    private int calculateComplexity(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods())
                .mapToInt(method -> {
                    int complexity = 1;
                    complexity += method.getParameterCount();
                    complexity += method.getExceptionTypes().length;
                    return complexity;
                })
                .sum();
    }
}
