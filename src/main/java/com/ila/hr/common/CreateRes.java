package com.ila.hr.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateRes implements IRes {
    public int id;

    public CreateRes() {
        super();
    }

    public CreateRes(int id) {
        super();
        this.id = id;
    }

    @JsonIgnore
    @Override
    public boolean getSuccess() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getStatus() {
        return StatusId.SUCCESS;
    }

    @JsonIgnore
    @Override
    public <T> T getContent() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
