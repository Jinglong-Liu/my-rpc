package edu.nju.rpc.invocation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramType;
    private Object[] params;
}
