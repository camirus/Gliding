package com.multimedianetwork.gliding.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "flight_track")
public class FlightTrack implements java.io.Serializable {

    private Long id;
    private String planeName;
    private Date timeStamp;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Double groundSpeed;

    public FlightTrack() {
    }

    public FlightTrack(String planeName, Date timeStamp, Double longitude, Double latitude, Double altitude, Double ground_speed) {
        this.planeName = planeName;
        this.timeStamp = timeStamp;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.groundSpeed = ground_speed;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "plane_name", unique = true, length = 100)
    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_stamp")
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "altitude")
    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    @Column(name = "ground_speed")
    public Double getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundSpeed(Double ground_speed) {
        this.groundSpeed = ground_speed;
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof FlightTrack) && obj.hashCode() == this.hashCode());
    }
}
