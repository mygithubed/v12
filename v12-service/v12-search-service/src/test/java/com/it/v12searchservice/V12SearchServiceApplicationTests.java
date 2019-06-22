package com.it.v12searchservice;

import com.it.v12.api.ISearchApi;
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
