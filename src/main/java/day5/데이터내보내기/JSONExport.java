package day5.데이터내보내기;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@AllArgsConstructor
public class JSONExport implements ExportStrategy{
    private boolean isPrettyPrint;

    public JSONExport() {
        this.isPrettyPrint = true;
    }

    @Override
    public boolean export(List<Map<String, Object>> data, String filePath) {
        if (data == null || data.isEmpty()) {
            System.out.println("내보낼 데이터가 없습니다.");
            return false;
        }

        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write("[");
            writer.write(isPrettyPrint ? System.lineSeparator() : "");

            // 각 데이터 항목을 JSON 객체로 변환
            for (int i = 0; i < data.size(); i++) {
                Map<String, Object> item = data.get(i);

                // JSON 객체 시작
                writer.write(isPrettyPrint ? "  {" : "{");
                writer.write(isPrettyPrint ? System.lineSeparator() : "");

                int keyCount = 0;
                for (Map.Entry<String, Object> entry : item.entrySet()) {
                    if (isPrettyPrint) {
                        writer.write("    ");
                    }

                    writer.write("\"" + entry.getValue() + "\":");

                    if (entry.getValue() == null) {
                        writer.write("null");
                    } else if (entry.getValue() instanceof Number) {
                        writer.write(entry.getValue().toString());
                    } else if (entry.getValue() instanceof Boolean) {
                        writer.write(entry.getValue().toString());
                    } else {
                        writer.write("\"" + entry.getValue().toString().replace("\"", "\\\"") + "\"");
                    }

                    if (++keyCount < item.size()) {
                        writer.write(",");
                    }

                    writer.write(isPrettyPrint ? System.lineSeparator() : "");
                }

                writer.write(isPrettyPrint ? "  }" : "}");

                // 마지막 항목이 아니면 쉼표
                if (i < data.size() - 1) {
                    writer.write(",");
                }

                writer.write(isPrettyPrint ? System.lineSeparator() : "");
            }

            writer.write("]");
            System.out.println("데이터를 JSON 파일로 성공적으로 내보냈습니다: " + filePath);
            return true;

        } catch (Exception e) {
            System.out.println("Json 파일 내보내기 실패: " + e.getMessage());
            return false;
        }
    }
}
