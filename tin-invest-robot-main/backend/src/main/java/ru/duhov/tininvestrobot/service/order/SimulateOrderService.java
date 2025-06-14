package ru.duhov.tininvestrobot.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.duhov.tininvestrobot.domain.Order;
import ru.duhov.tininvestrobot.domain.OrderAction;
import ru.duhov.tininvestrobot.domain.OrderStatus;
import ru.duhov.tininvestrobot.domain.TradeType;
import ru.duhov.tininvestrobot.dto.CreateOrderInfo;
import ru.duhov.tininvestrobot.repository.InstrumentRepository;
import ru.duhov.tininvestrobot.repository.OrderHistoryRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulateOrderService implements OrderService {

    private final OrderObserversHolder observersHolder;
    private final InstrumentRepository instrumentRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public Optional<Order> openLong(CreateOrderInfo orderInfo) {
        int lots = orderInfo.getNumberOfLots();
        BigDecimal commission = getCommission(orderInfo.getPrice(), lots);

        var order = new Order(
                UUID.randomUUID().toString(),
                orderInfo.getBotUuid(),
                TradeType.LONG,
                orderInfo.getPrice(),
                lots,
                commission,
                orderInfo.getTime(),
                orderInfo.getReason(),
                OrderAction.OPEN,
                OrderStatus.SUCCESS,
                instrumentRepository.get(orderInfo.getFigi()).getName()
        );
        observersHolder.notifyNewOrderObservers(order);
        observersHolder.notifySuccessOrderObservers(order);
        return Optional.of(order);
    }


    @Override
    public Optional<Order> closeLong(CreateOrderInfo orderInfo) {
        int lots = orderInfo.getNumberOfLots();
        BigDecimal commission = getCommission(orderInfo.getPrice(), lots);

        var order = new Order(
                UUID.randomUUID().toString(),
                orderInfo.getBotUuid(),
                TradeType.LONG,
                orderInfo.getPrice(),
                orderInfo.getNumberOfLots(),
                commission,
                orderInfo.getTime(),
                orderInfo.getReason(),
                OrderAction.CLOSE,
                OrderStatus.SUCCESS,
                instrumentRepository.get(orderInfo.getFigi()).getName()
        );
        observersHolder.notifyNewOrderObservers(order);
        observersHolder.notifySuccessOrderObservers(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> openShort(CreateOrderInfo orderInfo) {
        int lots = orderInfo.getNumberOfLots();
        BigDecimal commission = getCommission(orderInfo.getPrice(), lots);

        var order = new Order(
                UUID.randomUUID().toString(),
                orderInfo.getBotUuid(),
                TradeType.SHORT,
                orderInfo.getPrice(),
                orderInfo.getNumberOfLots(),
                commission,
                orderInfo.getTime(),
                orderInfo.getReason(),
                OrderAction.OPEN,
                OrderStatus.SUCCESS,
                instrumentRepository.get(orderInfo.getFigi()).getName()
        );
        observersHolder.notifyNewOrderObservers(order);
        observersHolder.notifySuccessOrderObservers(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> closeShort(CreateOrderInfo orderInfo) {
        int lots = orderInfo.getNumberOfLots();
        BigDecimal commission = getCommission(orderInfo.getPrice(), lots);

        var order = new Order(
                UUID.randomUUID().toString(),
                orderInfo.getBotUuid(),
                TradeType.SHORT,
                orderInfo.getPrice(),
                orderInfo.getNumberOfLots(),
                commission,
                orderInfo.getTime(),
                orderInfo.getReason(),
                OrderAction.CLOSE,
                OrderStatus.SUCCESS,
                instrumentRepository.get(orderInfo.getFigi()).getName()
        );
        observersHolder.notifyNewOrderObservers(order);
        observersHolder.notifySuccessOrderObservers(order);
        return Optional.of(order);
    }

    @Override
    public boolean cancelOrder(String accountId, String orderId) {
        Optional<Order> order = orderHistoryRepository.get(orderId);
        order.ifPresent(observersHolder::notifyFailedOrderObservers);
        return order.isPresent();
    }

    private BigDecimal getCommission(BigDecimal price, int lots) {
        return price
                .multiply(BigDecimal.valueOf(lots))
                .multiply(BigDecimal.valueOf(0.003));
    }
}
