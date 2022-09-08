package edu.nju.rpc.url;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@ProtobufClass
@NoArgsConstructor
public class URL {
    @Protobuf(fieldType = FieldType.STRING, order = 1)
    private String hostName;
    @Protobuf(fieldType = FieldType.INT32,order = 2)
    private Integer port;
}
