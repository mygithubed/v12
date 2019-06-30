package com.it.v12searchservice;

import com.it.v12.api.ISearchApi;
import com.it.v12.common.pojo.PageResultBean;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TProduct;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12SearchServiceApplicationTests {

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private ISearchApi searchApi;


	@Test
	public void queryPages(){
		PageResultBean<TProduct> pageResultBean = searchApi.searchPagesByKeyWord("华为", 1L, 3L);

		for (TProduct product : pageResultBean.getList()) {
			System.out.println(product.getId());
			System.out.println(product.getName());
		}

		for (int i = 0; i < pageResultBean.getNavigatepageNums().length; i++) {
			System.out.println(i);
		}
		System.out.println(pageResultBean.getNavigatepageNums());
		System.out.println(pageResultBean.getPages()+"===========总页数");
		System.out.println(pageResultBean.getPageNum());
		System.out.println(pageResultBean.getTotal());

		/*RsetBean<PageResultBean> rsetBean = searchApi.searchPagesByKeyWord("华为", 1L, 2L);
		System.out.println("每页显示的条数："+rsetBean.getData().getPageSize());
		System.out.println("总记录数："+rsetBean.getData().getTotal());
		System.out.println("当前页数："+rsetBean.getData().getPageNum());
		System.out.println("总页数："+rsetBean.getData().getPages());
		System.out.println("集合的大小："+rsetBean.getData().getList().size());
		System.out.println("导航的页码数："+rsetBean.getData().getNavigatePages());
		System.out.println("导航的集合数："+rsetBean.getData().getNavigatepageNums().length);
		for (Object tProduct : rsetBean.getData().getList()) {
			System.out.println(tProduct);
		}*/

	}
	@Test
	public void queryBywoksList(){
		RsetBean<List<TProduct>> rsetBean = searchApi.searchByKeyWord("");
		List<TProduct> data = rsetBean.getData();
		for (TProduct d : data) {
			System.out.println(d.getId());
			System.out.println(d.getName());
		}
	}

	/**
	 * 初始化数据
	 */
	@Test
	public void testSearchApi(){
		RsetBean rsetBean = searchApi.syncAllData();
		System.out.println(rsetBean.getStatCodes());
	}

	@Test
	public void add() throws Exception{
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id",2);
		document.setField("product_name","小米迷你电脑！");
		document.setField("product_price",88888);
		document.setField("product_images","暂无");
		document.setField("product_sale_point","小米首发限量版");
		solrClient.add(document);
		solrClient.commit();
		System.out.println("添加数据OK！");
	}

	@Test
	public void select() throws  Exception{
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("product_name:华为迷你电脑");

		QueryResponse query = solrClient.query(solrQuery);

		SolrDocumentList results = query.getResults();

		long numFound = results.getNumFound();

		System.out.println(numFound);

		for (SolrDocument s : results) {
			System.out.println(s.get("product_name"));
			System.out.println(s.get("product_price"));
			System.out.println(s.get("product_sale_point"));
		}
	}

	@Test
	public void deleteById() throws IOException, SolrServerException {
		solrClient.deleteById("2");
		solrClient.commit();
		System.out.println("删除OK");
	}

	@Test
	public void deleteByName() throws IOException, SolrServerException {
		solrClient.deleteByQuery("*:*");
		solrClient.commit();

	}

}
