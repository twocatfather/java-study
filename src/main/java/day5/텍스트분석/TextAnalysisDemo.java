package day5.텍스트분석;

import java.util.Map;

public class TextAnalysisDemo {
    public static void main(String[] args) {
        // 분석할 샘플 텍스트
        String positiveText = "오늘은 정말 행복한 하루였습니다. 날씨도 좋고 맛있는 음식도 먹고 "
                + "좋은 친구들과 함께 즐거운 시간을 보냈습니다. 모든 것이 완벽했어요!";

        String negativeText = "오늘은 최악의 하루였습니다. 아침부터 비가 내려 기분이 우울했고 "
                + "실수로 중요한 회의에 늦었습니다. 점심은 맛이 없었고 일도 계속 실패했습니다.";

        String articleText = "인공지능 기술이 빠르게 발전하고 있습니다. 머신러닝과 딥러닝을 통해 "
                + "다양한 산업 분야에서 혁신이 일어나고 있습니다. 특히 자연어 처리 기술은 "
                + "대화형 AI, 번역, 감정 분석 등 여러 응용 분야에서 활용되고 있습니다. "
                + "그러나 인공지능의 윤리적 문제와 개인정보 보호에 대한 우려도 커지고 있습니다. "
                + "앞으로 인공지능 기술은 더욱 발전하면서 우리 사회에 큰 변화를 가져올 것으로 예상됩니다.";

        // 텍스트 분석기 생성
        TextAnalyzer analyzer = new TextAnalyzer();

        // 다양한 분석 전략 생성 및 등록
        analyzer.registerStrategy("wordFrequency", new WordFrequencyAnalysis());
        analyzer.registerStrategy("sentiment", new SentimentAnalysis());
        analyzer.registerStrategy("keyword", new KeywordExtractionAnalysis());

        System.out.println("=== 텍스트 분석 시스템 데모 ===\n");

        // 1. 단어 빈도 분석 수행
        System.out.println("1. 단어 빈도 분석");
        analyzer.selectStrategy("wordFrequency");
        System.out.println("현재 분석 전략: " + analyzer.getCurrentStrategyType());
        AnalysisResult wordFreqResult = analyzer.analyze(articleText);
        System.out.println(wordFreqResult);

        // 2. 감정 분석 수행 (긍정적 텍스트)
        System.out.println("\n2. 감정 분석 - 긍정적 텍스트");
        analyzer.selectStrategy("sentiment");
        System.out.println("현재 분석 전략: " + analyzer.getCurrentStrategyType());
        AnalysisResult positiveSentimentResult = analyzer.analyze(positiveText);
        System.out.println(positiveSentimentResult);

        // 3. 감정 분석 수행 (부정적 텍스트)
        System.out.println("\n3. 감정 분석 - 부정적 텍스트");
        AnalysisResult negativeSentimentResult = analyzer.analyze(negativeText);
        System.out.println(negativeSentimentResult);

        // 4. 키워드 추출 분석 수행
        System.out.println("\n4. 키워드 추출 분석");
        analyzer.selectStrategy("keyword");
        System.out.println("현재 분석 전략: " + analyzer.getCurrentStrategyType());
        AnalysisResult keywordResult = analyzer.analyze(articleText);
        System.out.println(keywordResult);

        // 5. 분석 전략 직접 지정하여 분석
        System.out.println("\n5. 특정 전략으로 직접 분석 (단어 빈도)");
        AnalysisResult directResult = analyzer.analyzeWith(articleText, "wordFrequency");
        System.out.println("총 단어 수: " + directResult.getIntegerResult("총 단어 수"));
        System.out.println("고유 단어 수: " + directResult.getIntegerResult("고유 단어 수"));

        // 6. 모든 전략으로 동시에 분석
        System.out.println("\n6. 모든 전략으로 동시에 분석");
        Map<String, AnalysisResult> allResults = analyzer.analyzeWithAll(articleText);
        System.out.println("총 " + allResults.size() + "개의 분석 결과가 생성되었습니다.");

        for (Map.Entry<String, AnalysisResult> entry : allResults.entrySet()) {
            System.out.println("\n=== " + entry.getKey() + " ===");
            System.out.println("분석 유형: " + entry.getValue().getAnalysisType());
        }
    }
}
