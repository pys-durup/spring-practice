package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST: 빠른배송
 * NORMAL: 일반배송
 * SLOW: 느린 배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName; // 고객에게 보여주는 값

}
