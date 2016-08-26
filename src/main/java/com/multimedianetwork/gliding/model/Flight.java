package com.multimedianetwork.gliding.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "flight", catalog = "gliding")
public class Flight implements java.io.Serializable {

    private Integer id;
    private Member memberBySecondSeatPayedByMember;
    private Member memberByCopilotId;
    private Member memberByPilotId;
    private Member memberByFirstSeatPayedByMember;
    private Glider glider;
    private Date flightDate = new Date();
    private Date startTime;
    private Date endTime;
    private Integer durationInMinutes;
    private String observation;
    private Date addDate;
    private String addedBy;
    private boolean synced;
    private boolean protocolFlight;
    private String olcLink;
    private String takeOffMethod;
    private boolean inBooklet;
    private Integer distance;
    /**
     * Transient fields
     */
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private boolean pilotNew;
    private boolean pilotHasLicense;
    private String pilotName;
    private String pilotCNP;
    private String pilotTelephone;
    private String pilotEmail;
    private MemberType pilotMemberType;
    private boolean copilotNew;
    private String copilotName;
    private String copilotCNP;
    private String copilotTelephone;
    private String copilotEmail;
    private MemberType copilotMemberType;
    private boolean copilotHasLicense;
    private String startHourAndMinute;
    private String endHourAndMinute;
    private String startHourAndMinuteInput;
    private String endHourAndMinuteInput;

    public Flight() {
    }

    public Flight(Member memberBySecondSeatPayedByMember, Member memberByFirstSeatPayedByMember, Glider glider, Date startTime, Date endTime, boolean synced, boolean protocolFlight) {
        this.memberBySecondSeatPayedByMember = memberBySecondSeatPayedByMember;
        this.memberByFirstSeatPayedByMember = memberByFirstSeatPayedByMember;
        this.glider = glider;
        this.startTime = startTime;
        this.endTime = endTime;
        this.synced = synced;
        this.protocolFlight = protocolFlight;
    }

    public Flight(Member memberBySecondSeatPayedByMember, Member memberByCopilotId, Member memberByPilotId, Member memberByFirstSeatPayedByMember, Glider glider, String pilotName, String copilotName, Date flightDate, Date startTime, Date endTime, Integer durationInMinutes, String observation, Date addDate, String addedBy, boolean synced, boolean protocolFlight, String olcLink) {
        this.memberBySecondSeatPayedByMember = memberBySecondSeatPayedByMember;
        this.memberByCopilotId = memberByCopilotId;
        this.memberByPilotId = memberByPilotId;
        this.memberByFirstSeatPayedByMember = memberByFirstSeatPayedByMember;
        this.glider = glider;
        this.pilotName = pilotName;
        this.copilotName = copilotName;
        this.flightDate = flightDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInMinutes = durationInMinutes;
        this.observation = observation;
        this.addDate = addDate;
        this.addedBy = addedBy;
        this.synced = synced;
        this.protocolFlight = protocolFlight;
        this.olcLink = olcLink;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_seat_payed_by_member", nullable = false)
    public Member getMemberBySecondSeatPayedByMember() {
        return this.memberBySecondSeatPayedByMember;
    }

    public void setMemberBySecondSeatPayedByMember(Member memberBySecondSeatPayedByMember) {
        this.memberBySecondSeatPayedByMember = memberBySecondSeatPayedByMember;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copilot_id")
    public Member getMemberByCopilotId() {
        return this.memberByCopilotId;
    }

    public void setMemberByCopilotId(Member memberByCopilotId) {
        this.memberByCopilotId = memberByCopilotId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_id")
    public Member getMemberByPilotId() {
        return this.memberByPilotId;
    }

    public void setMemberByPilotId(Member memberByPilotId) {
        this.memberByPilotId = memberByPilotId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_seat_payed_by_member", nullable = false)
    public Member getMemberByFirstSeatPayedByMember() {
        return this.memberByFirstSeatPayedByMember;
    }

    public void setMemberByFirstSeatPayedByMember(Member memberByFirstSeatPayedByMember) {
        this.memberByFirstSeatPayedByMember = memberByFirstSeatPayedByMember;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "glider_id", nullable = false)
    public Glider getGlider() {
        return this.glider;
    }

    public void setGlider(Glider glider) {
        this.glider = glider;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "flight_date", length = 10)
    public Date getFlightDate() {
        return this.flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false, length = 19)
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false, length = 19)
    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "duration_in_minutes")
    public Integer getDurationInMinutes() {
        return this.durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Column(name = "observation", length = 65535)
    public String getObservation() {
        return this.observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Column(name = "take_off_method")
    public String getTakeOffMethod() {
        return takeOffMethod;
    }

    public void setTakeOffMethod(String takeOffMethod) {
        this.takeOffMethod = takeOffMethod;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "add_date", length = 10)
    public Date getAddDate() {
        return this.addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Column(name = "added_by", length = 50)
    public String getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name = "synced", nullable = false)
    public boolean isSynced() {
        return this.synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    @Column(name = "in_booklet", nullable = false)
    public boolean isInBooklet() {
        return inBooklet;
    }

    public void setInBooklet(boolean inBooklet) {
        this.inBooklet = inBooklet;
    }

    @Column(name = "protocol_flight", nullable = false)
    public boolean isProtocolFlight() {
        return this.protocolFlight;
    }

    public void setProtocolFlight(boolean protocolFlight) {
        this.protocolFlight = protocolFlight;
    }

    @Column(name = "olc_link", length = 500)
    public String getOlcLink() {
        return this.olcLink;
    }

    public void setOlcLink(String olcLink) {
        this.olcLink = olcLink;
    }

    @Column(name = "distance")
    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Transient
    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @Transient
    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    @Transient
    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    @Transient
    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    @Transient
    public boolean isPilotNew() {
        return pilotNew;
    }

    public void setPilotNew(boolean pilotNew) {
        this.pilotNew = pilotNew;
    }

    @Transient
    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    @Transient
    public String getPilotCNP() {
        return pilotCNP;
    }

    public void setPilotCNP(String pilotCNP) {
        this.pilotCNP = pilotCNP;
    }

    @Transient
    public String getPilotTelephone() {
        return pilotTelephone;
    }

    public void setPilotTelephone(String pilotTelephone) {
        this.pilotTelephone = pilotTelephone;
    }

    @Transient
    public String getPilotEmail() {
        return pilotEmail;
    }

    public void setPilotEmail(String pilotEmail) {
        this.pilotEmail = pilotEmail;
    }

    @Transient
    public MemberType getPilotMemberType() {
        return pilotMemberType;
    }

    public void setPilotMemberType(MemberType pilotMemberType) {
        this.pilotMemberType = pilotMemberType;
    }

    @Transient
    public boolean isCopilotNew() {
        return copilotNew;
    }

    public void setCopilotNew(boolean copilotNew) {
        this.copilotNew = copilotNew;
    }

    @Transient
    public String getCopilotName() {
        return copilotName;
    }

    public void setCopilotName(String copilotName) {
        this.copilotName = copilotName;
    }

    @Transient
    public String getCopilotCNP() {
        return copilotCNP;
    }

    public void setCopilotCNP(String copilotCNP) {
        this.copilotCNP = copilotCNP;
    }

    @Transient
    public String getCopilotTelephone() {
        return copilotTelephone;
    }

    public void setCopilotTelephone(String copilotTelephone) {
        this.copilotTelephone = copilotTelephone;
    }

    @Transient
    public String getCopilotEmail() {
        return copilotEmail;
    }

    public void setCopilotEmail(String copilotEmail) {
        this.copilotEmail = copilotEmail;
    }

    @Transient
    public MemberType getCopilotMemberType() {
        return copilotMemberType;
    }

    public void setCopilotMemberType(MemberType copilotMemberType) {
        this.copilotMemberType = copilotMemberType;
    }

    @Transient
    public boolean isPilotHasLicense() {
        return pilotHasLicense;
    }

    public void setPilotHasLicense(boolean pilotHasLicense) {
        this.pilotHasLicense = pilotHasLicense;
    }

    @Transient
    public boolean isCopilotHasLicense() {
        return copilotHasLicense;
    }

    public void setCopilotHasLicense(boolean copilotHasLicense) {
        this.copilotHasLicense = copilotHasLicense;
    }

    @Transient
    public String getFlightDateStr() {
        return new SimpleDateFormat("dd/MM/yyyy").format(flightDate);
    }

    @Transient
    public String getStartTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
    }

    @Transient
    public String getEndTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
    }

    @Transient
    public String getStartHourAndMinute() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(startTime);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return ((hour < 10) ? ("0" + hour) : hour) + ":" + ((minute < 10) ? ("0" + minute) : minute);
    }

    @Transient
    public String getEndHourAndMinute() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(endTime);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return ((hour < 10) ? ("0" + hour) : hour) + ":" + ((minute < 10) ? ("0" + minute) : minute);
    }

    @Transient
    public String getStartHourAndMinuteInput() {
        return startHourAndMinuteInput;
    }

    public void setStartHourAndMinuteInput(String startHourAndMinuteInput) {
        this.startHourAndMinuteInput = startHourAndMinuteInput;
    }

    @Transient
    public String getEndHourAndMinuteInput() {
        return endHourAndMinuteInput;
    }

    public void setEndHourAndMinuteInput(String endHourAndMinuteInput) {
        this.endHourAndMinuteInput = endHourAndMinuteInput;
    }

    @Transient
    public int getDurationInHours() {
        return (int) (durationInMinutes / 60);
    }

    @Transient
    public int getExtraDurationInMinutes() {
        return (int) (durationInMinutes % 60);
    }

    @Transient
    public String getInBookletStr() {
        return inBooklet ? "da" : "nu";
    }

    @Transient
    public String getDurationInHoursAndMinutes() {
        int hour = getDurationInHours();
        int minute = getExtraDurationInMinutes();
        return ((hour < 10) ? ("0" + hour) : hour) + ":" + ((minute < 10) ? ("0" + minute) : minute);
    }

    @Transient
    public int getLevel() {
        int level = 0;
        if (durationInMinutes > 60 && durationInMinutes < 140) {
            level = 1;
        } else if (durationInMinutes >= 140 && durationInMinutes < 240) {
            level = 2;
        } else if (durationInMinutes >= 240) {
            level = 3;
        }

        if (distance != null && (distance > 300)) {
            level = 3;
            return level;
        }
        if (distance != null && (distance > 120)) {
            if (level < 2) {
                level = 2;
            }
        }
        return level;
    }
}
