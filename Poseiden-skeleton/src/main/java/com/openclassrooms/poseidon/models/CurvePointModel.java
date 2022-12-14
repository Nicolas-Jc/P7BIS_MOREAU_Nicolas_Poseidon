package com.openclassrooms.poseidon.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "curvepoint")
public class CurvePointModel {
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Curve point Id is mandatory")
    @Column(name = "curveId")
    private Integer curveId;

    @Column(name = "asOfDate")
    private LocalDateTime asOfDate;

    @NotNull(message = "Term is mandatory")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "Value is mandatory")
    @Column(name = "value")
    private Double value;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

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

    public LocalDateTime getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDateTime asOfDate) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
