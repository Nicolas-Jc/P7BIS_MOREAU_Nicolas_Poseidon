package com.openclassrooms.poseidon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "curvepoint")
public class CurvePointModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Positive(message = "Curve point Id must be greater than zero")
    @NotNull(message = "Curve point Id is mandatory")
    @Column(name = "curveId")
    private Integer curveId;

    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @NotNull(message = "Term is mandatory")
    @PositiveOrZero(message = "Term should be a decimal number and greater than zero")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "Value is mandatory")
    @PositiveOrZero(message = "Value should be a decimal number and greater than zero")
    @Column(name = "value")
    private Double value;

    @Column(name = "creationDate")
    private Timestamp creationDate;

   }
