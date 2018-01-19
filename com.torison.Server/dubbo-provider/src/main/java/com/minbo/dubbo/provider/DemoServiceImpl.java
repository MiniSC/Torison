package com.minbo.dubbo.provider;

import com.minbo.api.provider.DemoService;

public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		return "Welcome to Minbo's Dubbo, Hello " + name;
	}

}
