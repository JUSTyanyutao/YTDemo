package com.just.ytdemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.just.ytdemo.dao.StudentDao;
import com.just.ytdemo.entity.Student;
import com.just.ytdemo.exception.BaseErrorCode;
import com.just.ytdemo.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EsStudentService {


    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    private String index = "student_es_v1";

    @Autowired
    private StudentDao studentDao;





    /**
     * 创建索引
     */
    public void createIndex() {
        try {
            //1.使用client获取操作索引对象
            IndicesClient indices = restHighLevelClient.indices();
            //2.具体操作获取返回值
            //2.1 设置索引名称
            CreateIndexRequest createIndexRequest=new CreateIndexRequest(index);
//            createIndexRequest.mapping()

            CreateIndexResponse response = indices.create(createIndexRequest, RequestOptions.DEFAULT);
            //3.根据返回值判断结果
            System.out.println(response.isAcknowledged());
            if (response.isAcknowledged() || response.isShardsAcknowledged()) {
                log.info("创建索引成功！索引名称为{}", index);
            }else{
                throw new BaseException(BaseErrorCode.FAILED.getCode(),"创建索引失败！");
            }

        } catch (Exception e) {
            log.error("创建索引失败！", e);
            throw new BaseException(BaseErrorCode.FAILED.getCode(),"创建索引失败！");
        }
    }



    /**
     * 删除索引
     */
    public void delIndex() {
        try {
            //1.使用client获取操作索引对象
            IndicesClient indices = restHighLevelClient.indices();
            //2.具体操作获取返回值
            //2.1 设置索引名称
            DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest(index);
            AcknowledgedResponse response = indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
            //3.根据返回值判断结果
            System.out.println(response.isAcknowledged());
            if (response.isAcknowledged()) {
                log.info("删除索引成功！索引名称为{}", index);
            }else{
                throw new BaseException(BaseErrorCode.FAILED.getCode(),"删除索引失败！");
            }
        } catch (Exception e) {
            log.error("删除索引失败！", e);
            throw new BaseException(BaseErrorCode.FAILED.getCode(),"删除索引失败！");
        }
    }


    /**
     * 查询索引
     */
    public void getIndex() {
        try {
            //1.使用client获取操作索引对象
            IndicesClient indices = restHighLevelClient.indices();
            //2.具体操作获取返回值
            //2.1 设置索引名称
            GetIndexRequest getIndexRequest=new GetIndexRequest(index);
            GetIndexResponse response = indices.get(getIndexRequest, RequestOptions.DEFAULT);
            //3.根据返回值判断结果
            System.out.println(response);
            Map<String, MappingMetadata> mappings = response.getMappings();
            //iter 提示foreach
            for (String key : mappings.keySet()) {
                System.out.println(key+"==="+mappings.get(key).getSourceAsMap());
            }
        } catch (Exception e) {
            log.error("获取索引失败！", e);
            throw new BaseException(BaseErrorCode.FAILED.getCode(),"获取索引失败！");
        }
    }


    /**
     * 校验索引是否存在
     */
    public void existIndex() {
        try {
            //1.使用client获取操作索引对象
            IndicesClient indices = restHighLevelClient.indices();
            //2.具体操作获取返回值
            //2.1 设置索引名称
            GetIndexRequest getIndexRequest=new GetIndexRequest(index);
            Boolean flag = indices.exists(getIndexRequest, RequestOptions.DEFAULT);
            //3.根据返回值判断结果
            System.out.println(flag);
        } catch (Exception e) {
            log.error("获取索引失败！", e);
            throw new BaseException(BaseErrorCode.FAILED.getCode(),"获取索引失败！");
        }
    }


    /**
     * 映射mapping
     */
    public void putMapping(){

    }


    /**
     * 批量插入
     */
    public void addDocs(){
        List<Student> list = studentDao.queryAll();
        BulkRequest bulkRequest = new BulkRequest();
        for (Student student : list) {
            String data = JSON.toJSONString(student);
            IndexRequest request = new IndexRequest(index)
                    .id(student.getId().toString())
                    .source(data, XContentType.JSON);
            bulkRequest.add(request);
        }
        try {
            BulkResponse response = restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
            if (response.hasFailures()) {
                log.error("student批量上传es失败");
            } else {
                for (BulkItemResponse itemResponse  : response) {
                    if (itemResponse.isFailed()) {
                        BulkItemResponse.Failure failure = itemResponse.getFailure();
                        log.error("批量学生文档增加失败:index:{},type:{},id:{}", failure.getIndex(), failure.getType(), failure.getId());
                        log.error("批量增加学生失败详情:{}", failure.getMessage());
                    } else {
                        log.info("批量学生文档增加成功:index:{},type:{},id:{}", itemResponse.getIndex(), itemResponse.getType(), itemResponse.getId());
                    }
                }

            }
        } catch (IOException e) {
            log.error("student批量上传es失败");
            e.printStackTrace();
        }

    }



    /**
     *  单个插入/修改
     */
    public void addDoc(){
        Student student = studentDao.get(2L);

        String data = JSON.toJSONString(student);
        IndexRequest request = new IndexRequest(index)
                    .id(student.getId().toString())
                    .source(data, XContentType.JSON);

        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            String index = response.getIndex();
            String documentId = response.getId();
            if (response.getResult() == DocWriteResponse.Result.CREATED) {
                log.info("新增文档成功！ index: {},  id: {}", index ,  documentId);
            } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
                log.info("修改文档成功！ index: {},  id: {}", index,  documentId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Object queryConditons(){
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        boolQueryBuilder.must(QueryBuilders.queryStringQuery("闫雨涛").defaultField("name"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("name","快"));
//        boolQueryBuilder.must(QueryBuilders.termQuery("id",1));


        FieldSortBuilder order = SortBuilders.fieldSort("updateTime").order(SortOrder.DESC);

        // 多条件查询
        searchSourceBuilder.query(boolQueryBuilder);
        // 排序
        searchSourceBuilder.sort(order);
        // 分页 从某个元素 往后找10个
        searchSourceBuilder.from(0).size(10);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("文档查询异常", e);
            throw new BaseException(BaseErrorCode.FAILED.getCode(),"文档查询异常");
        }
        System.out.println(JSONObject.toJSON(response));

        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        List<Map<String, Object>> results = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            results.add(hit.getSourceAsMap());
        }

        return results;

    }




}
