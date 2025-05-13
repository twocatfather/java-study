package day7.설정관리자;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public enum ConfigurationManager {

    // 유일한 상수
    INSTANCE;

    private final Properties properties;

    private final ConcurrentHashMap<String, String> cachedProperties;

    private static final String DEFAULT_CONFIG_FILE = "config.properties";
    private String configFilePath;

    ConfigurationManager() {
        this.properties = new Properties();
        this.cachedProperties = new ConcurrentHashMap<>();
        this.configFilePath = DEFAULT_CONFIG_FILE;
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream(configFilePath)) {

            if (input == null) {
                System.err.println("설정 파일 '" + configFilePath + "'을 찾을 수 없습니다.");
                return;
            }

            properties.clear();
            cachedProperties.clear();

            properties.load(input);
            System.out.println("설정 파일 '" + configFilePath + "'을 로드했습니다.");
        } catch (IOException e) {
            throw new RuntimeException("설정 파일 로드 실패: " + e.getMessage(), e);
        }
    }

    public void setProperty(String key, String value) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("속성 키는 비어있을 수 없습니다.");
        }

        properties.setProperty(key, value);
        cachedProperties.put(key, value);
    }

    public String getProperty(String key) {
        String cachedValue = cachedProperties.get(key);
        if (cachedValue != null) {
            return cachedValue;
        }

        String value = properties.getProperty(key);
        if (value != null) {
            cachedProperties.put(key, value);
        }

        return value;
    }
}
