package com.yq.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName : CommonResult
 * @Description :
 * @Author : yuqian
 * @Date : 2023/1/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T      data;
    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
