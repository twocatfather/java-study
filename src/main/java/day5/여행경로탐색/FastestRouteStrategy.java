package day5.여행경로탐색;

public class FastestRouteStrategy implements RouteStrategy{

    @Override
    public Route findRoute(String start, String destination) {
        Route route = new Route(start, destination);

        if (start.equals("서울") && destination.equals("부산")) {
            route.addWaypoint("천안");
            route.addWaypoint("대전");
            route.addWaypoint("대구");
            route.setDistance(400.0);
            route.setDuration(240);
            route.setCost(25000.0);
        } else if (start.equals("서울") && destination.equals("강릉")) {
            route.addWaypoint("춘천");
            route.setDistance(220.0);
            route.setDuration(150);
            route.setCost(15000.0);
        } else {
            double distance = Math.random() * 300 + 100;
            int duration = (int) (distance * 0.6);
            double cost = distance * 60;

            route.setDistance(distance);
            route.setDuration(duration);
            route.setCost(cost);
        }
        return route;
    }
}
