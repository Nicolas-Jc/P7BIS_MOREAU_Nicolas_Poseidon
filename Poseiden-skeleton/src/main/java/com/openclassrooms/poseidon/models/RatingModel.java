package com.openclassrooms.poseidon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "rating")
public class RatingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Moodys Rating is mandatory")
    @Size(max = 125)
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "Sand PRating is mandatory")
    @Size(max = 125)
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "Fitch Rating is mandatory")
    @Size(max = 125)
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotNull(message = "Order Number is mandatory")
    @PositiveOrZero(message = "Order Number should be a decimal number and greater than zero")
    @Column(name = "orderNumber")
    private Integer orderNumber;

   }
