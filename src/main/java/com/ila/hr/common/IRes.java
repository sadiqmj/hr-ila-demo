package com.ila.hr.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IRes {
    public boolean getSuccess();

    public String getStatus();

    @JsonIgnore
    public <T> T getContent();
}
