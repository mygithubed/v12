package com.it.v12productservice;

import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectService;
import com.it.v12.api.IProdectTypeService;
import com.it.v12.entity.TProduct;
import com.it.v12.entity.TProductType;
import com.it.v12.pojo.TProductVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12ProductServiceApplicationTests {

	@Autowired
	private IProdectService prodectService;

	@Autowired
	private IProdectTypeService prodectTypeService;
	@Test
	public void contextLoads() {
		List<TProductType> lis = prodectTypeService.list();

		System.out.println(lis.size());
	}

}
