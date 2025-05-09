package day5.텍스트분석;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AnalysisResult {
    private String originalText;
    private String analysisType;
    private Map<String, Object> resultData;

    public AnalysisResult(String originalText, String analysisType) {
        this.originalText = originalText;
        this.analysisType = analysisType;
        this.resultData = new HashMap<>();
    }

    public void addResult(String key, Object value) {
        resultData.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("분석 유형: ").append(analysisType).append("\n");
        sb.append("원본 텍스트: ").append(shortenText(originalText, 50)).append("\n");
        sb.append("분석 결과:\n");

        for (Map.Entry<String, Object> entry : resultData.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ");

            Object value = entry.getValue();
            if (value instanceof Map) {
                sb.append("\n");
                for (Map.Entry<?, ?> subEntry : ((Map<?, ?>) value).entrySet()) {
                    sb.append("  * ").append(subEntry.getKey()).append(": ");
                    sb.append(subEntry.getValue()).append("\n");
                }
            } else {
                sb.append(value).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * 텍스트가 너무 길 경우 줄이는 유틸리티 메소드
     *
     * @param text 원본 텍스트
     * @param maxLength 최대 길이
     * @return 줄어든 텍스트
     */
    private String shortenText(String text, int maxLength) {
        if (text == null) {
            return "";
        }

        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength - 3) + "...";
    }

    public Integer getIntegerResult(String key) {
        Object value = resultData.get(key);
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
