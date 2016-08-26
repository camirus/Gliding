package com.multimedianetwork.gliding.model;

import java.util.Date;
import javax.persistence.CascadeType;
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
@Table(name = "member", catalog = "gliding")
public class Member implements java.io.Serializable {

    private Integer id;
    private User user;
    private String name;
    private Date birthDate;
    private String cnp;
    private String telephone;
    private String email;
    private String address;
    private boolean hasLicense;
    private String licenseNumber;
    private Date licenseFirstReleaseDate;
    private Date licenseExpiryDate;
    private boolean hasMedicalLicense;
    private Date medicalLicenseExpiryDate;
    private Integer initialNumberOfHoursOfExperience;
    private Integer initialNumberOfTakeoffs;
    private boolean hasSilverBadge;
    private boolean hasGoldBadge;
    private boolean hasAcrobaticsBadge;
    private boolean hasUlmLicense;
    private boolean hasTowingLicense;
    private boolean instructor;
    private String status;
    private String addedBy;
    private Date addDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private MemberType memberType;

    public Member() {
    }

    public Member(User user, boolean hasLicense, boolean hasMedicalLicense, boolean hasSilverBadge, boolean hasGoldBadge, boolean hasAcrobaticsBadge, boolean hasUlmLicense, boolean hasTowingLicense, String addedBy, Date addDate) {
        this.user = user;
        this.hasLicense = hasLicense;
        this.hasMedicalLicense = hasMedicalLicense;
        this.hasSilverBadge = hasSilverBadge;
        this.hasGoldBadge = hasGoldBadge;
        this.hasAcrobaticsBadge = hasAcrobaticsBadge;
        this.hasUlmLicense = hasUlmLicense;
        this.hasTowingLicense = hasTowingLicense;
        this.addedBy = addedBy;
        this.addDate = addDate;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_type_id")
    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", length = 10)
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "cnp", length = 30)
    public String getCnp() {
        return this.cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Column(name = "has_license", nullable = false)
    public boolean isHasLicense() {
        return this.hasLicense;
    }

    public void setHasLicense(boolean hasLicense) {
        this.hasLicense = hasLicense;
    }

    @Column(name = "license_number", length = 50)
    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "license_first_release_date", length = 10)
    public Date getLicenseFirstReleaseDate() {
        return this.licenseFirstReleaseDate;
    }

    public void setLicenseFirstReleaseDate(Date licenseFirstReleaseDate) {
        this.licenseFirstReleaseDate = licenseFirstReleaseDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "license_expiry_date", length = 10)
    public Date getLicenseExpiryDate() {
        return this.licenseExpiryDate;
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    @Column(name = "has_medical_license", nullable = false)
    public boolean isHasMedicalLicense() {
        return this.hasMedicalLicense;
    }

    public void setHasMedicalLicense(boolean hasMedicalLicense) {
        this.hasMedicalLicense = hasMedicalLicense;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "medical_license_expiry_date", length = 10)
    public Date getMedicalLicenseExpiryDate() {
        return this.medicalLicenseExpiryDate;
    }

    public void setMedicalLicenseExpiryDate(Date medicalLicenseExpiryDate) {
        this.medicalLicenseExpiryDate = medicalLicenseExpiryDate;
    }

    @Column(name = "initial_number_of_hours_of_experience")
    public Integer getInitialNumberOfHoursOfExperience() {
        return this.initialNumberOfHoursOfExperience;
    }

    public void setInitialNumberOfHoursOfExperience(Integer initialNumberOfHoursOfExperience) {
        this.initialNumberOfHoursOfExperience = initialNumberOfHoursOfExperience;
    }

    @Column(name = "initial_number_of_takeoffs")
    public Integer getInitialNumberOfTakeoffs() {
        return this.initialNumberOfTakeoffs;
    }

    public void setInitialNumberOfTakeoffs(Integer initialNumberOfTakeoffs) {
        this.initialNumberOfTakeoffs = initialNumberOfTakeoffs;
    }

    @Column(name = "has_silver_badge", nullable = false)
    public boolean isHasSilverBadge() {
        return this.hasSilverBadge;
    }

    public void setHasSilverBadge(boolean hasSilverBadge) {
        this.hasSilverBadge = hasSilverBadge;
    }

    @Column(name = "has_gold_badge", nullable = false)
    public boolean isHasGoldBadge() {
        return this.hasGoldBadge;
    }

    public void setHasGoldBadge(boolean hasGoldBadge) {
        this.hasGoldBadge = hasGoldBadge;
    }

    @Column(name = "has_acrobatics_badge", nullable = false)
    public boolean isHasAcrobaticsBadge() {
        return this.hasAcrobaticsBadge;
    }

    public void setHasAcrobaticsBadge(boolean hasAcrobaticsBadge) {
        this.hasAcrobaticsBadge = hasAcrobaticsBadge;
    }

    @Column(name = "has_ulm_license", nullable = false)
    public boolean isHasUlmLicense() {
        return this.hasUlmLicense;
    }

    public void setHasUlmLicense(boolean hasUlmLicense) {
        this.hasUlmLicense = hasUlmLicense;
    }

    @Column(name = "instructor", nullable = false)
    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

    @Column(name = "has_towing_license", nullable = false)
    public boolean isHasTowingLicense() {
        return this.hasTowingLicense;
    }

    public void setHasTowingLicense(boolean hasTowingLicense) {
        this.hasTowingLicense = hasTowingLicense;
    }

    @Column(name = "status", length = 30)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "added_by", nullable = false, length = 50)
    public String getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "add_date", nullable = false, length = 10)
    public Date getAddDate() {
        return this.addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date", nullable = false, length = 10)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_updated_by", nullable = false, length = 50)
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Member) && (this.hashCode() == obj.hashCode()));
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Transient
    public String getQualifications() {

        StringBuilder sb = new StringBuilder();
        if (hasLicense) {
            sb.append("Licenta planor; ");
        }
        if (isInstructor()) {
            sb.append("Instructor; ");
        }
        if (hasGoldBadge) {
            sb.append("C-ul de aur; ");
        } else if (hasSilverBadge) {
            sb.append("C-ul de argint; ");
        }
        if (hasAcrobaticsBadge) {
            sb.append("Acrobatie; ");
        }
        if (hasTowingLicense) {
            sb.append("Licenta mosor; ");
        }
        if (hasUlmLicense) {
            sb.append("Licenta ULM; ");
        }

        return sb.toString();
    }
}
