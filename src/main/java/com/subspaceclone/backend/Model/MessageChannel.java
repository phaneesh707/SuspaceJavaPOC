package com.subspaceclone.backend.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MESSAGE")
public class MessageChannel extends Channel {
    private Boolean isDm;

    public Boolean getDm() {
        return isDm;
    }

    public void setDm(Boolean dm) {
        isDm = dm;
    }

}
