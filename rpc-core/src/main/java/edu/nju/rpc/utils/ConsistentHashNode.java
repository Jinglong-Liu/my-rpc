package edu.nju.rpc.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsistentHashNode implements Serializable {
    private Integer point;
    private Object target;
}
