package ru.duhov.tininvestrobot.dto;

import lombok.Getter;
import ru.duhov.tininvestrobot.strategy.config.OneMinuteScalpingConfig;
import ru.duhov.tininvestrobot.strategy.config.ThreeLineStrikeConfig;

@Getter
public class StrategiesConfig {
    private OneMinuteScalpingConfig oneMinuteScalpingConfig;
    private ThreeLineStrikeConfig threeLineStrikeConfig;
}
