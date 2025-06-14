package ru.duhov.tininvestrobot.repository;

import ru.duhov.tininvestrobot.domain.CachedCandle;
import ru.duhov.tininvestrobot.domain.CandleGroupId;

import java.util.Collection;
import java.util.SortedSet;

public interface CandleRepository {
    boolean add(CandleGroupId groupId, CachedCandle candle);

    void addAll(CandleGroupId groupId, Collection<CachedCandle> candle);

    SortedSet<CachedCandle> getAll(CandleGroupId groupId);

    void remove(CandleGroupId groupId);
}
