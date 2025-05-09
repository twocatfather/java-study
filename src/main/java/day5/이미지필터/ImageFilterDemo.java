package day5.이미지필터;

import java.util.Arrays;
import java.util.List;

/**
 * 이미지 필터 전략 패턴 예제의 실행 클래스
 * 다양한 이미지 필터 전략을 사용하는 방법을 보여줍니다.
 */
public class ImageFilterDemo {
    public static void main(String[] args) {
        // 테스트용 이미지 생성 (랜덤 색상의 20x10 이미지)
        Image originalImage = new Image(20, 10, "원본 이미지");
        originalImage.fillRandom();
        
        // 이미지 처리기 생성
        ImageProcessor processor = new ImageProcessor();
        
        // 다양한 필터 전략 생성 및 등록
        processor.registerFilter("grayscale", new GrayscaleFilter());
        processor.registerFilter("blur", new BlurFilter(1));
        processor.registerFilter("strongBlur", new BlurFilter(3));
        processor.registerFilter("sharpen", new SharpenFilter(1.0));
        processor.registerFilter("strongSharpen", new SharpenFilter(1.5));
        
        System.out.println("=== 이미지 필터 시스템 데모 ===\n");
        
        // 원본 이미지 출력
        System.out.println("원본 이미지:");
        System.out.println(originalImage.toAsciiArt());
        
        // 1. 흑백 필터 적용
        System.out.println("\n1. 흑백 필터 적용");
        processor.selectFilter("grayscale");
        System.out.println("현재 필터: " + processor.getCurrentFilterName());
        Image grayscaleImage = processor.applyFilter(originalImage);
        System.out.println(grayscaleImage.toAsciiArt());
        
        // 2. 흐림 필터 적용
        System.out.println("\n2. 흐림 필터 적용");
        processor.selectFilter("blur");
        System.out.println("현재 필터: " + processor.getCurrentFilterName());
        Image blurImage = processor.applyFilter(originalImage);
        System.out.println(blurImage.toAsciiArt());
        
        // 3. 강한 흐림 필터 적용
        System.out.println("\n3. 강한 흐림 필터 적용");
        processor.selectFilter("strongBlur");
        System.out.println("현재 필터: " + processor.getCurrentFilterName());
        Image strongBlurImage = processor.applyFilter(originalImage);
        System.out.println(strongBlurImage.toAsciiArt());
        
        // 4. 선명화 필터 적용
        System.out.println("\n4. 선명화 필터 적용");
        processor.selectFilter("sharpen");
        System.out.println("현재 필터: " + processor.getCurrentFilterName());
        Image sharpenImage = processor.applyFilter(originalImage);
        System.out.println(sharpenImage.toAsciiArt());
        
        // 5. 필터 체인 적용 (흑백 -> 흐림 -> 선명화)
        System.out.println("\n5. 필터 체인 적용 (흑백 -> 흐림 -> 선명화)");
        List<String> filterChain = Arrays.asList("grayscale", "blur", "sharpen");
        Image chainedImage = processor.applyFilterChain(originalImage, filterChain);
        System.out.println(chainedImage.toAsciiArt());
        
        // 필터 기록 확인
        System.out.println("\n=== 필터 적용 기록 ===");
        List<Image> history = processor.getFilterHistory();
        System.out.println("총 " + history.size() + "개의 필터가 적용되었습니다:");
        
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i).getName());
        }
        
        // 등록된 필터 목록 확인
        System.out.println("\n=== 등록된 필터 목록 ===");
        List<String> filterNames = processor.getRegisteredFilterNames();
        for (String name : filterNames) {
            System.out.println("- " + name);
        }
        
        // 전략 패턴의 장점을 설명하는 주석
        /*
         * 전략 패턴의 장점:
         * 1. 다양한 이미지 필터 알고리즘을 캡슐화하여 필요에 따라 교체 가능
         * 2. 새로운 필터 알고리즘 추가가 용이함 (OCP 원칙)
         *    - 예: SepiaFilter, InvertFilter, EdgeDetectionFilter 등
         * 3. 클라이언트 코드(ImageProcessor)는 구체적인 알고리즘에 의존하지 않음 (DIP 원칙)
         * 4. 복잡한 조건문을 제거하고 코드 가독성 향상
         * 5. 필터 체인 적용, 기록 저장 등 고급 기능 구현이 용이
         */
    }
}
