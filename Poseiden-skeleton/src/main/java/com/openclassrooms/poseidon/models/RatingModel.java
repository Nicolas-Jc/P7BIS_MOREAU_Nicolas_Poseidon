package com.openclassrooms.poseidon.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rating")
public class RatingModel {
    @Id
    @Column(name = "id")
    private Integer id;

    @Size(max = 125)
    @Column(name = "moodysRating")
    private String moodysRating;

    @Size(max = 125)
    @Column(name = "sandPRating")
    private String sandPRating;

    @Size(max = 125)
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotNull(message = "Order Number is mandatory")
    @Column(name = "orderNumber")
    private Integer orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
