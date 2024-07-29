package com.miniproject.Quizziz.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Data
@AllArgsConstructor
@Builder
public class UserKey implements Serializable {
    private String customerId;
    private String employeeId;
    private String organizationName;
}

