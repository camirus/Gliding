package com.multimedianetwork.gliding.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "flight_package", catalog = "gliding", uniqueConstraints =
        @UniqueConstraint(columnNames = "name"))
public class FlightPackage implements java.io.Serializable {

    private Integer id;
    private String name;
    private String description;
    private float price;
    private float takeoffCost;
    private float perHourCost;

    public FlightPackage() {
    }

    public FlightPackage(float price, float takeoffCost, float perHourCost) {
        this.price = price;
        this.takeoffCost = takeoffCost;
        this.perHourCost = perHourCost;
    }

    public FlightPackage(String name, String description, float price, float takeoffCost, float perHourCost) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.takeoffCost = takeoffCost;
        this.perHourCost = perHourCost;
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

    @Column(name = "name", unique = true, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 250)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price", nullable = false, precision = 12, scale = 0)
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "takeoff_cost", nullable = false, precision = 12, scale = 0)
    public float getTakeoffCost() {
        return this.takeoffCost;
    }

    public void setTakeoffCost(float takeoffCost) {
        this.takeoffCost = takeoffCost;
    }

    @Column(name = "per_hour_cost", nullable = false, precision = 12, scale = 0)
    public float getPerHourCost() {
        return this.perHourCost;
    }

    public void setPerHourCost(float perHourCost) {
        this.perHourCost = perHourCost;
    }
}
