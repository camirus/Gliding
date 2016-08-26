package com.multimedianetwork.gliding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_type", catalog = "gliding")
public class MemberType implements java.io.Serializable {

    private Integer id;
    private String name;
    private String description;
    private boolean oneDayMember;

    public MemberType() {
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

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "one_day_member")
    public boolean isOneDayMember() {
        return oneDayMember;
    }

    public void setOneDayMember(boolean oneDayMember) {
        this.oneDayMember = oneDayMember;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof MemberType) && (obj.hashCode() == this.hashCode());
    }
}
