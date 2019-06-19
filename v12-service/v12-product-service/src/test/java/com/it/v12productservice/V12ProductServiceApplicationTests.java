package com.it.v12productservice;

import com.it.v12.api.IProdectDescService;
import com.it.v12.api.IProdectService;
import com.it.v12.api.IProdectTypeService;
import com.it.v12.entity.TProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12ProductServiceApplicationTests {

	@Autowired
	private IProdectService prodectService;

	@Autowired
	private IProdectTypeService prodectTypeService;

	@Autowired
	private IProdectDescService prodectDescService;


	@Test
	public void contextLoads() {
		String s = prodectDescService.selectByproductDesc(9L);
		System.out.println(s);
	}


	@Test
	public void testList() {
		List<TProductType> list = prodectTypeService.list();

		System.out.println(list.size());
	}

}
