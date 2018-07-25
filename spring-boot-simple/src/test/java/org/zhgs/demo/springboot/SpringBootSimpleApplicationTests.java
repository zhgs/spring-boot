package org.zhgs.demo.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhgs.demo.springboot.controller.HelloWordController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSimpleApplicationTests {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloWordController()).build();
	}

	/**
	 * 关于mockmvc参考 https://www.cnblogs.com/lyy-2016/p/6122144.html
	 * @throws Exception
	 */
	@Test
	public void contextLoads() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk()
		).andDo(MockMvcResultHandlers.print()
		).andReturn();
	}

}
