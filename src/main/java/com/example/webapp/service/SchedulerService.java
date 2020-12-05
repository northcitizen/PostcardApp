package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SchedulerService {

    final PostcardRepository postcardRepository;
    private static int cardsNumber;
    private List<Postcard> list;

    @Autowired
    public SchedulerService(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }

    public SchedulerService(PostcardRepository postcardRepository, List<Postcard> list) {
        this.postcardRepository = postcardRepository;
        this.list = (List<Postcard>) postcardRepository.findAll();
    }

    public void changePostcardListNumber() {
        setList((List<Postcard>) postcardRepository.findAll());
        setCardsNumber(list.size());
    }

    @Scheduled(initialDelayString = "${scheduler.delay}", fixedDelayString = "${scheduler.delay}")
    public void postcardTask() throws InterruptedException {
        log.info("List of cards");
        List<Postcard> postcardList = (List<Postcard>) postcardRepository.findAll();
        int currentCardsNumber = postcardList.size();
        if (currentCardsNumber == getCardsNumber()) {
            log.error("CAUTION!");
        } else {
            log.info("POSTCARD LIST CHANGED");
            changePostcardListNumber();
        }
        log.info(String.valueOf(postcardList.size()) + " postcards in database");
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
