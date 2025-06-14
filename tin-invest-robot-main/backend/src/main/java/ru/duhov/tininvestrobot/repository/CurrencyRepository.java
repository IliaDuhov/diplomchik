package ru.duhov.tininvestrobot.repository;

import java.math.BigDecimal;

public interface CurrencyRepository {
    BigDecimal getCurrentPrice(String isoCode);
}
