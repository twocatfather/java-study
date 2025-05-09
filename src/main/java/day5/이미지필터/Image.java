package day5.이미지필터;

/**
 * 이미지를 표현하는 클래스
 * 간단한 구현을 위해 실제 이미지 데이터 대신 RGB 픽셀 배열을 사용합니다.
 */
public class Image {
    private final int width;           // 이미지 너비
    private final int height;          // 이미지 높이
    private final int[][][] pixels;    // RGB 픽셀 배열 [y][x][rgb]
    private String name;               // 이미지 이름
    
    /**
     * 지정된 크기의 빈 이미지를 생성하는 생성자
     * 
     * @param width 이미지 너비
     * @param height 이미지 높이
     * @param name 이미지 이름
     */
    public Image(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.pixels = new int[height][width][3];
    }
    
    /**
     * 이미지의 깊은 복사본을 생성하는 메소드
     * 
     * @return 이미지의 복사본
     */
    public Image copy() {
        Image copy = new Image(width, height, name + " (복사본)");
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                copy.pixels[y][x][0] = this.pixels[y][x][0]; // R
                copy.pixels[y][x][1] = this.pixels[y][x][1]; // G
                copy.pixels[y][x][2] = this.pixels[y][x][2]; // B
            }
        }
        
        return copy;
    }
    
    /**
     * 이미지를 랜덤 컬러로 채우는 메소드
     */
    public void fillRandom() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x][0] = (int)(Math.random() * 256); // R
                pixels[y][x][1] = (int)(Math.random() * 256); // G
                pixels[y][x][2] = (int)(Math.random() * 256); // B
            }
        }
    }
    
    /**
     * 특정 위치의 픽셀 값을 설정하는 메소드
     * 
     * @param x X 좌표
     * @param y Y 좌표
     * @param r 빨간색 값 (0-255)
     * @param g 녹색 값 (0-255)
     * @param b 파란색 값 (0-255)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = clamp(r, 0, 255);
            pixels[y][x][1] = clamp(g, 0, 255);
            pixels[y][x][2] = clamp(b, 0, 255);
        }
    }
    
    /**
     * 특정 위치의 픽셀 값을 가져오는 메소드
     * 
     * @param x X 좌표
     * @param y Y 좌표
     * @return RGB 값 배열 [r, g, b]
     */
    public int[] getPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return new int[] {
                pixels[y][x][0],
                pixels[y][x][1],
                pixels[y][x][2]
            };
        }
        return new int[] {0, 0, 0};
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
    
    // Getter 메소드
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 이미지 정보를 문자열로 반환하는 메소드
     */
    @Override
    public String toString() {
        return String.format("이미지: %s (%dx%d)", name, width, height);
    }
    
    /**
     * 이미지를 ASCII 아트로 표현하는 메소드
     * 간단한 시각화를 위해 사용
     * 
     * @return ASCII 아트 문자열
     */
    public String toAsciiArt() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString()).append("\n");
        
        // 크기가 너무 크면 축소된 버전 생성
        int stepX = Math.max(1, width / 40);
        int stepY = Math.max(1, height / 20);
        
        for (int y = 0; y < height; y += stepY) {
            for (int x = 0; x < width; x += stepX) {
                int[] pixel = getPixel(x, y);
                // 평균 밝기 계산
                int brightness = (pixel[0] + pixel[1] + pixel[2]) / 3;
                // 밝기에 따라 ASCII 문자 선택
                char c = getAsciiChar(brightness);
                sb.append(c);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * 밝기 값에 따라 ASCII 문자를 선택하는 메소드
     * 
     * @param brightness 밝기 값 (0-255)
     * @return ASCII 문자
     */
    private char getAsciiChar(int brightness) {
        // 밝기에 따른 ASCII 문자 배열 (어두운 것부터 밝은 것 순)
        char[] asciiChars = {' ', '.', ':', '-', '=', '+', '*', '#', '%', '@'};
        int index = brightness * (asciiChars.length - 1) / 255;
        return asciiChars[index];
    }
}
