package ru.duhov.tininvestrobot.indicator.helper;

import ru.duhov.tininvestrobot.domain.CachedCandle;

import java.util.List;

/**
 * Индикатор значения открытия свечи
 */
public class OpenPriceIndicator extends PriceIndicator {

    public OpenPriceIndicator(List<CachedCandle> candles) {
        super(candles, CachedCandle::getOpenPrice);
    }
}
