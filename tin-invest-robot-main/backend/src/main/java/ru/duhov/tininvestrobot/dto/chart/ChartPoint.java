package ru.duhov.tininvestrobot.dto.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.duhov.tininvestrobot.domain.CachedCandle;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@AllArgsConstructor
public class ChartPoint {
    public BigDecimal value;
    public Instant time;

    public static ChartPoint of(CachedCandle candle) {
        return new ChartPoint(candle.getClosePrice(), candle.getTime());
    }
}
