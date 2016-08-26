/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multimedianetwork.gliding.model.util;

import com.multimedianetwork.gliding.model.Member;
import java.io.Serializable;
import javax.persistence.Transient;

/**
 *
 * @author Cami
 */
public class PilotDetails implements Serializable{

    Member member;
    int year;
    int numberOfFlights;
    int totalDistance;
    int totalTime;

    public PilotDetails() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(int numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getDurationInHours() {
        return (int) (totalTime / 60);
    }

    public int getExtraDurationInMinutes() {
        return (int) (totalTime % 60);
    }

    public String getDurationInHoursAndMinutes() {

        int hour = getDurationInHours();
        int minute = getExtraDurationInMinutes();
        return ((hour < 10) ? ("0" + hour) : hour) + ":" + ((minute < 10) ? ("0" + minute) : minute);
    }

}
