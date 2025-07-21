package com.task.tracking.service.impl;

import com.task.tracking.constant.ErrorCode;
import com.task.tracking.dto.TrackingRequest;
import com.task.tracking.dto.TrackingResponse;
import com.task.tracking.entity.TrackingNumber;
import com.task.tracking.exception.CustomException;
import com.task.tracking.helper.TrackingNumberHelper;
import com.task.tracking.repository.TrackingNumberRepository;
import com.task.tracking.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class TrackingServiceImpl implements TrackingService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    @Autowired
    private TrackingNumberHelper trackingNumberHelper;

    @Autowired
    private TrackingNumberRepository trackingNumberRepository;

    @Override
    public TrackingResponse generateNextTrackingNumber(TrackingRequest trackingRequest) {

        OffsetDateTime createdAt = null;
        try {
            createdAt = OffsetDateTime.parse(trackingRequest.getCreated_at());
        } catch (Exception e) {
            log.error("Invalid created_at format: {}", trackingRequest.getCreated_at());
            throw new CustomException(ErrorCode.BAD_REQUEST, "Invalid created_at format.");
        }

        String generatedNumber = trackingNumberHelper.generate();
        OffsetDateTime generatedTime = OffsetDateTime.now();
        String generatedTimeStr = generatedTime.format(formatter);

        TrackingNumber trackingNumber = new TrackingNumber();
        trackingNumber.setOriginCountryId(trackingRequest.getOrigin_country_id());
        trackingNumber.setDestinationCountryId(trackingRequest.getDestination_country_id());
        trackingNumber.setWeight(trackingRequest.getWeight());
        trackingNumber.setCreatedAt(createdAt);
        trackingNumber.setCustomerId(trackingRequest.getCustomer_id().toString());
        trackingNumber.setCustomerName(trackingRequest.getCustomer_name());
        trackingNumber.setCustomerSlug(trackingRequest.getCustomer_slug());
        trackingNumber.setTrackingNumber(generatedNumber);
        trackingNumber.setGeneratedAt(generatedTime);

        try {
            trackingNumberRepository.save(trackingNumber);
        } catch (DataIntegrityViolationException dive) {
            log.error("DataIntegrityViolationException : duplicate generated tracking number. Error: {}", dive.getMessage());
            throw new CustomException(ErrorCode.BAD_REQUEST, "Please try again.");
        } catch (Exception e) {
            log.error("Exception while saving tracking number. Error: {}", e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR, "Internal server error.");
        }
        return new TrackingResponse(generatedNumber, generatedTimeStr);
    }
}