package com.loyalty.loyalty_simulator.dto;

import java.time.LocalDateTime;


public class GeneralErrorResponse {
    private String uuid;
    private String message;
    private Integer statusCode;
    private String statusName;
    private String fileName;
    private String path;
    private String method;
    private LocalDateTime timestamp;

    public GeneralErrorResponse(String uuid, String message, Integer statusCode, String statusName, String fileName, String path, String method, LocalDateTime timestamp) {
        this.uuid = uuid;
        this.message = message;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.fileName = fileName;
        this.path = path;
        this.method = method;
        this.timestamp = timestamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
