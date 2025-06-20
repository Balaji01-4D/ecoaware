package com.ecoaware.tracker.DTO;

import com.ecoaware.tracker.enums.Status;
import lombok.Data;

@Data
public class ComplaintStatusUpdate {
    private Status status;
}
