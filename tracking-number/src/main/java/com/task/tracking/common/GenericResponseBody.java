package com.task.tracking.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseBody {

    private Integer code;
    private boolean success;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
