package day5.데이터내보내기;

import java.util.List;
import java.util.Map;

public interface ExportStrategy {
    boolean export(List<Map<String, Object>> data, String filePath);
}
