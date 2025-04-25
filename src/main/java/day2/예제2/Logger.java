package day2.예제2;

public interface Logger {
    void log(String message);
    void error(String message);
    LogLevel getLogLevel();
}
