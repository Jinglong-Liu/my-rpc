package edu.nju.rpc.provider;

import edu.nju.rpc.api.HelloService;
import edu.nju.rpc.impl.HelloServiceImpl;
import edu.nju.rpc.protocal.http.HttpServer;
import edu.nju.rpc.register.LocalRegister;
import edu.nju.rpc.register.RemoteMapRegister;
import edu.nju.rpc.url.URL;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //本地注册
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        //服务注册 接口名，URL = ip地址 + 端口
        //机器ip + 用户配置
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(),url);

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }
}
