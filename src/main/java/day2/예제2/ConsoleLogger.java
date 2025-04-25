package day2.예제2;

// 2. 구체적인 로거
public class ConsoleLogger implements Logger{
    private final LogLevel level;

    public ConsoleLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    public void log(String message) {
        System.out.println("[INFO] " + message);
    }

    @Override
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public LogLevel getLogLevel() {
        return level;
    }
}
