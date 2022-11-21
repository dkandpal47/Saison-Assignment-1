package com.example.assignment.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    Double latitude;
    Double longitude;

    @Override
    public String toString(){
        return "( "
                +latitude.toString()
                +","
                +longitude.toString()
                +" )";
    }
}
