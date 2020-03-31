package com.xie.domain.rpc.client;

import com.xie.domain.rpc.IHello;

public class RpcClient {
	public static void main(String[] args) {
		RpcClientProxy client = new RpcClientProxy<>(IHello.class,"localhost",6666);
		IHello hello = (IHello)client.getClientIntance();
		System.out.println(hello.sayHello("socket rpc"));
	}
}
