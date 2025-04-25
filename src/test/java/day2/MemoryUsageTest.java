package day2;

import day2.예제2.Logger;
import day2.예제2.LoggerFactory;
import day2.예제2.LoggerType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MemoryUsageTest {
    @Test
    void memoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // 팩토리 패턴을 통한 객체 생성
        List<Logger> loggers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            loggers.add(LoggerFactory.getFactory(LoggerType.CONSOLE).createLogger());
        }

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("Memory used: %d bytes%n", usedMemoryAfter - usedMemoryBefore);
    }
}
