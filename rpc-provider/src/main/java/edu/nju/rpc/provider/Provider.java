package edu.nju.rpc.provider;

import edu.nju.rpc.api.HelloService;
import edu.nju.rpc.impl.HelloServiceImpl;
import edu.nju.rpc.protocal.http.HttpServer;
import edu.nju.rpc.register.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);
    }
}
