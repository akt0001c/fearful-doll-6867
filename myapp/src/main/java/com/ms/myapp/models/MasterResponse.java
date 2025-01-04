package com.ms.myapp.models;

import com.ms.myapp.models.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterResponse<T> {
    private String metaData;
    private Response<T> response;

}
