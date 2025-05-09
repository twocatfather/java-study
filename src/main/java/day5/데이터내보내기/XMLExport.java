package day5.데이터내보내기;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XMLExport implements ExportStrategy {
    private String rootElementName;     // 루트 엘리먼트 이름
    private String itemElementName;     // 각 항목의 엘리먼트 이름
    private boolean includeXmlDeclaration; // XML 선언 포함 여부

    /**
     * 기본 설정으로 초기화하는 생성자
     */
    public XMLExport() {
        this.rootElementName = "data";
        this.itemElementName = "item";
        this.includeXmlDeclaration = true;
    }

    /**
     * 사용자 정의 설정으로 초기화하는 생성자
     *
     * @param rootElementName 루트 엘리먼트 이름
     * @param itemElementName 각 항목의 엘리먼트 이름
     * @param includeXmlDeclaration XML 선언 포함 여부
     */
    public XMLExport(String rootElementName, String itemElementName, boolean includeXmlDeclaration) {
        this.rootElementName = rootElementName;
        this.itemElementName = itemElementName;
        this.includeXmlDeclaration = includeXmlDeclaration;
    }

    /**
     * 데이터를 XML 형식으로 내보내는 메소드
     *
     * @param data 내보낼 데이터 목록
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
            // XML 선언
            if (includeXmlDeclaration) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            }

            // 루트 엘리먼트 시작
            writer.write("<" + rootElementName + ">\n");

            // 각 데이터 항목을 XML 엘리먼트로 변환
            for (Map<String, Object> item : data) {
                writer.write("  <" + itemElementName + ">\n");

                // 항목의 속성들을 하위 엘리먼트로 작성
                for (Map.Entry<String, Object> entry : item.entrySet()) {
                    writer.write("    <" + entry.getKey() + ">");

                    // 값이 null이면 빈 엘리먼트 작성
                    if (entry.getValue() != null) {
                        // XML 특수 문자 이스케이프 처리
                        String value = entry.getValue().toString()
                                .replace("&", "&amp;")
                                .replace("<", "&lt;")
                                .replace(">", "&gt;")
                                .replace("\"", "&quot;")
                                .replace("'", "&apos;");
                        writer.write(value);
                    }

                    writer.write("</" + entry.getKey() + ">\n");
                }

                writer.write("  </" + itemElementName + ">\n");
            }

            // 루트 엘리먼트 종료
            writer.write("</" + rootElementName + ">");

            System.out.println("데이터를 XML 파일로 성공적으로 내보냈습니다: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("XML 파일 내보내기 실패: " + e.getMessage());
            return false;
        }
    }
}
