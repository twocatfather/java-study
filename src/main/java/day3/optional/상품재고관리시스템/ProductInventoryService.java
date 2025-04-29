package day3.optional.상품재고관리시스템;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProductInventoryService {
    private Map<String, Product> inventory = new ConcurrentHashMap<>();

    public Optional<Product> findProduct(String id) {
        return Optional.of(inventory.get(id));
    }

    /**
     * 주문을 처리하는 메서드
     */
    public void processOrder(String productId, int quantity) {
        findProduct(productId)
                .filter(product -> product.getStock() >= quantity) // 충분한 재고가 확보 되어야 구매가 가능하다.
                .ifPresentOrElse(
                        product -> {
                            product.setStock(product.getStock() - quantity);
                            System.out.println("주문 처리 완료");
                        },
                        () -> {
                            throw new IllegalStateException("재고 부족 또는 상품이 존재하지 않습니다.");
                        }
                );
    }
}
