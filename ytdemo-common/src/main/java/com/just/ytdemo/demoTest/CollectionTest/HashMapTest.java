package com.just.ytdemo.demoTest.CollectionTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yutao.yan
 * @date 2018/8/8 16:37
 *
 * Hash的底层是散列表  java中散列表是 数组+链表 实现的
 *
 * HashMap 特点：
 * 无序  允许为null  不同步
 * 底层由散列表（哈希表）实现
 * 初始容量和装载因子对HashMap影响挺大
 *
 */
public class HashMapTest {


    public static File createFile(String path, String fileName) throws IOException {

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
            if (!f.exists()) {
                System.out.println("创建目录失败啦");
            }
        }
        File file = new File(f, fileName);
        if (!file.exists()) {
            file.createNewFile();
            if (!file.exists()) {
                System.out.println("创建文件失败啦");
            }
        }
        return new File(path + fileName);
    }



    public static void main(String[] args) {

//        try {
//            String fileName ="广告素材行业数据-%s.csv";
//            File file = createFile("D:\\bb\\aa\\demo数据\\",fileName);
//
//        }catch (Exception e){
//            System.out.println("创建失败啦");
//        }


        String s = "{\"CallbackCommand\":\"Group.CallbackAfterSendMsg\",\"From_Account\":\"1073260113\",\"GroupId\":\"o2efcb58857jbW6Ki\",\"MsgBody\":[{\"MsgContent\":{\"Data\":\"{\\\"cmd\\\":\\\"CustomBuy\\\",\\\"data\\\":{\\\"nickName\\\":\\\"我好累啊哇??\\\",\\\"text\\\":\\\"购物车购买：10957030100\\\"}}\",\"Desc\":\"\",\"Ext\":\"\"},\"MsgType\":\"TIMCustomElem\"},{\"MsgContent\":{\"Text\":\"购物车购买：10957030100\"},\"MsgType\":\"TIMTextElem\"}],\"MsgSeq\":925,\"MsgTime\":1535137638,\"Type\":\"AVChatRoom\"}";
        JSONObject param = JSON.parseObject(s);

        JSONArray msgBodys = param.getJSONArray("MsgBody");

        StringBuilder msgBuilder = new StringBuilder();
        for (Object msgBodyObj : msgBodys) {
            JSONObject msgBody = (JSONObject) msgBodyObj;
            JSONObject msgContent = msgBody.getJSONObject("MsgContent");
            String msgType = msgBody.getString("MsgType");
            if (msgType == null || !"TIMCustomElem".equals(msgType)) {
                continue;
            }
            if (msgContent != null) {
                JSONObject dataObj = msgContent.getJSONObject("Data");
                if (dataObj != null) {
                    String cmd = dataObj.getString("cmd");
                    if (cmd != null && "CustomTextMsg".equals(cmd)) {
                        String data = dataObj.getString("data");
                        if (data != null && !"".equals(data)  ) {
                            msgBuilder.append(data);
                        }
                    }
                }
            }
        }

        System.out.println(msgBuilder);

//        if (CollectionUtils.isEmpty(msgBodys)) {
//            log.error("消息发送后:消息体为空,bodyParam:{}", bodyParam);
//            return false;
//        }


//        HashMap hashMap = new HashMap();


//        String s = "{\"errcode\":\"0\",\"errmsg\":\"处理成功\",\"data\":{\"totalCount\":37,\"goodsList\":[{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/47\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=47\",\"goodsId\":47,\"title\":\"测试商品0001\",\"saleNum\":0,\"stockNum\":12,\"salePrice\":0.01},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/46\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=46\",\"goodsId\":46,\"title\":\"测试0101\",\"saleNum\":0,\"stockNum\":23,\"salePrice\":0.01},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/1d33fb5f8f3c41cd86192cbca6fa3ccd.png\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/45\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=45\",\"goodsId\":45,\"title\":\"测试现金券\",\"saleNum\":0,\"stockNum\":31,\"salePrice\":226.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/44\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=44\",\"goodsId\":44,\"title\":\"商品测是02\",\"saleNum\":0,\"stockNum\":23,\"salePrice\":34.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/43\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=43\",\"goodsId\":43,\"title\":\"商品测是02\",\"saleNum\":0,\"stockNum\":23,\"salePrice\":34.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/42\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=42\",\"goodsId\":42,\"title\":\"测试商品00003\",\"saleNum\":0,\"stockNum\":2333,\"salePrice\":123.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/41\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=41\",\"goodsId\":41,\"title\":\"测试商品001\",\"saleNum\":0,\"stockNum\":21,\"salePrice\":123.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/40\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=40\",\"goodsId\":40,\"title\":\"测试023\",\"saleNum\":0,\"stockNum\":23,\"salePrice\":12.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/38\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=38\",\"goodsId\":38,\"title\":\"测试商品01\",\"saleNum\":0,\"stockNum\":21,\"salePrice\":12321.00},{\"pid\":1918,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/b64911750d3c4ba695ab2b64b021808d.jpg\",\"h5Url\":\"http://1918.hotel.n.saas.weimobqa.com/saas/hotel/1918/mall/goods/detail/37\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=37\",\"goodsId\":37,\"title\":\"测试商品\",\"saleNum\":0,\"stockNum\":21,\"salePrice\":12.00}]}}";
//
//        JSONObject object = JSONObject.parseObject(s);
//        if (object.containsKey("errcode") && object.get("errcode").toString().equals("0")) {
//
//            JSONObject data = (JSONObject) object.get("data");
//            int totalCount = Integer.valueOf(data.get("totalCount")+"");
//
////            arr=obj.getJSONArray("resultData");
//
//            String s1 = JSONObject.toJSONString(data.getJSONArray("goodsList"));
//
//
//            List<BusinessGoods> goodsList = new ArrayList<>();
//            List<BusinessGoods> goodsList1 = new ArrayList<>();
//
//            goodsList = JSONObject.parseArray(s1,BusinessGoods.class);
//            goodsList1 = JSONObject.parseObject(s1, new TypeReference<List<BusinessGoods>>(){});
//
//            System.out.println(1);

//            String s = "{\"errcode\":\"0\",\"errmsg\":\"处理成功\",\"data\":[{\"pid\":2064,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/40b000422909493f8b4e4049b29170a4.jpg\",\"h5Url\":\"http://2064.hotel.n.saas.weimobqa.com/saas/hotel/2064/mall/goods/detail/40\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=40\",\"goodsId\":40,\"title\":\"预热砍价\",\"saleNum\":0,\"stockNum\":99,\"salePrice\":10.00},{\"pid\":2064,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/5ce938285ea6427a8f434c367c1f3616.jpeg\",\"h5Url\":\"http://2064.hotel.n.saas.weimobqa.com/saas/hotel/2064/mall/goods/detail/42\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=42\",\"goodsId\":42,\"title\":\"现金券1\",\"saleNum\":3,\"stockNum\":96,\"salePrice\":100.00},{\"pid\":2064,\"storeId\":null,\"coverUrl\":\"https://image-c-dev.weimobwmc.com/dev-saas-wxbiz/ed80a05eb18541b7bf06974ce8ae9375.jpeg\",\"h5Url\":\"http://2064.hotel.n.saas.weimobqa.com/saas/hotel/2064/mall/goods/detail/43\",\"miniUrl\":\"pages/store_detail/store_detail?goodsId=43\",\"goodsId\":43,\"title\":\"测试余额\",\"saleNum\":2,\"stockNum\":98,\"salePrice\":0.01}]}";
//
//        JSONObject object = JSONObject.parseObject(s);
//        if (object.containsKey("errcode") && object.get("errcode").toString().equals("0")) {
//            Object data =  object.get("data");
//            List<BusinessGoods> queryResponse = JSONObject.parseArray(data + "", BusinessGoods.class);
//        }

//
//        String s ="[{\"solutionType\":\"intelligenceHotel\",\"totalUrl\":\"/#/app/${pid}/${storeId}/statistics/live\",\"goodsListUrl\":\"http://172.21.246.231:81/hotel/pcaal/core/thirdparty/queryLiveGoods\",\"business.goodsBatchUrl\":\"http://172.21.246.231:81/hotel/pcaal/core/thirdparty/queryBatchLiveGoods\"},\n" +
//                "{\"solutionType\":\"smallStore\",\"totalUrl\":\"\"}]";
//
//
//        JSONArray infoArray = JSONObject.parseArray(s);
////        String solutionType = SolutionTypeEnum.get(type).getEnDesc();
//        for (Object obj : infoArray) {
//            System.out.println(obj);
//        }


    }





}
