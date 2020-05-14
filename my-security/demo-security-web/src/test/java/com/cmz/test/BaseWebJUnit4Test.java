package com.cmz.test;

import com.cmz.web.WebApplication;
import org.aspectj.lang.annotation.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class BaseWebJUnit4Test {

    @Autowired
    private WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before("")
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .alwaysDo(new TestPrintingResultHandler()).build();
    }
}