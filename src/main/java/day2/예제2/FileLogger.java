package day2.예제2;

public class FileLogger implements Logger{
    private final String filePath;
    private final LogLevel level;

    public FileLogger(String filePath, LogLevel level) {
        this.filePath = filePath;
        this.level = level;
    }

    @Override
    public void log(String message) {
        // 파일에 로그 작성 로직
    }

    @Override
    public void error(String message) {
        // 파일에 에러로그 작성 로직
    }

    @Override
    public LogLevel getLogLevel() {
        return level;
    }
}
