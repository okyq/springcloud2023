package com.yq.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName : Payment
 * @Description :
 * @Author : yuqian
 * @Date : 2023/1/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private long id;
    private String serial;
    public String toString() {
        return "serial是"+serial+"/n";
    }
}
