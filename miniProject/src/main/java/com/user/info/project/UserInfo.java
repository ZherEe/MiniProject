package com.user.info.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity(name = "USER_INFO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo{


    @Id
    @JsonProperty("name")
    @Column(name="NAME", nullable=false, unique = true)
    private String name;
    @JsonProperty("salary")
    @Column(name="SALARY")
    @Size(min=1000, max = 4000)
    private Double salary;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
