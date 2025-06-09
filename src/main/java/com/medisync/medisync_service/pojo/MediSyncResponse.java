package com.medisync.medisync_service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediSyncResponse<T> {
    private T data;
    private String errorMessage;
    private Boolean isError = false;

    public MediSyncResponse(String errorMessage, Boolean isError) {
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    public MediSyncResponse(T data) {
        this.data = data;
    }

}
