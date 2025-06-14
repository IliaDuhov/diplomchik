package ru.duhov.tininvestrobot.repository;

import ru.duhov.tininvestrobot.domain.Order;

import java.util.Optional;
import java.util.SortedSet;
import java.util.UUID;

public interface OrderHistoryRepository {
    Optional<Order> get(String orderId);

    void add(UUID botUuid, Order order);

    void update(Order order);

    SortedSet<Order> getAll(UUID botUuid);

    void remove(UUID botUuid, String orderId);
}
