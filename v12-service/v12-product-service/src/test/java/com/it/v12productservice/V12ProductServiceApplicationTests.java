package com.it.v12productservice;

import com.it.v12.api.IProdectService;
import com.it.v12.entity.TProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12ProductServiceApplicationTests {

	@Autowired
	private IProdectService prodectService;
	@Test
	public void contextLoads() {
		TProduct t = prodectService.selectByPrimaryKey(1L);

		System.out.println(t.getName());
	}

}
