package com.multimedianetwork.gliding.model;
// Generated Jul 15, 2015 7:27:31 PM by Hibernate Tools 3.2.1.GA

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
@Table(name = "earned_tokens", catalog = "gliding")
public class EarnedTokens implements java.io.Serializable {

    private Integer id;
    private Member member;
    private int numberOfTokens;
    private String reason;
    private Date addDate;
    private String addedBy;

    public EarnedTokens() {
    }

    public EarnedTokens(Member member, int numberOfTokens) {
        this.member = member;
        this.numberOfTokens = numberOfTokens;
    }

    public EarnedTokens(Member member, int numberOfTokens, String reason, Date addDate, String addedBy) {
        this.member = member;
        this.numberOfTokens = numberOfTokens;
        this.reason = reason;
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

    @Column(name = "number_of_tokens", nullable = false)
    public int getNumberOfTokens() {
        return this.numberOfTokens;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }

    @Column(name = "reason", length = 65535)
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
