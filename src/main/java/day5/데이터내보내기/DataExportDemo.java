package day5.데이터내보내기;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataExportDemo {
    public static void main(String[] args) {
        // 테스트용 데이터 생성
        List<Map<String, Object>> employeeData = createSampleData();

        // 기본 전략으로 CSV 내보내기를 사용하는 DataExporter 생성
        DataExporter exporter = new DataExporter(new CSVExport());

        // 다양한 형식의 내보내기 전략 등록
        exporter.registerStrategy("csv", new CSVExport());
        exporter.registerStrategy("json", new JSONExport());
        exporter.registerStrategy("xml", new XMLExport("employees", "employee", true));

        // CSV 형식으로 내보내기 (자동 형식 감지)
        System.out.println("\n=== CSV 형식으로 내보내기 ===");
        exporter.exportData(employeeData, "employees.csv");

        // JSON 형식으로 내보내기 (자동 형식 감지)
        System.out.println("\n=== JSON 형식으로 내보내기 ===");
        exporter.exportData(employeeData, "employees.json");

        // XML 형식으로 내보내기 (자동 형식 감지)
        System.out.println("\n=== XML 형식으로 내보내기 ===");
        exporter.exportData(employeeData, "employees.xml");

        // 명시적으로 형식 지정하여 내보내기
        System.out.println("\n=== 명시적으로 JSON 형식 지정하여 내보내기 ===");
        exporter.exportData(employeeData, "custom_output.dat", "json");

        // 사용자 정의 CSV 내보내기 설정 (세미콜론 구분자, 헤더 없음)
        System.out.println("\n=== 사용자 정의 CSV 내보내기 설정 사용 ===");
        exporter.setExportStrategy(new CSVExport(";", false));
        exporter.exportData(employeeData, "employees_custom.csv");
    }

    /**
     * 테스트용 샘플 데이터를 생성하는 메소드
     *
     * @return 직원 정보가 담긴 맵 리스트
     */
    private static List<Map<String, Object>> createSampleData() {
        List<Map<String, Object>> data = new ArrayList<>();

        // 첫 번째 직원 정보
        Map<String, Object> employee1 = new HashMap<>();
        employee1.put("id", 1);
        employee1.put("name", "홍길동");
        employee1.put("department", "개발팀");
        employee1.put("salary", 5000000);
        employee1.put("isManager", false);
        data.add(employee1);

        // 두 번째 직원 정보
        Map<String, Object> employee2 = new HashMap<>();
        employee2.put("id", 2);
        employee2.put("name", "김철수");
        employee2.put("department", "마케팅팀");
        employee2.put("salary", 4500000);
        employee2.put("isManager", false);
        data.add(employee2);

        // 세 번째 직원 정보
        Map<String, Object> employee3 = new HashMap<>();
        employee3.put("id", 3);
        employee3.put("name", "이영희");
        employee3.put("department", "개발팀");
        employee3.put("salary", 7000000);
        employee3.put("isManager", true);
        data.add(employee3);

        return data;
    }
}
