package com.example.assignment.api.response;

import com.example.assignment.api.model.Location;
import com.example.assignment.util.TruckUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodTruck implements Comparable<FoodTruck>{

    private Double locationId;
    private String Applicant;
    private String FacilityType;
    private Double cnn;

    private String LocationDescription;
    private String Address;
    private String blocklot;
    private String block;
    private String lot;
    private String permit;
    private String status;
    private String FoodItems;

    private Double X;
    private Double Y;
    private Double Latitude;
    private Double Longitude;

    private String Schedule;
    private Date NOISent;
    private Date Approved;
    private Date Received;

    private Date ExpirationDate;
    private Location Location;

    @Override
    public int compareTo(FoodTruck o) {
        Double dis = TruckUtil.distance(this.getLatitude(), this.getLongitude(), o.getLatitude(), o.getLongitude(),"k");
        return dis.intValue();
    }
}
