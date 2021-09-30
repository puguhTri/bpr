package com.mib.customer.bprcustomersvc.dto.response;

import com.mib.customer.bprcustomersvc.utils.CommonsUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeneralResponse<T> {

    private String status;
    private String message;
    private T data;
    private Date timestamp = new Date();
    private String signature;

    public GeneralResponse<T> success() {
        this.status = "00";
        this.message = "Success";
        return this;
    }

    public GeneralResponse<T> success(T t) {
        this.status = "00";
        this.message = "Success";
        if (CommonsUtil.isArrayOrCollection(t)) {
            Map<String, Object> content = new HashMap<>();
            content.put("content", t);
            this.data = (T) content;
        } else {
            this.data = t;
        }
        return this;
    }

    public GeneralResponse<T> success(T t, String signature) {
        this.status = "01";
        this.message = "Success";
        if (CommonsUtil.isArrayOrCollection(t)) {
            Map<String, Object> content = new HashMap<>();
            content.put("content", t);
            this.data = (T) content;
        } else {
            this.data = t;
        }
        this.signature = signature;
        return this;
    }

    public GeneralResponse<T> fail() {
        this.status = "02";
        this.message = "Fail";
        return this;
    }

    public GeneralResponse<T> fail(Exception e) {
        this.status = "03";
        this.message = e.getMessage();
        return this;
    }

    public GeneralResponse<T> fail(Exception e, String message) {
        this.status = "03";
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                ", signature='" + signature + '\'' +
                '}';
    }
}
