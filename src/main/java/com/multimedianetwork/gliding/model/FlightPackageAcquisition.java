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
@Table(name = "flight_package_acquisition", catalog = "gliding")
public class FlightPackageAcquisition implements java.io.Serializable {

    private Integer id;
    private Member member;
    private FlightPackage flightPackage;
    private Float amountPayed;
    private int usedTokens;
    private Date paymentDate;
    private String billNumber;
    private Date addDate;
    private String addedBy;

    public FlightPackageAcquisition() {
    }

    public FlightPackageAcquisition(Member member, FlightPackage flightPackage, int usedTokens) {
        this.member = member;
        this.flightPackage = flightPackage;
        this.usedTokens = usedTokens;
    }

    public FlightPackageAcquisition(Member member, FlightPackage flightPackage, Float amountPayed, int usedTokens, Date paymentDate, String billNumber, Date addDate, String addedBy) {
        this.member = member;
        this.flightPackage = flightPackage;
        this.amountPayed = amountPayed;
        this.usedTokens = usedTokens;
        this.paymentDate = paymentDate;
        this.billNumber = billNumber;
        this.addDate = addDate;
        this.addedBy = addedBy;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_package_id", nullable = false)
    public FlightPackage getFlightPackage() {
        return this.flightPackage;
    }

    public void setFlightPackage(FlightPackage flightPackage) {
        this.flightPackage = flightPackage;
    }

    @Column(name = "amount_payed", precision = 12, scale = 0)
    public Float getAmountPayed() {
        return this.amountPayed;
    }

    public void setAmountPayed(Float amountPayed) {
        this.amountPayed = amountPayed;
    }

    @Column(name = "used_tokens", nullable = false)
    public int getUsedTokens() {
        return this.usedTokens;
    }

    public void setUsedTokens(int usedTokens) {
        this.usedTokens = usedTokens;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "payment_date", length = 10)
    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "bill_number", length = 50)
    public String getBillNumber() {
        return this.billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date", length = 19)
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
}
