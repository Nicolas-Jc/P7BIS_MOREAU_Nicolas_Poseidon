package com.openclassrooms.poseidon.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "bidlist")
public class BidListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bidListId")
    private Integer bidListId;

    @Column(name = "account")
    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    private String account;

    @Column(name = "type")
    @NotBlank(message = "Type is mandatory")
    @Size(max = 30)
    private String type;

    @Column(name = "bidQuantity")
    @NotNull(message = "Bid Quantity is mandatory")
    @Positive(message = "Bid Quantity must be greater than zero")
    private Double bidQuantity;

    @Column(name = "askQuantity")
    private Double askQuantity;

    @Column(name = "bid")
    private Double bid;

    @Column(name = "ask")
    private Double ask;

    @Size(max = 125)
    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    @Size(max = 125)
    @Column(name = "commentary")
    private String commentary;

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
    @Column(name = "sourceListid")
    private String sourceListId;

    @Size(max = 125)
    @Column(name = "side")
    private String side;

}
