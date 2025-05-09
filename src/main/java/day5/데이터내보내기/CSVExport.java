package day5.데이터내보내기;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVExport implements ExportStrategy {
    private String delimiter;  // CSV 구분자 (기본값: 쉼표)
    private boolean includeHeader; // 헤더 포함 여부

    /**
     * 기본 구분자(쉼표)와 헤더 포함 설정으로 초기화하는 생성자
     */
    public CSVExport() {
        this.delimiter = ",";
        this.includeHeader = true;
    }

    /**
     * 사용자 정의 구분자와 헤더 포함 설정으로 초기화하는 생성자
     *
     * @param delimiter     CSV 구분자
     * @param includeHeader 헤더 포함 여부
     */
    public CSVExport(String delimiter, boolean includeHeader) {
        this.delimiter = delimiter;
        this.includeHeader = includeHeader;
    }

    /**
     * 데이터를 CSV 형식으로 내보내는 메소드
     *
     * @param data     내보낼 데이터 목록
     * @param filePath 내보낸 파일이 저장될 경로
     * @return 내보내기 성공 여부
     */
    @Override
    public boolean export(List<Map<String, Object>> data, String filePath) {
        if (data == null || data.isEmpty()) {
            System.out.println("내보낼 데이터가 없습니다.");
            return false;
        }

        try (FileWriter writer = new FileWriter(new File(filePath))) {
            // 헤더 작성 (첫 번째 데이터 항목의 키를 사용)
            if (includeHeader) {
                String header = data.get(0).keySet().stream()
                        .collect(Collectors.joining(delimiter));
                writer.write(header + System.lineSeparator());
            }

            // 데이터 항목 작성
            for (Map<String, Object> item : data) {
                String row = item.values().stream()
                        .map(value -> value != null ? value.toString() : "")
                        .collect(Collectors.joining(delimiter));
                writer.write(row + System.lineSeparator());
            }

            System.out.println("데이터를 CSV 파일로 성공적으로 내보냈습니다: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("CSV 파일 내보내기 실패: " + e.getMessage());
            return false;
        }
    }

}