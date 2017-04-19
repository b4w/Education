package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Account {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
}
