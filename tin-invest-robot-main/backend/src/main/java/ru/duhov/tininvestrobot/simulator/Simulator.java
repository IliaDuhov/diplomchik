package ru.duhov.tininvestrobot.simulator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.duhov.tininvestrobot.domain.CachedCandle;
import ru.duhov.tininvestrobot.domain.CandleGroupId;
import ru.duhov.tininvestrobot.helper.HelperUtils;
import ru.duhov.tininvestrobot.repository.InstrumentRepository;
import ru.duhov.tininvestrobot.service.SdkService;
import ru.duhov.tininvestrobot.service.candle.CandleService;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;

import java.time.Instant;
import java.util.List;

/**
 * Симуляция получения данных на исторических значениях
 */
@Service
@RequiredArgsConstructor
public class Simulator {

    private final SdkService sdkService;
    private final CandleService candleService;
    private final InstrumentRepository instrumentRepository;

    public void simulate(CandleGroupId groupId, Instant start) {
        var requestPeriod = HelperUtils.getMaxRequestPeriod(groupId.getCandleInterval());
        var from = start;
        var to = from.plus(requestPeriod);
        while (from.isBefore(Instant.now())) {
            List<HistoricCandle> candles = sdkService.getInvestApi().getMarketDataService()
                    .getCandlesSync(groupId.getFigi(), from, to, groupId.getCandleInterval());
            candles.forEach(candle ->
                    candleService.addCandleIfAbsent(groupId, CachedCandle
                            .ofHistoricCandle(candle, instrumentRepository.get(groupId.getFigi()).getLot())));
            from = to;
            to = from.plus(requestPeriod);
        }
    }
}