package ru.duhov.tininvestrobot.indicator.helper;

import ru.duhov.tininvestrobot.domain.CachedCandle;

import java.util.List;

/**
 * Индикатор значения закрытия свечи
 */
public class ClosePriceIndicator extends PriceIndicator {

    public ClosePriceIndicator(List<CachedCandle> candles) {
        super(candles, CachedCandle::getClosePrice);
    }
}
