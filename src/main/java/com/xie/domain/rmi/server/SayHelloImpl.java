package com.xie.domain.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.xie.domain.rmi.ISayHello;

public class SayHelloImpl extends UnicastRemoteObject implements ISayHello{
	
	public SayHelloImpl() throws RemoteException {
	}

	public String sayHello(String name) throws RemoteException{
		return "Hello Mic->"+name;
	}
}
