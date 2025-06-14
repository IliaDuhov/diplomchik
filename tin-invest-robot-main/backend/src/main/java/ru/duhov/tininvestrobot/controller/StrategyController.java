package ru.duhov.tininvestrobot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.duhov.tininvestrobot.dto.StrategyInfo;
import ru.duhov.tininvestrobot.strategy.StrategyCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Стратегия")
@RestController
@RequestMapping("strategy")
public class StrategyController {

    @Operation(summary = "Все стратегии")
    @GetMapping
    public List<StrategyInfo> getAll() {
        return Arrays.stream(StrategyCode.values())
                .map(StrategyCode::toDto)
                .collect(Collectors.toList());
    }
}
