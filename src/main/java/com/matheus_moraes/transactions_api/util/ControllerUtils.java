package com.matheus_moraes.transactions_api.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ControllerUtils {
    public String generateProcessId(){
        return UUID.randomUUID().toString();
    }
}
