/*
 * 
 * 
 */
package com.multimedianetwork.gliding.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Camelia Rus
 */
@Entity
@Table(name = "flight_journal")
public class FlightJournal implements java.io.Serializable {

    private Long id;
    private String planeName;
    private Date samplingStart;
    private Date samplingEnd;
    private String startAddress;
    private String stopAddress;
    private Float distance;
    private String type;
    private Long duration;
    private Float avgSpeed;
    private Float maxSpeed;
    private Date addDate;

    public FlightJournal() {
    }

    public FlightJournal(String carName) {
        this.planeName = carName;
    }

    public FlightJournal(String planeName, Date samplingStart, Date samplingEnd, String startAddress, String stopAddress, Float distance, String type, Long duration, Float avgSpeed, Float maxSpeed, Date addDate) {
        this.planeName = planeName;
        this.samplingStart = samplingStart;
        this.samplingEnd = samplingEnd;
        this.startAddress = startAddress;
        this.stopAddress = stopAddress;
        this.distance = distance;
        this.type = type;
        this.duration = duration;
        this.avgSpeed = avgSpeed;
        this.maxSpeed = maxSpeed;
        this.addDate = addDate;
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

    @Column(name = "plane_name", nullable = false, length = 100)
    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sampling_start", length = 19)
    public Date getSamplingStart() {
        return this.samplingStart;
    }

    public void setSamplingStart(Date samplingStart) {
        this.samplingStart = samplingStart;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sampling_end", length = 19)
    public Date getSamplingEnd() {
        return this.samplingEnd;
    }

    public void setSamplingEnd(Date samplingEnd) {
        this.samplingEnd = samplingEnd;
    }

    @Column(name = "start_address", length = 600)
    public String getStartAddress() {
        return this.startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    @Column(name = "stop_address", length = 600)
    public String getStopAddress() {
        return this.stopAddress;
    }

    public void setStopAddress(String stopAddress) {
        this.stopAddress = stopAddress;
    }

    @Column(name = "distance", precision = 12, scale = 0)
    public Float getDistance() {
        return this.distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    @Column(name = "type", length = 30)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "duration")
    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Column(name = "avg_speed", precision = 12, scale = 0)
    public Float getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(Float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    @Column(name = "max_speed", precision = 12, scale = 0)
    public Float getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date", length = 19)
    public Date getAddDate() {
        return this.addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Transient
    public String getSamplingStartHoursStr() {
        return new SimpleDateFormat("HH:mm").format(samplingStart);
    }

    @Transient
    public String getSamplingEndHoursStr() {
        return new SimpleDateFormat("HH:mm").format(samplingEnd);
    }

    @Transient
    public String getDurationStr() {
        /*
         * Duration is expressed in seconds
         */
        long value = duration;
        long seconds = value % 60;
        value = value / 60;
        long minutes = value % 60;
        value = value / 60;
        long hours = value % 24;
        long days = value / 24;

        return days + ":" + (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes)
                + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }

    @Transient
    public String getDistanceStr() {
        return (distance / 1000) + " km";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FlightJournal) && (this.hashCode() == obj.hashCode());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Transient
    public String getStartTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(samplingStart);
    }

    @Transient
    public String getEndTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(samplingEnd);
    }
}
