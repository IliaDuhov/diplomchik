package ru.duhov.tininvestrobot.repository;

import ru.duhov.tininvestrobot.domain.CachedInstrument;

import java.util.List;

public interface InstrumentRepository {
    CachedInstrument get(String figi);

    List<CachedInstrument> getAllShare();

    void add(String figi);
}
