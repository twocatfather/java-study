package day5.텍스트분석;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordFrequencyAnalysis implements TextAnalysisStrategy{
    @Override
    public AnalysisResult analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("분석할 텍스트가 없습니다.");
        }

        AnalysisResult result = new AnalysisResult(text, "단어 빈도 분석");

        // 단어 분리 및 빈도 계산
        Map<String, Integer> wordFrequency = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(text.toLowerCase(), " \t\n\r\f.,;:!?'\"()[]{}");

        int totalWordCount = 0;

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();

            // 특수 문자 및 숫자만 있는 토큰은 무시
            if (word.matches("^[0-9\\p{Punct}]+$")) {
                continue;
            }

            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            totalWordCount++;
        }

        // 가장 빈번한 단어 찾기
        String mostFrequentWord = "";
        int highestFrequency = 0;

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (entry.getValue() > highestFrequency) {
                mostFrequentWord = entry.getKey();
                highestFrequency = entry.getValue();
            }
        }

        // 결과 저장
        result.addResult("총 단어 수", totalWordCount);
        result.addResult("고유 단어 수", wordFrequency.size());
        result.addResult("가장 빈번한 단어", mostFrequentWord);
        result.addResult("가장 빈번한 단어의 출현 횟수", highestFrequency);
        result.addResult("단어 빈도", wordFrequency);

        return result;
    }
}
