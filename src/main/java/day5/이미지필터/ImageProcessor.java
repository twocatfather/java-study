package day5.이미지필터;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 컨텍스트 클래스 (Context)
 * 전략 객체를 사용하여 이미지 필터 처리를 수행하는 클래스입니다.
 * 클라이언트는 이 클래스를 통해 다양한 이미지 필터를 적용할 수 있습니다.
 */
public class ImageProcessor {
    private ImageFilterStrategy currentFilter; // 현재 사용 중인 필터 전략
    private Map<String, ImageFilterStrategy> registeredFilters; // 등록된 필터들
    private List<Image> filterHistory; // 필터 적용 기록
    
    /**
     * 기본 생성자
     * 등록된 필터가 없는 상태로 초기화
     */
    public ImageProcessor() {
        this.registeredFilters = new HashMap<>();
        this.filterHistory = new ArrayList<>();
    }
    
    /**
     * 생성자를 통해 필터 전략을 주입받습니다.
     * 
     * @param filter 사용할 필터 전략
     */
    public ImageProcessor(ImageFilterStrategy filter) {
        this();
        this.currentFilter = filter;
    }
    
    /**
     * 필터 전략을 설정하는 메소드
     * 
     * @param filter 새로운 필터 전략
     */
    public void setFilter(ImageFilterStrategy filter) {
        this.currentFilter = filter;
    }
    
    /**
     * 필터를 이름으로 등록하는 메소드
     * 
     * @param name 필터 이름
     * @param filter 등록할 필터 전략
     */
    public void registerFilter(String name, ImageFilterStrategy filter) {
        registeredFilters.put(name, filter);
    }
    
    /**
     * 등록된 필터 중 이름으로 선택하여 현재 필터로 설정하는 메소드
     * 
     * @param name 사용할 필터의 이름
     * @return 필터 설정 성공 여부
     */
    public boolean selectFilter(String name) {
        ImageFilterStrategy filter = registeredFilters.get(name);
        if (filter != null) {
            this.currentFilter = filter;
            return true;
        }
        return false;
    }
    
    /**
     * 현재 설정된 필터를 사용하여 이미지를 처리하는 메소드
     * 
     * @param image 처리할 이미지
     * @return 필터가 적용된 이미지
     * @throws IllegalStateException 필터가 설정되지 않은 경우
     */
    public Image applyFilter(Image image) {
        if (currentFilter == null) {
            throw new IllegalStateException("필터가 설정되지 않았습니다.");
        }
        
        Image filteredImage = currentFilter.applyFilter(image);
        
        // 필터 적용 기록 저장
        filterHistory.add(filteredImage);
        
        return filteredImage;
    }
    
    /**
     * 특정 이름의 필터를 사용하여 이미지를 처리하는 메소드
     * 
     * @param image 처리할 이미지
     * @param filterName 사용할 필터 이름
     * @return 필터가 적용된 이미지
     * @throws IllegalArgumentException 존재하지 않는 필터 이름인 경우
     */
    public Image applyFilter(Image image, String filterName) {
        ImageFilterStrategy filter = registeredFilters.get(filterName);
        if (filter == null) {
            throw new IllegalArgumentException("존재하지 않는 필터 이름입니다: " + filterName);
        }
        
        Image filteredImage = filter.applyFilter(image);
        
        // 필터 적용 기록 저장
        filterHistory.add(filteredImage);
        
        return filteredImage;
    }
    
    /**
     * 여러 필터를 순차적으로 적용하는 메소드
     * 
     * @param image 처리할 이미지
     * @param filterNames 적용할 필터 이름 목록
     * @return 모든 필터가 적용된 최종 이미지
     * @throws IllegalArgumentException 존재하지 않는 필터 이름이 있는 경우
     */
    public Image applyFilterChain(Image image, List<String> filterNames) {
        Image currentImage = image;
        
        for (String filterName : filterNames) {
            currentImage = applyFilter(currentImage, filterName);
        }
        
        return currentImage;
    }
    
    /**
     * 필터 적용 기록을 가져오는 메소드
     * 
     * @return 필터가 적용된 이미지 기록 목록
     */
    public List<Image> getFilterHistory() {
        return new ArrayList<>(filterHistory);
    }
    
    /**
     * 필터 적용 기록을 초기화하는 메소드
     */
    public void clearHistory() {
        filterHistory.clear();
    }
    
    /**
     * 현재 설정된 필터 이름을 반환하는 메소드
     * 
     * @return 필터 이름
     */
    public String getCurrentFilterName() {
        return currentFilter != null ? currentFilter.getFilterName() : "설정되지 않음";
    }
    
    /**
     * 등록된 모든 필터 이름을 반환하는 메소드
     * 
     * @return 등록된 필터 이름 목록
     */
    public List<String> getRegisteredFilterNames() {
        return new ArrayList<>(registeredFilters.keySet());
    }
}
