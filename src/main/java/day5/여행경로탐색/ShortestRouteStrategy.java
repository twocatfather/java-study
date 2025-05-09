package day5.여행경로탐색;

public class ShortestRouteStrategy implements RouteStrategy{
    @Override
    public Route findRoute(String start, String destination) {
        Route route = new Route(start, destination);

        if (start.equals("서울") && destination.equals("부산")) {
            route.addWaypoint("안성");
            route.addWaypoint("충주");
            route.addWaypoint("문경");
            route.addWaypoint("대구");
            route.setDistance(370.0);
            route.setDuration(330);
            route.setCost(10000.0);
        } else if (start.equals("서울") && destination.equals("강릉")) {
            route.addWaypoint("홍천");
            route.addWaypoint("평창");
            route.setDistance(200.0);
            route.setDuration(180);
            route.setCost(12000.0);
        } else {
            double distance = Math.random() * 250 + 100;
            int duration = (int) (distance * 0.9);
            double cost = distance * 45;

            route.setDistance(distance);
            route.setDuration(duration);
            route.setCost(cost);
        }
        return route;
    }
}
