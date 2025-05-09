package day5.데이터내보내기;

import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataExporter {
    @Setter
    private ExportStrategy exportStrategy;
    private Map<String, ExportStrategy> formatStrategies = new HashMap<>();

    public DataExporter(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    private String getFileExtension(String filePath) {
        int lastDotPos =filePath.lastIndexOf(".");
        if (lastDotPos > 0 && lastDotPos < filePath.length() - 1) {
            return filePath.substring(lastDotPos + 1).toLowerCase();
        }
        return "";
    }

    /**
     * 특정 파일 형식에 대한 내보내기 전략을 등록하는 메소드
     *
     * @param format 파일 형식 (예: "csv", "json", "xml" 등)
     * @param strategy 해당 형식에 사용할 내보내기 전략
     */
    public void registerStrategy(String format, ExportStrategy strategy) {
        formatStrategies.put(format.toLowerCase(), strategy);
    }


    /**
     * 데이터를 내보내는 메소드
     * 파일 확장자에 따라 적절한 전략을 자동으로 선택합니다.
     *
     * @param data 내보낼 데이터 목록
     * @param filePath 내보낸 파일이 저장될 경로
     * @return 내보내기 성공 여부
     */
    public boolean exportData(List<Map<String, Object>> data, String filePath) {
        // 파일 확장자 추출
        String extension = getFileExtension(filePath);

        // 확장자에 맞는 전략 선택, 없으면 기본 전략 사용
        ExportStrategy strategy = formatStrategies.getOrDefault(extension, exportStrategy);

        // 선택된 전략으로 데이터 내보내기
        return strategy.export(data, filePath);
    }

    /**
     * 지정된 형식으로 데이터를 내보내는 메소드
     *
     * @param data 내보낼 데이터 목록
     * @param filePath 내보낸 파일이 저장될 경로
     * @param format 사용할 내보내기 형식
     * @return 내보내기 성공 여부
     */
    public boolean exportData(List<Map<String, Object>> data, String filePath, String format) {
        // 지정된 형식에 맞는 전략 선택, 없으면 기본 전략 사용
        ExportStrategy strategy = formatStrategies.getOrDefault(format.toLowerCase(), exportStrategy);

        // 선택된 전략으로 데이터 내보내기
        return strategy.export(data, filePath);
    }
}
