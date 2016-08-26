package com.multimedianetwork.gliding.model;

import java.util.Date;
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

@Entity
@Table(name = "reservation", catalog = "gliding")
public class Reservation implements java.io.Serializable {

    private Integer id;
    private Member member;
    private Date forDate;
    private String message;
    private Date requestDate;
    private Date verificationDate;
    private String verifiedBy;
    private boolean confirmed;
    private boolean declined;
    private String declinationReason;
    private boolean canceled;
    private Date canceledAt;

    public Reservation() {
    }

    public Reservation(Member member, Date forDate, boolean confirmed, boolean declined, boolean canceled) {
        this.member = member;
        this.forDate = forDate;
        this.confirmed = confirmed;
        this.declined = declined;
        this.canceled = canceled;
    }

    public Reservation(Member member, Date forDate, String message, Date requestDate, Date verificationDate, String verifiedBy, boolean confirmed, boolean declined, String declinationReason, boolean canceled, Date canceledAt) {
        this.member = member;
        this.forDate = forDate;
        this.message = message;
        this.requestDate = requestDate;
        this.verificationDate = verificationDate;
        this.verifiedBy = verifiedBy;
        this.confirmed = confirmed;
        this.declined = declined;
        this.declinationReason = declinationReason;
        this.canceled = canceled;
        this.canceledAt = canceledAt;
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
    @JoinColumn(name = "member_id", nullable = false)
    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "for_date", nullable = false, length = 10)
    public Date getForDate() {
        return this.forDate;
    }

    public void setForDate(Date forDate) {
        this.forDate = forDate;
    }

    @Column(name = "message", length = 250)
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date", length = 19)
    public Date getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "verification_date", length = 19)
    public Date getVerificationDate() {
        return this.verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    @Column(name = "verified_by", length = 100)
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    @Column(name = "confirmed", nullable = false)
    public boolean isConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Column(name = "declined", nullable = false)
    public boolean isDeclined() {
        return this.declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    @Column(name = "declination_reason", length = 250)
    public String getDeclinationReason() {
        return this.declinationReason;
    }

    public void setDeclinationReason(String declinationReason) {
        this.declinationReason = declinationReason;
    }

    @Column(name = "canceled", nullable = false)
    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "canceled_at", length = 19)
    public Date getCanceledAt() {
        return this.canceledAt;
    }

    public void setCanceledAt(Date canceledAt) {
        this.canceledAt = canceledAt;
    }
}
