package com.user.info.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersResults {

    @JsonProperty("results")
    private List<UserInfo> results;

    @JsonProperty("error")
    private String error;

    public List<UserInfo> getResults() {
        return results;
    }

    public void setResults(List<UserInfo> results) {
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
