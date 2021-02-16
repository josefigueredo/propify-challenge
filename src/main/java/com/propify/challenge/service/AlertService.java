package com.propify.challenge.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AlertService {

    public void sendPropertyDeletedAlert(Integer id) {
        // What this method actually does is not important
        log.info(">> Alert: Property deleted with id: " + id);
    }
}
