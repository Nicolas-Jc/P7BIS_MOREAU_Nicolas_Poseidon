package com.openclassrooms.poseidon.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "curvepoint")
public class CurvePointModel {
    @Id
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
