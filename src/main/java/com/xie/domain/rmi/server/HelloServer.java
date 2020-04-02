package com.xie.domain.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloServer {
	public static void main(String[] args) throws Exception {
		SayHelloImpl helloImpl = new SayHelloImpl();
		
		Registry registry= LocateRegistry.createRegistry(8888);
		
		Naming.bind("rmi://127.0.0.1:8888/sayHello1", helloImpl);
		
		System.out.println("server start success");
	}
}
