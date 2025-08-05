package com.matheus_moraes.transactions_api.controller;

import com.matheus_moraes.transactions_api.dto.res.StatisticsResDto;
import com.matheus_moraes.transactions_api.service.StatisticsService;
import com.matheus_moraes.transactions_api.util.ControllerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final ControllerUtils controllerUtils;

    @GetMapping
    public StatisticsResDto getStatistics(@RequestParam(value = "time", defaultValue = "60") Long timeInterval) {
        String processId = controllerUtils.generateProcessId();
        log.info("[ID: {}] Controller: Received request to get statistics for time interval: {} seconds.", processId, timeInterval);
        StatisticsResDto statistics = statisticsService.generateStatisticsPerTimeInterval(timeInterval, processId);
        log.info("[ID: {}] Controller: Statistics generated successfully. Returning statistics.", processId);
        return statistics;
    }
}