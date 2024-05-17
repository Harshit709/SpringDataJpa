package com.springdatajpa.dto.request;

import lombok.Builder;

@Builder
public record StudentRequest(String firstName,
    String lastName,
    String emailId) {
}
