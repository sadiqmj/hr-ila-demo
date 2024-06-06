package com.ila.hr.common;

public class ResBuilder {
    private Res res;

    private void initRes() {
        if (res == null) {
            res = new Res();
        }
    }

    public ResBuilder success() {
        initRes();
        res.success = true;
        res.status = StatusId.SUCCESS;
        return this;
    }

    public ResBuilder error() {
        return error(StatusId.ERROR);
    }

    public ResBuilder error(String statusId) {
        initRes();
        res.success = false;
        res.status = statusId;
        return this;
    }

    public ResBuilder message(Object message) {
        initRes();
        res.message = message;
        return this;
    }

    public ResBuilder buildMessage() {
        initRes();
        // todo build message
        return this;
    }

    public ResBuilder data(Object content) {
        initRes();
        res.data = content;
        return this;
    }

    public IRes build() {
        return res;
    }
}
