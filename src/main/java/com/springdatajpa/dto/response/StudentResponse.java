package com.springdatajpa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Builder
public record StudentResponse( Long studentId,  String emailId, String firstName,
                               String lastName
) {
}
