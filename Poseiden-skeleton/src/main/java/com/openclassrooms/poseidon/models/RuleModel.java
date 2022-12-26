package com.openclassrooms.poseidon.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "rulename")
public class RuleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Rule name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 125)
    @Column(name = "description")
    private String description;

    @Size(max = 125)
    @Column(name = "json")
    @NotBlank(message = "json is mandatory")
    private String json;

    @Size(max = 512)
    @Column(name = "template")
    @NotBlank(message = "template is mandatory")
    private String template;

    @Size(max = 125)
    @Column(name = "sqlStr")
    @NotBlank(message = "sqlStr is mandatory")
    private String sqlStr;

    @Size(max = 125)
    @Column(name = "sqlPart")
    @NotBlank(message = "sqlPart is mandatory")
    private String sqlPart;

   }
