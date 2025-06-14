package ru.duhov.tininvestrobot.indicator.helper;

import ru.duhov.tininvestrobot.domain.CachedCandle;

import java.util.List;

/**
 * Индикатор максимального значения в свече
 */
public class HighPriceIndicator extends PriceIndicator {

    public HighPriceIndicator(List<CachedCandle> candles) {
        super(candles, CachedCandle::getHighPrice);
    }
}
