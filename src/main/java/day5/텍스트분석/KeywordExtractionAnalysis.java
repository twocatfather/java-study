package day5.텍스트분석;

import java.util.*;

public class KeywordExtractionAnalysis implements TextAnalysisStrategy{

    // 불용어(stopwords) 목록 - 의미 없는 일반 단어들
    private final List<String> stopwords = Arrays.asList(
            "the", "a", "an", "and", "or", "but", "is", "are", "was", "were",
            "in", "on", "at", "to", "for", "with", "by", "about", "like", "through",
            "over", "before", "after", "between", "of", "from", "as", "up", "down",
            "이", "그", "저", "이것", "그것", "저것", "이번", "저번", "그런", "이런",
            "나", "너", "우리", "저희", "당신", "그들", "그녀", "그이",
            "은", "는", "이", "가", "을", "를", "에", "에서", "로", "으로",
            "와", "과", "의", "도", "만", "뿐", "다", "에게");

    @Override
    public AnalysisResult analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("분석할 텍스트가 없습니다.");
        }

        AnalysisResult result = new AnalysisResult(text, "키워드 추출 분석");

        // 텍스트 전처리
        String processedText = text.toLowerCase()
                .replaceAll("[\\p{Punct}]", " ")
                .replaceAll("\\s", " ")
                .trim();
        String[] wordsArray = processedText.split("\\s+");
        List<String> words = new ArrayList<>();

        for (String word : wordsArray) {
            if (!stopwords.contains(word) && word.length() > 1) {
                words.add(word);
            }
        }

        // 단어 빈도 계산
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // 단어 점수 계산 (빈도 + 단어 길이 가중치)
        Map<String, Double> wordScores = new HashMap<>();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            // 단순 점수 계산 -> 빈도 * (1 + 0.1 * 단어 길이)
            double lengthWeight = 1.0 + (0.1 * word.length());
            double score = frequency * lengthWeight;

            wordScores.put(word, score);
        }

        List<Map.Entry<String, Double>> sortedScores = new ArrayList<>(wordScores.entrySet());
        sortedScores.sort(Map.Entry.<String, Double>comparingByValue().reversed());

        // 상위 키워드 추출 (최대 10개)
        List<String> topKeywords = new ArrayList<>();
        Map<String, Double> topKeywordScores = new HashMap<>();

        int count = 0;
        for (Map.Entry<String, Double> entry : sortedScores) {
            if (count >= 10) {
                break;
            }

            String keyword = entry.getKey();
            double score = entry.getValue();

            topKeywords.add(keyword);
            topKeywordScores.put(keyword, score);

            count++;

        }

        result.addResult("분석된 총 단어 수", words.size());
        result.addResult("불용어 제외 고유 단어 수", wordFrequency.size());
        result.addResult("상위 키워드", topKeywords);
        result.addResult("키워드 점수", topKeywordScores);

        return result;
    }
}
