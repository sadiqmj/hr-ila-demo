package com.ila.hr.common;

public class Res implements IRes {
    public boolean success;
    public String status;
    public Object message;
    public Object data;

    @Override
    public boolean getSuccess() {
        return success;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getContent() {
        return (T) data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
