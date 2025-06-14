package ru.duhov.tininvestrobot.service.order;

import ru.duhov.tininvestrobot.domain.Order;
import ru.duhov.tininvestrobot.dto.CreateOrderInfo;
import ru.tinkoff.piapi.core.exception.ApiRuntimeException;

/**
 * Уведомления о состоянии поручения
 */
public interface OrderObserver {
    default void notifyNewOrder(Order order) {}

    default void notifySuccessfulOrder(Order order) {}

    default void notifyFailedOrder(Order order) {}

    default void notifyError(CreateOrderInfo info, ApiRuntimeException e) {}
}
