package day5.이미지필터;

/**
 * 전략 패턴에서 '전략' 역할을 하는 인터페이스
 * 다양한 이미지 필터 알고리즘이 구현해야 할 공통 인터페이스를 정의합니다.
 */
public interface ImageFilterStrategy {
    /**
     * 이미지에 필터를 적용하는 메소드
     * 
     * @param image 필터를 적용할 이미지
     * @return 필터가 적용된 이미지
     */
    Image applyFilter(Image image);
    
    /**
     * 필터 이름을 반환하는 메소드
     * 
     * @return 필터 이름
     */
    String getFilterName();
}
