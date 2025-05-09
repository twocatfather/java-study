package day5.이미지필터;

/**
 * 구체적인 전략 클래스 (Concrete Strategy)
 * 흑백(그레이스케일) 필터 알고리즘을 구현합니다.
 */
public class GrayscaleFilter implements ImageFilterStrategy {

    /**
     * 이미지에 흑백 필터를 적용하는 메소드
     * 각 픽셀의 RGB 값을 평균하여 그레이스케일로 변환합니다.
     * 
     * @param image 필터를 적용할 이미지
     * @return 필터가 적용된 이미지
     */
    @Override
    public Image applyFilter(Image image) {
        // 원본 이미지의 복사본 생성
        Image filteredImage = image.copy();
        filteredImage.setName(image.getName() + " (흑백)");
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        // 모든 픽셀에 대해 처리
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] rgb = image.getPixel(x, y);
                
                // RGB 값의 평균 계산 (가중치 적용 가능)
                int gray = (rgb[0] + rgb[1] + rgb[2]) / 3;
                
                // 그레이스케일 값으로 픽셀 설정 (R=G=B)
                filteredImage.setPixel(x, y, gray, gray, gray);
            }
        }
        
        return filteredImage;
    }

    /**
     * 필터 이름을 반환하는 메소드
     * 
     * @return 필터 이름
     */
    @Override
    public String getFilterName() {
        return "흑백 필터";
    }
}
