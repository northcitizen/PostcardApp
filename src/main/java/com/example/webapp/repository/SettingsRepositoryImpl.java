package com.example.webapp.repository;

import org.springframework.stereotype.Repository;

@Repository
public class SettingsRepositoryImpl {

    static private final int criticalPostcardNumber = -1;

    public int getCriticalPostcardNumber() {
        return criticalPostcardNumber;
    }

}
