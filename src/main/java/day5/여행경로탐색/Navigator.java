package day5.여행경로탐색;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Navigator {
    @Setter
    private RouteStrategy routeStrategy;


    public Route navigate(String start, String destination) {
        if (routeStrategy == null) {
            throw new IllegalArgumentException("Route strategy is not set.");
        }

        return routeStrategy.findRoute(start, destination);
    }

    // 사용자 선호도에 따라서 적절한 경로로 전략을 자동으로 선택해주는 메소드
    public void setStrategyByPreference(String preference) {
        if (preference == null) {
            throw new IllegalArgumentException("Preference is null.");
        }

        switch (preference.toLowerCase()) {
            case "fastest" -> setRouteStrategy(new FastestRouteStrategy());
            case "economic" -> setRouteStrategy(new EconomicRouteStrategy());
            case "shortest" -> setRouteStrategy(new ShortestRouteStrategy());
            default -> throw new IllegalArgumentException("Invalid preference: " + preference);
        }
    }

    public String getStrategyType() {
        if (routeStrategy == null) {
            return "설정 되지 않았습니다.";
        } else if (routeStrategy instanceof FastestRouteStrategy) {
            return "가장 빠른 경로";
        } else if (routeStrategy instanceof EconomicRouteStrategy) {
            return "가장 경제적인 경로";
        } else if (routeStrategy instanceof ShortestRouteStrategy) {
            return "가장 짧은 경로";
        } else {
            return "기타 경로 전략";
        }
    }
}
