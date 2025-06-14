package ru.duhov.tininvestrobot.indicator.helper;

import ru.duhov.tininvestrobot.domain.CachedCandle;

import java.util.List;

/**
 * Индикатор минимального значения в свече
 */
public class LowPriceIndicator extends PriceIndicator {

    public LowPriceIndicator(List<CachedCandle> candles) {
        super(candles, CachedCandle::getLowPrice);
    }
}
