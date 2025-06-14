package ru.duhov.tininvestrobot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.duhov.tininvestrobot.domain.Order;
import ru.duhov.tininvestrobot.domain.OrderAction;
import ru.duhov.tininvestrobot.domain.OrderReason;
import ru.duhov.tininvestrobot.domain.TradeType;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDto {
    private BigDecimal price;
    private BigDecimal commission;
    private TradeType type;
    private OrderAction action;
    private Instant time;
    private OrderReason reason;

    public static OrderDto map(Order order) {
        return new OrderDto()
                .setPrice(order.getPrice())
                .setCommission(order.getCommission())
                .setType(order.getType())
                .setAction(order.getAction())
                .setTime(order.getTime())
                .setReason(order.getReason());
    }
}
