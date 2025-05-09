package day5.여행경로탐색;

public class EconomicRouteStrategy implements RouteStrategy{

    @Override
    public Route findRoute(String start, String destination) {
        Route route = new Route(start, destination);

        // 1. 서울에서 부산으로 가는 가장 경제적인 경로(대중교통)
        if (start.equals("서울") && destination.equals("부산")) {
            route.addWaypoint("대전 (KTX 환승)");
            route.addWaypoint("동대구 (SRT 환승)");
            route.setDistance(420.0);
            route.setDuration(300);
            route.setCost(15000.0);
        } else if (start.equals("서울") && destination.equals("강릉")) {
            route.addWaypoint("횡성 (버스 환승)");
            route.setDistance(230.0);
            route.setDuration(210);
            route.setCost(10000.0);
        } else {
            double distance = Math.random() * 300 + 100;
            int duration = (int) (distance * 0.75);
            double cost = distance * 40;

            route.setDistance(distance);
            route.setDuration(duration);
            route.setCost(cost);
        }

        return route;
    }
}
