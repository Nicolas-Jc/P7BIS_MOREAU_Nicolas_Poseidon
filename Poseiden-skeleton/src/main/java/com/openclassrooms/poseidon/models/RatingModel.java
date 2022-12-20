package com.openclassrooms.poseidon.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rating")
public class RatingModel {
    @Id
    @Column(name = "id")
    private Integer id;

    @Size(max = 125)
    @Column(name = "moodysRating")
    @NotNull(message = "Moodys Rating is mandatory")
    private String moodysRating;

    @Size(max = 125)
    @Column(name = "sandPRating")
    @NotNull(message = "Sand PRating is mandatory")
    private String sandPRating;

    @Size(max = 125)
    @Column(name = "fitchRating")
    @NotNull(message = "Fitch Rating is mandatory")
    private String fitchRating;

    @NotNull(message = "Order Number is mandatory")
    @PositiveOrZero(message = "Order Number should be a decimal number and greater than zero")
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
