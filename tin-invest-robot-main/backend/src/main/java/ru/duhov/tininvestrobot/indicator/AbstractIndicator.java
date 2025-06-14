package ru.duhov.tininvestrobot.indicator;

import lombok.Getter;
import ru.duhov.tininvestrobot.domain.CachedCandle;

import java.util.List;

public abstract class AbstractIndicator<T> implements Indicator<T> {
    @Getter
    private final List<CachedCandle> candles;

    protected AbstractIndicator(List<CachedCandle> candles) {
        this.candles = candles;
    }
}
