package day5.여행경로탐색;

public class RouteStrategyDemo {
    public static void main(String[] args) {
        // 출발지와 목적지 설정
        String start = "서울";
        String destination = "부산";

        // 네비게이터 생성 (초기 전략 없음)
        Navigator navigator = new Navigator();

        System.out.println("=== '서울'에서 '부산'까지의 경로 탐색 ===\n");

        // 가장 빠른 경로 전략 설정
        System.out.println("1. 가장 빠른 경로 탐색");
        navigator.setRouteStrategy(new FastestRouteStrategy());
        System.out.println("현재 탐색 전략: " + navigator.getStrategyType());
        Route fastestRoute = navigator.navigate(start, destination);
        System.out.println(fastestRoute);

        System.out.println("\n2. 가장 짧은 경로 탐색");
        navigator.setRouteStrategy(new ShortestRouteStrategy());
        System.out.println("현재 탐색 전략: " + navigator.getStrategyType());
        Route shortestRoute = navigator.navigate(start, destination);
        System.out.println(shortestRoute);

        System.out.println("\n3. 가장 경제적인 경로 탐색");
        navigator.setRouteStrategy(new EconomicRouteStrategy());
        System.out.println("현재 탐색 전략: " + navigator.getStrategyType());
        Route economicRoute = navigator.navigate(start, destination);
        System.out.println(economicRoute);

        // 선호도에 따른 전략 자동 설정
        System.out.println("\n=== 선호도에 따른 전략 자동 설정 ===");
        navigator.setStrategyByPreference("economic");
        System.out.println("선호도에 따른 현재 탐색 전략: " + navigator.getStrategyType());

        // 다른 출발지와 목적지 설정
        System.out.println("\n=== '서울'에서 '강릉'까지의 경로 탐색 ===");
        start = "서울";
        destination = "강릉";

        // 각 전략별로 경로 탐색
        navigator.setStrategyByPreference("fastest");
        System.out.println("\n1. 가장 빠른 경로 탐색");
        System.out.println(navigator.navigate(start, destination));

        navigator.setStrategyByPreference("shortest");
        System.out.println("\n2. 가장 짧은 경로 탐색");
        System.out.println(navigator.navigate(start, destination));

        navigator.setStrategyByPreference("economic");
        System.out.println("\n3. 가장 경제적인 경로 탐색");
        System.out.println(navigator.navigate(start, destination));
    }
}
