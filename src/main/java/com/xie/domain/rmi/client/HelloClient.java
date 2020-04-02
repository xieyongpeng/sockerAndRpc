package com.xie.domain.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.xie.domain.rmi.ISayHello;

public class HelloClient {
	public static void main(String[] args) {
		try {
			ISayHello hello = (ISayHello) Naming.lookup("rmi://127.0.0.1:8888/sayHello1");
			System.out.println(hello.sayHello("dad司法部"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
