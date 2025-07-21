package com.task.tracking.service;

import com.task.tracking.dto.TrackingRequest;
import com.task.tracking.dto.TrackingResponse;

public interface TrackingService {

    TrackingResponse generateNextTrackingNumber(TrackingRequest trackingRequest);
}
