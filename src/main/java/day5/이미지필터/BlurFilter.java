package day5.이미지필터;

/**
 * 구체적인 전략 클래스 (Concrete Strategy)
 * 흐림(블러) 필터 알고리즘을 구현합니다.
 */
public class BlurFilter implements ImageFilterStrategy {
    private int blurRadius; // 블러 반경
    
    /**
     * 기본 블러 반경(1)으로 초기화하는 생성자
     */
    public BlurFilter() {
        this.blurRadius = 1;
    }
    
    /**
     * 블러 반경을 지정하는 생성자
     * 
     * @param blurRadius 블러 반경
     */
    public BlurFilter(int blurRadius) {
        this.blurRadius = Math.max(1, blurRadius);
    }

    /**
     * 이미지에 흐림 필터를 적용하는 메소드
     * 각 픽셀을 주변 픽셀의 평균값으로 설정합니다.
     * 
     * @param image 필터를 적용할 이미지
     * @return 필터가 적용된 이미지
     */
    @Override
    public Image applyFilter(Image image) {
        // 원본 이미지의 복사본 생성
        Image filteredImage = image.copy();
        filteredImage.setName(image.getName() + " (흐림)");
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        // 모든 픽셀에 대해 처리
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 주변 픽셀 평균 계산
                int[] avgColor = getAverageColor(image, x, y);
                
                // 계산된 색상으로 픽셀 설정
                filteredImage.setPixel(x, y, avgColor[0], avgColor[1], avgColor[2]);
            }
        }
        
        return filteredImage;
    }
    
    /**
     * 특정 좌표 주변 픽셀들의 평균 색상을 계산하는 메소드
     * 
     * @param image 이미지
     * @param centerX 중심 X 좌표
     * @param centerY 중심 Y 좌표
     * @return 평균 RGB 값 배열 [r, g, b]
     */
    private int[] getAverageColor(Image image, int centerX, int centerY) {
        int totalR = 0;
        int totalG = 0;
        int totalB = 0;
        int count = 0;
        
        // 지정된 반경 내의 모든 픽셀 탐색
        for (int y = centerY - blurRadius; y <= centerY + blurRadius; y++) {
            for (int x = centerX - blurRadius; x <= centerX + blurRadius; x++) {
                // 이미지 경계 체크
                if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                    int[] rgb = image.getPixel(x, y);
                    totalR += rgb[0];
                    totalG += rgb[1];
                    totalB += rgb[2];
                    count++;
                }
            }
        }
        
        // 평균 계산
        if (count > 0) {
            return new int[] {
                totalR / count,
                totalG / count,
                totalB / count
            };
        } else {
            return new int[] {0, 0, 0};
        }
    }

    /**
     * 필터 이름을 반환하는 메소드
     * 
     * @return 필터 이름
     */
    @Override
    public String getFilterName() {
        return "흐림 필터 (반경: " + blurRadius + ")";
    }
    
    /**
     * 블러 반경을 설정하는 메소드
     * 
     * @param blurRadius 새 블러 반경
     */
    public void setBlurRadius(int blurRadius) {
        this.blurRadius = Math.max(1, blurRadius);
    }
    
    /**
     * 현재 블러 반경을 반환하는 메소드
     * 
     * @return 블러 반경
     */
    public int getBlurRadius() {
        return blurRadius;
    }
}
