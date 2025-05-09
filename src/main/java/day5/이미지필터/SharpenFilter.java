package day5.이미지필터;

/**
 * 구체적인 전략 클래스 (Concrete Strategy)
 * 선명화(샤프닝) 필터 알고리즘을 구현합니다.
 */
public class SharpenFilter implements ImageFilterStrategy {
    private double intensity; // 선명화 강도 (0.0 ~ 2.0)
    
    /**
     * 기본 선명화 강도(1.0)로 초기화하는 생성자
     */
    public SharpenFilter() {
        this.intensity = 1.0;
    }
    
    /**
     * 선명화 강도를 지정하는 생성자
     * 
     * @param intensity 선명화 강도 (0.0 ~ 2.0)
     */
    public SharpenFilter(double intensity) {
        setIntensity(intensity);
    }

    /**
     * 이미지에 선명화 필터를 적용하는 메소드
     * 각 픽셀의 대비를 높여 이미지를 더 선명하게 만듭니다.
     * 
     * @param image 필터를 적용할 이미지
     * @return 필터가 적용된 이미지
     */
    @Override
    public Image applyFilter(Image image) {
        // 원본 이미지의 복사본 생성
        Image filteredImage = image.copy();
        filteredImage.setName(image.getName() + " (선명화)");
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        // 모든 픽셀에 대해 처리
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 현재 픽셀과 주변 픽셀의 차이를 이용한 선명화 적용
                applySharpen(image, filteredImage, x, y);
            }
        }
        
        return filteredImage;
    }
    
    /**
     * 특정 픽셀에 선명화 알고리즘을 적용하는 메소드
     * 
     * @param srcImage 원본 이미지
     * @param destImage 결과 이미지
     * @param x X 좌표
     * @param y Y 좌표
     */
    private void applySharpen(Image srcImage, Image destImage, int x, int y) {
        int[] center = srcImage.getPixel(x, y);
        
        // 중심 픽셀과 주변 픽셀의 가중 평균 계산 (언샤프 마스킹 기법)
        double[] blurred = getBlurredPixel(srcImage, x, y);
        
        // 선명화 공식: 원본 + (원본 - 블러) * 강도
        int r = clamp((int)(center[0] + (center[0] - blurred[0]) * intensity), 0, 255);
        int g = clamp((int)(center[1] + (center[1] - blurred[1]) * intensity), 0, 255);
        int b = clamp((int)(center[2] + (center[2] - blurred[2]) * intensity), 0, 255);
        
        destImage.setPixel(x, y, r, g, b);
    }
    
    /**
     * 특정 픽셀 주변의 블러 값을 계산하는 메소드
     * 
     * @param image 이미지
     * @param centerX 중심 X 좌표
     * @param centerY 중심 Y 좌표
     * @return 블러 처리된 RGB 값 배열 [r, g, b]
     */
    private double[] getBlurredPixel(Image image, int centerX, int centerY) {
        double totalR = 0;
        double totalG = 0;
        double totalB = 0;
        int count = 0;
        
        // 3x3 커널을 이용한 블러 계산
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int x = centerX + dx;
                int y = centerY + dy;
                
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
            return new double[] {
                totalR / count,
                totalG / count,
                totalB / count
            };
        } else {
            return new double[] {0, 0, 0};
        }
    }
    
    /**
     * 값을 지정된 범위 내로 제한하는 유틸리티 메소드
     * 
     * @param value 원본 값
     * @param min 최소값
     * @param max 최대값
     * @return 범위 내로 제한된 값
     */
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * 필터 이름을 반환하는 메소드
     * 
     * @return 필터 이름
     */
    @Override
    public String getFilterName() {
        return "선명화 필터 (강도: " + intensity + ")";
    }
    
    /**
     * 선명화 강도를 설정하는 메소드
     * 
     * @param intensity 새 선명화 강도 (0.0 ~ 2.0)
     */
    public void setIntensity(double intensity) {
        this.intensity = Math.max(0.0, Math.min(2.0, intensity));
    }
    
    /**
     * 현재 선명화 강도를 반환하는 메소드
     * 
     * @return 선명화 강도
     */
    public double getIntensity() {
        return intensity;
    }
}
