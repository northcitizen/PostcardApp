package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
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
        logger.info("List of cards");
        List<Postcard> postcardList = (List<Postcard>) postcardRepository.findAll();
        int currentCardsNumber = postcardList.size();
        if (currentCardsNumber == getCardsNumber()) {
            logger.error("CAUTION!");
        } else {
            logger.info("POSTCARD LIST CHANGED");
            changePostcardListNumber();
        }
        logger.info(String.valueOf(postcardList.size()));

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
