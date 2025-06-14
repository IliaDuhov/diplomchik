package ru.duhov.tininvestrobot.strategy;

import ru.duhov.tininvestrobot.domain.CachedCandle;
import ru.duhov.tininvestrobot.dto.StrategiesConfig;

import java.util.List;

@FunctionalInterface
public interface StrategyCreator {
    InvestStrategy create(List<CachedCandle> candles, StrategiesConfig config);
}
