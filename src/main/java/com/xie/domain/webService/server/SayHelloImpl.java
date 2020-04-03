package com.xie.domain.webService.server;

import javax.jws.WebService;

import com.xie.domain.webService.ISayHello;

@WebService
public class SayHelloImpl implements ISayHello{
	public String sayHello(String name) {
        System.out.println("call sayHello()");
        return "Hello ,"+name+",I'am 菲菲";
    }
}
