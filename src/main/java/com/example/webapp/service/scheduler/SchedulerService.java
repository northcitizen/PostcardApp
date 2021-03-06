package com.example.webapp.service.scheduler;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.repository.SettingsRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SchedulerService {

    private final SettingsRepositoryImpl settingsRepository;
    private final PostcardRepository postcardRepository;
    private final CacheManager cacheManager;
    private static int cardsNumber;
    private List<Postcard> list;

    @Autowired
    public SchedulerService(SettingsRepositoryImpl settingsRepository, PostcardRepository postcardRepository, CacheManager cacheManager) {
        this.settingsRepository = settingsRepository;
        this.postcardRepository = postcardRepository;
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    private void init() {
        this.list = (List<Postcard>) postcardRepository.findAll();
        if (list.size() > settingsRepository.getCriticalPostcardNumber()) {
            log.warn("application started with critical number of postcards");
        }
    }

    @PreDestroy
    private void destroy() {
        try {
            this.list = (List<Postcard>) postcardRepository.findAll();
            if (list.size() > settingsRepository.getCriticalPostcardNumber()) {
                log.warn("application stopped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePostcardListNumber() {
        setList((List<Postcard>) postcardRepository.findAll());
        setCardsNumber(list.size());
    }

    @Scheduled(initialDelayString = "${scheduler.delay}", fixedDelayString = "${scheduler.delay}")
    public void postcardTask() {
        log.info("List of cards");
        List<Postcard> postcardList = (List<Postcard>) postcardRepository.findAll();
        int currentCardsNumber = postcardList.size();
        if (currentCardsNumber == getCardsNumber()) {
            log.error("CAUTION!");
        } else {
            log.info("POSTCARD LIST CHANGED");
            changePostcardListNumber();
        }
        log.info(postcardList.size() + " postcards in database");
    }

    @Scheduled(fixedRate = 1800000)
    public void evictAllCaches() {
        log.info("cache eviction");
        Objects.requireNonNull(cacheManager.getCache("postcardCache")).clear();
    }

    public static void setCardsNumber(int cardsNumber) {
        SchedulerService.cardsNumber = cardsNumber;
    }

    public void setList(List<Postcard> list) {
        this.list = list;
    }

    public static int getCardsNumber() {
        return cardsNumber;
    }

    public List<Postcard> getList() {
        return list;
    }
}
