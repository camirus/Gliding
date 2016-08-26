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
@Table(name = "glider", catalog = "gliding", uniqueConstraints =
        @UniqueConstraint(columnNames = "registration_number"))
public class Glider implements java.io.Serializable {

    private Integer id;
    private String registrationNumber;
    private String alias;
    private String gpsGatewayName;
    private Date addDate;
    private String addedBy;
    private String model;

    public Glider() {
    }

    public Glider(String registrationNumber, String gpsGatewayName, Date addDate, String addedBy, String model) {
        this.registrationNumber = registrationNumber;
        this.gpsGatewayName = gpsGatewayName;
        this.addDate = addDate;
        this.addedBy = addedBy;
        this.model = model;
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

    @Column(name = "registration_number", unique = true, length = 50)
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Column(name = "gps_gateway_name", length = 50)
    public String getGpsGatewayName() {
        return this.gpsGatewayName;
    }

    public void setGpsGatewayName(String gpsGatewayName) {
        this.gpsGatewayName = gpsGatewayName;
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

    @Column(name = "model", length = 50)
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Glider) && obj.hashCode() == this.hashCode());
    }
}
