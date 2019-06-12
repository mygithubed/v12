package com.it.v12productservice;

import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectService;
import com.it.v12.entity.TProduct;
import com.it.v12.pojo.TProductVO;
import org.junit.Assert;
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
	@Test
	public void contextLoads() {

		TProduct p = new TProduct();
		p.setName("华为手机");
		p.setFlag(true);
		p.setPrice(1122L);
		p.setSalePoint("性能高！");
		p.setSalePrice(1234L);
		p.setImages("wu");
		p.setTypeId(2L);
		p.setTypeName("数码电子");

		TProductVO vo = new TProductVO();
		vo.setProductDesc("价格优惠，性能好！");
		vo.setProduct(p);

		Long id = prodectService.saves(vo);

		System.out.println(id);
	}

}
