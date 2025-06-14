package ru.duhov.tininvestrobot.domain;

public interface CandleObserver {
    void notifyCandle(CandleGroupId groupId, CachedCandle candle);
}
