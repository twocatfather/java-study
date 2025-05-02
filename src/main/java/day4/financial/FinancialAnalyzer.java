package day4.financial;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FinancialAnalyzer {
    private List<Transaction> transactions;

    public FinancialAnalyzer() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> filterTransactions(Predicate<Transaction> criteria) {
        return transactions.stream()
                .filter(criteria)
                .toList();
    }

    // 통계 계산 메서드
    public <R> R calculateStatistics(
            Predicate<Transaction> filter,
            Function<List<Transaction>, R> statCalculator
    ) {
        List<Transaction> filteredTransactions = filterTransactions(filter);
        return statCalculator.apply(filteredTransactions);
    }


    public void addTransactions(List<Transaction> sampleTransactions) {
        this.transactions.addAll(sampleTransactions);
    }

    public Map<Month, Map<String, Double>> getMonthlyFinanceSummary() {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getMonth,
                        Collectors.groupingBy(
                                Transaction::getType,
                                Collectors.summingDouble(Transaction::getAmount)
                        )
                ));
    }

    public Map<String, Double> getCategoryExpenses() {
        return transactions.stream()
                .filter(tx -> "expense".equals(tx.getType()))
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    public List<Map.Entry<String, Double>> getTopExpenseCategories(int limit) {
        return getCategoryExpenses().entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(limit)
                .toList();
    }

    // 수입 / 지출 비율 계산
    public double getExpenseToIncomeRatio() {
        double totalIncome = transactions.stream()
                .filter(tx -> "income".equals(tx.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = transactions.stream()
                .filter(tx -> "expense".equals(tx.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return (totalIncome > 0) ? (totalExpense / totalIncome) : 0.0;
    }

    // 이상치 거래 감지 메서드
    public List<Transaction> detectAnomalies(String type, double stdDeviations) {
        List<Transaction> filteredTransactions = filterTransactions(tx -> type.equals(tx.getType()));

        // 평균과 표준 편차 계산
        DoubleSummaryStatistics doubleSummaryStatistics = filteredTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();

        double mean = doubleSummaryStatistics.getAverage();

        // 표준편차를 계산
        double variance = filteredTransactions.stream()
                .mapToDouble(tx -> Math.pow(tx.getAmount() - mean, 2))
                .average()
                .orElse(0.0);

        double stdDev = Math.sqrt(variance);
        double threshold  = mean + (stdDev * stdDeviations);

        return filteredTransactions.stream()
                .filter(tx -> tx.getAmount() > threshold)
                .toList();
    }


    public List<Transaction> getTransactionByDateRange(LocalDate startDate, LocalDate endDate) {
        return filterTransactions(tx ->
                !tx.getDate().isBefore(startDate) && !tx.getDate().isAfter(endDate));
    }

    public Map<Month, Double> getMonthlyExpenseTrend() {
        return transactions.stream()
                .filter(tx -> "expense".equals(tx.getType()))
                .collect(Collectors.groupingBy(
                        Transaction::getMonth,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    public Map<Month, Double> getCategoryMonthlyTrend(String category) {
        return transactions.stream()
                .filter(tx -> "expense".equals(tx.getType()) && category.equals(tx.getCategory()))
                .collect(Collectors.groupingBy(
                        Transaction::getMonth,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    public Optional<Map.Entry<LocalDate, Double>> getHighestExpenseDay() {
        Map<LocalDate, Double> dailyExpenses = transactions.stream()
                .filter(tx -> "expense".equals(tx.getType()))
                .collect(Collectors.groupingBy(
                        Transaction::getDate,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        return dailyExpenses.entrySet().stream()
                .max(Map.Entry.comparingByValue());
    }



    public static void main(String[] args) {
        FinancialAnalyzer analyzer = new FinancialAnalyzer();

        // 샘플 데이터 초기화
        initializeSampleTransactions(analyzer);

        // 1. 람다식을 활용한 총 지출 계산
        Double totalExpenses = analyzer.calculateStatistics(
                tx -> "expense".equals(tx.getType()),
                txList -> txList.stream()
                        .mapToDouble(Transaction::getAmount)
                        .sum()
        );

        System.out.printf("총 지출: %,.0f원\n", totalExpenses);

        // 2. 카테고리별 지출 계산
        Map<String, Double> categoryExpenses = analyzer.calculateStatistics(
                tx -> "expense".equals(tx.getType()),
                txList -> txList.stream()
                        .collect(Collectors.groupingBy(
                                Transaction::getCategory,
                                Collectors.summingDouble(Transaction::getAmount)
                        )));

        System.out.println("\n2. 카테고리별 지출:");
        categoryExpenses.forEach((category, amount) ->
                System.out.printf("   %s: %,.0f원\n", category, amount));

        // 3. 상위 지출 카테고리 추출
        List<Map.Entry<String, Double>> topCategories = analyzer.getTopExpenseCategories(3);

        System.out.println("\n3. 상위 지출 카테고리 (TOP 3):");
        for (int i = 0; i < topCategories.size(); i++) {
            System.out.printf("   %d. %s: %,.0f원\n",
                    i + 1,
                    topCategories.get(i).getKey(),
                    topCategories.get(i).getValue());
        }

        // 4. 월별 수입/지출 요약
        Map<Month, Map<String, Double>> monthlySummary = analyzer.getMonthlyFinanceSummary();

        System.out.println("\n4. 월별 수입/지출 요약:");
        monthlySummary.forEach((month, typeMap) -> {
            double income = typeMap.getOrDefault("income", 0.0);
            double expense = typeMap.getOrDefault("expense", 0.0);
            System.out.printf("   %s: 수입 %,.0f원, 지출 %,.0f원, 잔액 %,.0f원\n",
                    month, income, expense, income - expense);
        });

        // 5. 수입/지출 비율 계산
        double expenseRatio = analyzer.getExpenseToIncomeRatio();

        System.out.printf("\n5. 수입 대비 지출 비율: %.1f%%\n", expenseRatio);

        // 6. 이상치 거래 감지 (평균 + 1.5 * 표준편차 초과)
        List<Transaction> anomalies = analyzer.detectAnomalies("expense", 1.5);

        System.out.println("\n6. 이상치 지출 거래 (평균의 1.5 표준편차 초과):");
        if (anomalies.isEmpty()) {
            System.out.println("   이상치 거래가 없습니다.");
        } else {
            anomalies.forEach(tx -> System.out.println("   " + tx));
        }

        // 7. 특정 날짜 범위의 거래 조회
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 31);
        List<Transaction> marchTransactions = analyzer.getTransactionByDateRange(startDate, endDate);

        System.out.println("\n7. 3월 거래 내역:");
        marchTransactions.forEach(tx -> System.out.println("   " + tx));

        // 8. 월별 지출 트렌드
        Map<Month, Double> monthlyTrend = analyzer.getMonthlyExpenseTrend();

        System.out.println("\n8. 월별 지출 트렌드:");
        monthlyTrend.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("   %s: %,.0f원\n", entry.getKey(), entry.getValue()));

        // 9. 특정 카테고리의 월별 트렌드
        String targetCategory = "식비";
        Map<Month, Double> categoryTrend = analyzer.getCategoryMonthlyTrend(targetCategory);

        System.out.printf("\n9. '%s' 카테고리의 월별 트렌드:\n", targetCategory);
        categoryTrend.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("   %s: %,.0f원\n", entry.getKey(), entry.getValue()));

        // 10. 지출이 가장 많았던 날
        Optional<Map.Entry<LocalDate, Double>> highestDay = analyzer.getHighestExpenseDay();

        System.out.println("\n10. 지출이 가장 많았던 날:");
        highestDay.ifPresentOrElse(
                entry -> System.out.printf("   %s: %,.0f원\n",
                        entry.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        entry.getValue()),
                () -> System.out.println("   지출 내역이 없습니다.")
        );
    }

    /**
     * 샘플 거래 데이터 초기화
     * @param analyzer 거래를 추가할 FinancialAnalyzer 인스턴스
     */
    private static void initializeSampleTransactions(FinancialAnalyzer analyzer) {
        List<Transaction> sampleTransactions = new ArrayList<>();

        // 1월 거래
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 1, 5), 3000000, "income", "급여", "1월 급여"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 1, 10), 450000, "expense", "주거비", "1월 월세"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 1, 15), 120000, "expense", "식비", "식료품 구매"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 1, 20), 85000, "expense", "교통비", "교통카드 충전"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 1, 25), 200000, "expense", "통신비", "휴대폰, 인터넷 요금"));

        // 2월 거래
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 5), 3000000, "income", "급여", "2월 급여"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 7), 150000, "expense", "의료비", "병원 진료"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 10), 450000, "expense", "주거비", "2월 월세"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 14), 100000, "expense", "의류", "옷 구매"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 15), 130000, "expense", "식비", "식료품 구매"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 20), 90000, "expense", "교통비", "교통카드 충전"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 2, 25), 200000, "expense", "통신비", "휴대폰, 인터넷 요금"));

        // 3월 거래
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 5), 3000000, "income", "급여", "3월 급여"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 10), 450000, "expense", "주거비", "3월 월세"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 12), 180000, "expense", "식비", "외식"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 15), 140000, "expense", "식비", "식료품 구매"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 18), 500000, "expense", "여가", "콘서트 티켓"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 20), 95000, "expense", "교통비", "교통카드 충전"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 23), 1200000, "expense", "여행", "제주도 여행 경비"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 25), 200000, "expense", "통신비", "휴대폰, 인터넷 요금"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 3, 28), 50000, "income", "이자수익", "예금 이자"));

        // 4월 거래
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 5), 3100000, "income", "급여", "4월 급여 (인상)"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 10), 450000, "expense", "주거비", "4월 월세"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 15), 130000, "expense", "식비", "식료품 구매"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 20), 85000, "expense", "교통비", "교통카드 충전"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 22), 320000, "expense", "의류", "여름 옷 구매"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 25), 200000, "expense", "통신비", "휴대폰, 인터넷 요금"));
        sampleTransactions.add(new Transaction(
                LocalDate.of(2023, 4, 30), 300000, "expense", "선물", "어버이날 선물"));

        analyzer.addTransactions(sampleTransactions);
    }
}
