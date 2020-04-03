package com.xie.domain.webService.server;

import javax.xml.ws.Endpoint;

public class Bootstrap {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/vip/hello",new SayHelloImpl());
		System.out.println("publish success");
	}
}
