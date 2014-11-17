package com.kharevich.entity;

import java.io.Serializable;


public interface BaseEntity<PK extends Serializable> extends Serializable {

    PK getId();

    void setId(PK id);
}
