package com.headhunter.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ValidatorResponse {
    private String code;
    private String description;
}
