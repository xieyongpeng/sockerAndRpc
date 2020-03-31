package com.xie.domain.rpc.service;

import com.xie.domain.rpc.IHello;

public class HelloServiceImpl implements IHello{

	@Override
	public String sayHello(String string) {
		return "你好:".concat ( string );
	}
}
