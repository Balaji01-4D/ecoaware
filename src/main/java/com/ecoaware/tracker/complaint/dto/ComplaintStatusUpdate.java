package com.ecoaware.tracker.complaint.dto;

import com.ecoaware.tracker.enums.Status;
import lombok.Data;

@Data
public class ComplaintStatusUpdate {
    private Status status;
}
