package com.task.tracking.controller;

import com.task.tracking.common.GenericResponseEntity;
import com.task.tracking.dto.TrackingRequest;
import com.task.tracking.dto.TrackingResponse;
import com.task.tracking.service.TrackingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/next-tracking-number")
    public GenericResponseEntity generateNextTrackingNumber(@Valid @ModelAttribute TrackingRequest request) {

        log.info("Next-tracking-number Request: " + request.toString());
        TrackingResponse trackingResponse = trackingService.generateNextTrackingNumber(request);
        GenericResponseEntity response = GenericResponseEntity.response("Tracking number generated.", trackingResponse);
        log.info("Next-tracking-number Response: " + response.toString());
        return response;
    }
}
