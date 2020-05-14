package com.cmz.test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

public class TestPrintingResultHandler implements ResultHandler {

	@Override
	public void handle(MvcResult result) throws Exception {
		MockHttpServletRequest req = result.getRequest();
		MockHttpServletResponse resp = result.getResponse();

		System.out.println(String.format("%20s = %s", "HTTP Method", req.getMethod()));
		System.out.println(String.format("%20s = %s", "Request URI", req.getRequestURI()));
		System.out.println(String.format("%20s = %s", "Status", resp.getStatus()));
		System.out.println(String.format("%20s = %s", "Body", resp.getContentAsString()));
	}

}