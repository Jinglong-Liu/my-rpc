package edu.nju.rpc.test;

import edu.nju.rpc.api.HelloService;
import edu.nju.rpc.factory.ProxyFactory;
import edu.nju.rpc.invocation.Invocation;

import java.nio.charset.StandardCharsets;

public class TestClient {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.hello("Alice");//invoke

        System.out.println(result);
    }
}
