package com.amica.example;

import lombok.*;
import lombok.extern.java.Log;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log
@ToString
@EqualsAndHashCode(of="ID")
//@Data //includes toString, Getter, Setter, EqualsAndHashCode, and RequiredArgsConstructor (final or @NonNull fields)
public class Employee {
    String firstName;
    String lastName;
    @NonNull
    int ID;
    String Department;

    //@SneakyThrows
    public String getFullName() throws Exception {
        log.info("Getting full name for employee ID " + this.ID);
        if( firstName == null || lastName == null ){
            throw new Exception("Null value for first or last name");
        }
        return firstName + " " + lastName;
    }
}
