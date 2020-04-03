package com.xie.domain.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISayHello {
	
	@WebMethod
	String sayHello(String name);
}
