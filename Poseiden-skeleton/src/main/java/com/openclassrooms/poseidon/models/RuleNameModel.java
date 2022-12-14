package com.openclassrooms.poseidon.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rulename")
public class RuleNameModel {
    @Id
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
    private String json;

    @Size(max = 512)
    @Column(name = "template")
    private String template;

    @Size(max = 125)
    @Column(name = "sqlStr")
    private String sqlStr;

    @Size(max = 125)
    @Column(name = "sqlPart")
    private String sqlPart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
