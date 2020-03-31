package com.xie.domain.rpc.service;

import com.xie.domain.rpc.IHello;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		new RpcServer(6666).register(IHello.class, HelloServiceImpl.class).bind();
	}
}
