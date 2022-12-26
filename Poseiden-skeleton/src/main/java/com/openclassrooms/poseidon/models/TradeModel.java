package com.openclassrooms.poseidon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Table(name = "trade")
public class TradeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tradeId")
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    @Column(name = "account")
    private String account;

    @Size(max = 30)
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @Column(name = "buyQuantity")
    @PositiveOrZero(message = "Buy Quantity must be greater than or equal to zero")
    private Double buyQuantity;

    @Column(name = "sellQuantity")
    private Double sellQuantity;

    @Column(name = "buyPrice")
    private Double buyPrice;

    @Column(name = "sellPrice")
    private Double sellPrice;

    @Size(max = 125)
    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    @Size(max = 125)
    @Column(name = "security")
    private String security;

    @Size(max = 10)
    @Column(name = "status")
    private String status;

    @Size(max = 125)
    @Column(name = "trader")
    private String trader;

    @Size(max = 125)
    @Column(name = "book")
    private String book;

    @Size(max = 125)
    @Column(name = "creationName")
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Size(max = 125)
    @Column(name = "revisionName")
    private String revisionName;

    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    @Size(max = 125)
    @Column(name = "dealName")
    private String dealName;

    @Size(max = 125)
    @Column(name = "dealType")
    private String dealType;

    @Size(max = 125)
    @Column(name = "sourceListId")
    private String sourceListId;

    @Size(max = 125)
    @Column(name = "side")
    private String side;

  }
