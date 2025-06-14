package ru.duhov.tininvestrobot.service.notification.socket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.duhov.tininvestrobot.domain.Order;
import ru.duhov.tininvestrobot.domain.OrderAction;
import ru.duhov.tininvestrobot.domain.OrderReason;
import ru.duhov.tininvestrobot.domain.TradeType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class OrderEvent {
    private UUID botUuid;
    private TradeType type;
    private BigDecimal price;
    private int numberOfLots;
    private OrderReason reason;
    private OrderAction action;
    private String instrumentName;

    public static OrderEvent of(Order order) {
        return new OrderEvent()
                .setBotUuid(order.getBotUuid())
                .setType(order.getType())
                .setPrice(order.getPrice())
                .setNumberOfLots(order.getNumberOfLots())
                .setReason(order.getReason())
                .setAction(order.getAction())
                .setInstrumentName(order.getInstrumentName());
    }
}
