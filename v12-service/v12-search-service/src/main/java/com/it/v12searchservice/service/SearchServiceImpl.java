package com.it.v12searchservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.ISearchApi;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TProduct;
import com.it.v12.mapper.TProductMapper;
import com.it.v12.common.pojo.PageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date:2019/6/17
 * Time:11:19
 * @author 曾志鹏
 */
@Service
public class SearchServiceImpl implements ISearchApi {


    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    /**
     * 返回的RseBean是在 common中的pojo中定义的   全量的同步
     * @return
     */
    @Override
    public RsetBean syncAllData() {

        //获取数据库中的数据
        List<TProduct> list = productMapper.list();
        //将数据导入索引库中去
        for (TProduct product : list) {
            SolrInputDocument document = new SolrInputDocument();
            //ID
            document.setField("id",product.getId());
            //名称
            document.setField("product_name",product.getName());
            //价格
            document.setField("product_price",product.getPrice());
            //折后价
            document.setField("product_sale_price",product.getSalePrice());
            //卖点
            document.setField("product_sale_point",product.getSalePoint());
            //图片
            document.setField("product_images",product.getImages());
            try {
                //将数据添加
                solrClient.add(document);
            } catch (IOException | SolrServerException e) {
                e.printStackTrace();
                return  new RsetBean("404","数据同步失败了！");
            }
            try {
                //提交事务
                solrClient.commit();
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return  new RsetBean("404","数据同步失败了！");
            }
        }
        return new RsetBean("200","数据同步成功了！");
    }

    /**
     * 根据ID进行增量同步
     *
     * @param id
     * @return
     */
    @Override
    public RsetBean queryDataById(Long id) {
        //1、根据ID获取数据
        TProduct product = productMapper.selectByPrimaryKey(id);
        //2、构建document对象
         SolrInputDocument document = new SolrInputDocument();
        //ID
        document.setField("id",product.getId());
        //名称
        document.setField("product_name",product.getName());
        //价格
        document.setField("product_price",product.getPrice());
        //折后价
        document.setField("product_sale_price",product.getSalePrice());
        //卖点
        document.setField("product_sale_point",product.getSalePoint());
        //图片
        document.setField("product_images",product.getImages());
        //将数据添加
        try {
            solrClient.add(document);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return  new RsetBean("404","数据同步失败了！");
        }
        try {
            //提交事务
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return  new RsetBean("404","数据同步失败了！");
        }
        return new RsetBean("200","数据同步成功了！");
    }

    /**
     * 搜索服务的接口实现
     * @param searchWords
     * @return
     */
    @Override
    public RsetBean searchByKeyWord(String searchWords) {

        //构建查询的条件
        SolrQuery solrQuery = new SolrQuery();
        //进行非空的判断
        if(!StringUtils.isAllEmpty(searchWords)){
            solrQuery.setQuery("product_keywords:"+searchWords);
        }else{
            solrQuery.setQuery("product_keywords:*");
        }

        //打开高亮的开关
        solrQuery.setHighlight(true);
        //商品的名称进行高亮
        solrQuery.addHighlightField("product_name");
        //前置加的东西
        solrQuery.setHighlightSimplePre("<font color='red'>");
        //后置
        solrQuery.setHighlightSimplePost("</font>");


        //分页的设置
        //solrQuery.setStart(pageIndex)

        List<TProduct> productList = null;
        try {
            QueryResponse response = solrClient.query(solrQuery);

            SolrDocumentList results = response.getResults();

            productList = new ArrayList<>(results.size());

            //获取进行了高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument document : results) {
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                /**product.setName(document.getFieldValue("product_name").toString());**/
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));

                product.setSalePrice(Long.parseLong(document.getFieldValue("product_sale_price").toString()));

                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                product.setImages(document.getFieldValue("product_images").toString());
                //对需要高亮的进行设置  product_name
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id"));
                //对应记录的高亮信息
                List<String> productNameHighLighting  = map.get("product_name");
                if(productNameHighLighting != null && productNameHighLighting.size() > 0){
                    //有高亮信息的时候
                    product.setName(productNameHighLighting.get(0));
                }else{
                    //没有高亮信息的时候
                    product.setName(document.getFieldValue("product_name").toString());
                }
                //
                productList.add(product);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new  RsetBean("404","null");
        }
        //进行查询
        return new RsetBean("200",productList);
    }

    /**
     * 分页显示搜索的结果
     *
     * @param searchWords
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageResultBean<TProduct> searchPagesByKeyWord(String searchWords, Long pageIndex, Long pageSize) {
        //构建查询的条件
        SolrQuery solrQuery = new SolrQuery();
        //进行非空的判断
        if(!StringUtils.isAllEmpty(searchWords)){
            solrQuery.setQuery("product_keywords:"+searchWords);
        }else{
            solrQuery.setQuery("product_keywords:*");
        }
        //打开高亮的开关
        solrQuery.setHighlight(true);
        //商品的名称进行高亮
        solrQuery.addHighlightField("product_name");
        //前置加的东西
        solrQuery.setHighlightSimplePre("<font color='red'>");
        //后置
        solrQuery.setHighlightSimplePost("</font>");

        PageResultBean<TProduct> pageResultBean = new PageResultBean<>();

        //分页的设置
        if(pageIndex<0L){
            pageIndex =1L;
        }
        if(pageSize<0L){
            pageSize =1L;
        }
        int start = ((pageIndex.intValue()-1)*pageSize.intValue());
        //分页的开始下标
        solrQuery.setStart(start);
        //每页显示几条
        solrQuery.setRows(pageSize.intValue());
        //存储总的记录数
        long numFound = 0L;

        List<TProduct> productList = null;
        try {
            QueryResponse response = solrClient.query(solrQuery);
            SolrDocumentList results = response.getResults();

            //
            //总记录数
             numFound = results.getNumFound();
            //
            productList = new ArrayList<>(results.size());
            //获取进行了高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument document : results) {
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setSalePrice(Long.parseLong(document.getFieldValue("product_sale_price").toString()));
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                product.setImages(document.getFieldValue("product_images").toString());
                //对需要高亮的进行设置  product_name
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id"));
                //对应记录的高亮信息
                List<String> productNameHighLighting  = map.get("product_name");
                if(productNameHighLighting != null && productNameHighLighting.size() > 0){
                    //有高亮信息的时候
                    product.setName(productNameHighLighting.get(0));
                }else{
                    //没有高亮信息的时候
                    product.setName(document.getFieldValue("product_name").toString());
                }
                //
                productList.add(product);
            }
            //===============================

            //总的记录数
            pageResultBean.setTotal(numFound);
            //每页显示多少条
            pageResultBean.setPageSize(pageSize.intValue());
            //当前页
            pageResultBean.setPageNum(pageIndex.intValue());
            //计算总的页数
            int pages = (int)(Math.ceil(pageResultBean.getTotal()*1.0/pageResultBean.getPageSize()));
            pageResultBean.setPages(pages);
            //导航页码数
            pageResultBean.setNavigatePages(pages);
            //查询出来的集合数据
            pageResultBean.setList(productList);
            //==========================
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        //进行查询
        return pageResultBean;
    }


}
