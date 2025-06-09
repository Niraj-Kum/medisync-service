package com.medisync.medisync_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medisync.medisync_service.utils.constants.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass
public class CommonFields {

    @Column(name = "created_date", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_FORMAT)
    @JsonIgnore
    private Date createdDate = new Date();

    @Column(name = "updated_date", insertable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_FORMAT)
    @JsonIgnore
    private Date updatedDate = new Date();

    @Column(name = "is_archived")
    @JsonIgnore
    private Boolean isArchived = false;
}
