package day5.여행경로탐색;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Route {
    private String startLocation;
    private String destination;
    private List<String> waypoints;
    private double distance; //km
    private int duration; //분
    private double cost;

    public Route(String startLocation, String destination) {
        this.startLocation = startLocation;
        this.destination = destination;
        this.waypoints = new ArrayList<>();
    }

    public void addWaypoint(String waypoint) {
        waypoints.add(waypoint);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("경로 정보:\n");
        sb.append("출발지: ").append(startLocation).append("\n");
        sb.append("목적지: ").append(destination).append("\n");

        sb.append("경유지: ");
        if (waypoints.isEmpty()) {
            sb.append("없음");
        } else {
            sb.append(String.join(" → ", waypoints));
        }
        sb.append("\n");

        sb.append("총 거리: ").append(String.format("%.1f", distance)).append("km\n");
        sb.append("예상 소요 시간: ").append(formatDuration(duration)).append("\n");
        sb.append("예상 비용: ").append(String.format("%,.0f", cost)).append("원");

        return sb.toString();
    }

    /**
     * 시간(분)을 시간과 분 형식으로 변환하는 유틸리티 메소드
     *
     * @param minutes 총 소요 시간(분)
     * @return 변환된 시간 문자열 (예: "2시간 30분")
     */
    private String formatDuration(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;

        if (hours > 0) {
            return hours + "시간 " + mins + "분";
        } else {
            return mins + "분";
        }
    }
}
