package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.HandMadePostcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class HandMadePostcardService {

    final
    HandMadePostcardRepository handMadePostcardRepository;
    @Autowired
    public HandMadePostcardService(HandMadePostcardRepository handMadePostcardRepository) {
        this.handMadePostcardRepository = handMadePostcardRepository;
    }

    public List<Postcard> findAllPostcards(){
        return handMadePostcardRepository.findAllPostcards();
    }

    public List<Postcard> getPostcardByYear(int year){
        return handMadePostcardRepository.getPostcardByYear(year);
    }

    public Long getDistance(String year) {
        Long distance = 0L;

        List<Postcard> postcards = getPostcardByYear(Integer.parseInt(year));

        if (!CollectionUtils.isEmpty(postcards)) {
            for (Postcard card : postcards)
                distance += card.getDistance();
        }
        return distance;
    }
}
