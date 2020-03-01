package com.szymon.application.service;

import com.szymon.application.dao.MarketRepository;
import com.szymon.application.model.Market;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarketService {

    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public void save(Market market) {
        marketRepository.save(market);
    }

    public void check() {
        marketRepository.check();
    }
}
