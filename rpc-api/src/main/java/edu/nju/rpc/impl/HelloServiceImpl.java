package edu.nju.rpc.impl;

import edu.nju.rpc.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello: " + name;
    }
}
