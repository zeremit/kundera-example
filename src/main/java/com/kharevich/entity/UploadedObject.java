package com.kharevich.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class UploadedObject implements BaseEntity<String>{

    @Column(name = "ID", nullable = false)
    protected String id;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "UPLOADED_ON", nullable = false, updatable = false)
    protected Date uploadedOn;

    @Column(name = "UPLOADED_BY", nullable = false, updatable = false)
    protected String uploadedBy;

    @Column(name = "MIME_TYPE", nullable = false, updatable = false)
    protected String mimeType;

    @Column(name = "DELETED", nullable = false)
    protected boolean deleted;

    @Column(name = "FILE_SIZE", nullable = false, updatable = false)
    protected long fileSize;

    public UploadedObject() {
    }

    protected UploadedObject(String name, String mimeType, boolean deleted) {
        this.name = name;
        this.mimeType = mimeType;
        this.deleted = deleted;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "UploadedObject{" +
                "name='" + name + '\'' +
                ", uploadedOn=" + uploadedOn +
                ", uploadedBy='" + uploadedBy + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", deleted=" + deleted +
                ", fileSize=" + fileSize +
                '}';
    }
}