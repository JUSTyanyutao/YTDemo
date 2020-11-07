package com.just.ytdemo.jsonTest;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author yutao.yan
 * @date 2019/1/22 17:08
 */
public class JSONUtil {




    public static List<String> list=new LinkedList<String>();

//    /**
//     * 递归读取所有的key，某些限制可以自己除去，核心部分
//     * @param jsonObject
//     */
//    public static void getAllKey(JSONObject jsonObject){
//        Iterator<String> keys=jsonObject.values();
//        while(keys.hasNext()){
//            String key=keys.next();
//            if(isJsonObject(jsonObject.get(key).toString())){
//                if(!key.equals("properties") && !isArrayOrObject(jsonObject.get(key).toString())) {
//                    System.out.println(key);
//                }
//                JSONObject innerObject=JSONObject.fromObject(jsonObject.get(key));
//                getAllKey(innerObject);
//            }
//        }
//    }

//    /**
//     * 从未知的JsonArray中获取LinkedList
//     * @return
//     */
//    public static LinkedList<LinkedList<String>> getLinkedListFromJsonArray(String jsonArrayStr){
//
//        LinkedList<LinkedList<String>> linkedList=null;
//        if(jsonArrayStr!=null && jsonArrayStr.length()>0){
//            JSONArray jsonArray=JSONArray.fromObject(jsonArrayStr);
//            linkedList=new LinkedList<LinkedList<String>>();
//            for(int i=0;i<jsonArray.size();i++){
//                JSONArray array=JSONArray.fromObject(jsonArray.get(i));
//                LinkedList<String> internalList=new LinkedList<String>();
//                for(int j=0;j<array.size();j++){
//                    internalList.add(array.get(j).toString());
//                }
//                linkedList.add(internalList);
//            }
//        }
//        return linkedList;
//    }
//
//    /**
//     * 判断某个Json字符串是否为一个标准的Json字符串
//     * @param jsonString
//     * @return
//     */
//    public static Boolean isJsonObject(String jsonString){
//        try{
//            JSONObject.fromObject(jsonString);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    /**
//     * 判断某个Json字符串是否为一个Json数组
//     * @param jsonObject
//     * @return
//     */
//    public static Boolean isArrayOrObject(String jsonObject){
//        String type=JSONObject.fromObject(jsonObject).get("type").toString();
//        if(type.equals("object") || type.equals("array")){
//            return true;
//        }else{
//            return false;
//        }
//    }

/*
给getAllKey使用的数据
{"title": "新增的任务的表单信息", "type": "object", "properties": {"finishTime": {"type": "number", "title": "任务结束时间例如：1450708950086"}, "contactEmail": {"type": "string", "title": "联系人邮箱"}, "sexType": {"type": "string", "title": "招聘性别要求,FEMALE:女，MALE：男，UNKWON：男女不限"}, "enrollEndTime": {"type": "number", "title": "报名截止时间例如：1450708950086"}, "description": {"type": "string", "title": "备注"}, "weekday": {"type": "string", "title": "工作日期"}, "title": {"type": "string", "title": "任务标题"}, "startTime": {"type": "number", "title": "任务开始时间,例如：1450708950086"}, "contactTelephone": {"type": "string", "title": "联系人电话"}, "timeDescription": {"type": "string", "title": "工作时间描述"}, "paymentType": {"type": "string", "title": "支付类型 0：线上支付，1：线下支付"}, "addressList": {"items": {"properties": {"latitude": {"type": "string", "title": "纬度"}, "cityName": {"type": "string", "title": "城市名称"}, "areaName": {"type": "string", "title": "区域名称"}, "longitude": {"type": "string", "title": "经度"}, "address": {"type": "string", "title": "t工作地点"}}, "type": "object", "description": "任务地址"}, "type": "array", "title": "地址列表"}, "balanceUnit": {"type": "string", "title": "兼职金额单位；例如：元/小时，元/天等"}, "contactName": {"type": "string", "title": "联系人姓名"}, "content": {"type": "string", "title": "工作内容"}, "balance": {"type": "number", "title": "兼职金额"}, "headcount": {"type": "integer", "title": "招聘人数"}, "typeDesc": {"type": "string", "title": "任务类型描述: 只有在任务类型为其他时有效"}, "type": {"type": "string", "title": "任务类型；例如：传单派发，服务员等"}, "balanceType": {"type": "integer", "title": "结算类型；例如：完工结，日结等"}}}
{"type": "object", "properties": {"arg0": {"type": "string", "description": "", "title": ""}, "arg1": {"title": "", "type": "integer", "description": "", "format": "int32"}, "arg2": {"title": "", "type": "integer", "description": "", "format": "int64"}}}
{"type": "object", "properties": {"arg0": {"type": "string", "description": "", "title": ""}, "arg1": {"title": "", "type": "integer", "description": "", "format": "int32"}, "arg2": {"title": "", "type": "integer", "description": "", "format": "int64"}}}
{"items": {"type": "string"}, "type": "array"}
{"type": "object", "properties": {}}
*/
//    /**
//     * 核心部分
//     * @param jsonObject
//     */
//    public static void getAllKey(JSONObject jsonObject){
//        Iterator<String> keys=jsonObject.keys();
//        while(keys.hasNext()){
//            String key=keys.next();
//            if(isJsonObject(jsonObject.get(key).toString())){
//                if(!key.equals("properties") && !isArrayOrObject(jsonObject.get(key).toString())) {
//                    System.out.println(key);
//                }
//                JSONObject innerObject=JSONObject.fromObject(jsonObject.get(key));
//                getAllKey(innerObject);
//            }
//        }
//    }
//
//    /**
//     * 从未知的JsonArray中获取LinkedList
//     * @return
//     */
//    public static LinkedList<LinkedList<String>> getLinkedListFromJsonArray(String jsonArrayStr){
//
//        LinkedList<LinkedList<String>> linkedList=null;
//        if(jsonArrayStr!=null && jsonArrayStr.length()>0){
//            JSONArray jsonArray=JSONArray.fromObject(jsonArrayStr);
//            linkedList=new LinkedList<LinkedList<String>>();
//            for(int i=0;i<jsonArray.size();i++){
//                JSONArray array=JSONArray.fromObject(jsonArray.get(i));
//                LinkedList<String> internalList=new LinkedList<String>();
//                for(int j=0;j<array.size();j++){
//                    internalList.add(array.get(j).toString());
//                }
//                linkedList.add(internalList);
//            }
//        }
//        return linkedList;
//    }
//
//    /**
//     * 判断某个Json字符串是否为一个标准的Json字符串
//     * @param jsonString
//     * @return
//     */
//    public static Boolean isJsonObject(String jsonString){
//        try{
//            JSONObject.fromObject(jsonString);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    /**
//     * 判断某个Json字符串是否为一个Json数组
//     * @param jsonObject
//     * @return
//     */
//    public static Boolean isArrayOrObject(String jsonObject){
//        String type=JSONObject.fromObject(jsonObject).get("type").toString();
//        if(type.equals("object") || type.equals("array")){
//            return true;
//        }else{
//            return false;
//        }
//    }


    public static void main(String[] args) {

        String str = " {\n" +
                "        \"3914898cba827c9e914b45388a71e2354ea8fe98572963ecd4156c188152d4c3\": \"73964151\",\n" +
                "        \"3914898cba827c9e914b45388a71e2353f0a18c1dd489244b8e6590441c6e0cc\": \"73904328\",\n" +
                "        \"3914898cba827c9e914b45388a71e235106800172\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235636518e2baf8fa3ac5256fbee4958a55\": \"73252129\",\n" +
                "        \"3914898cba827c9e914b45388a71e2351b9f7de236c4df5018b487dc6661c0aa\": \"62533573\",\n" +
                "        \"3914898cba827c9e914b45388a71e2357edc1b9a41a9f5d2231369a8a8418763\": \"64082495\",\n" +
                "        \"3914898cba827c9e914b45388a71e23586bc82800ece88a68c62a0000084eda2\": \"45679786\",\n" +
                "        \"f3363a0ef0d86ec3b87dd9cd7e673dca22f094e425347538b9cd462d4555b719\": \"74734346\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105341003\": null,\n" +
                "        \"ad343442fbf3dfbc27d972e057f6c302b5075a161d212297ad75b470c0b0e5a3\": \"62491241\",\n" +
                "        \"3914898cba827c9e914b45388a71e2357b328b27fd6a2152deadef3a3bf97095\": \"64195223\",\n" +
                "        \"3679a1a2a41c4f578459adce2dce0d4c7d96d5da017a6e56ab714e2ba9863da5\": \"62564719\",\n" +
                "        \"3914898cba827c9e914b45388a71e23526d0545fd8637b22f7f2a6449628ed80\": \"64104060\",\n" +
                "        \"3914898cba827c9e914b45388a71e235106806057\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235626eaf804f851efb617770d82f0b8c3d\": \"62533442\",\n" +
                "        \"3914898cba827c9e914b45388a71e235d47b25ab913758d4153d131fc2ed48dc\": \"64935165\",\n" +
                "        \"3914898cba827c9e914b45388a71e235e61240a8b00d9be92e9cca60cceed759\": \"48678246\",\n" +
                "        \"3914898cba827c9e914b45388a71e2355ffffd18da2ac18fef04d6e96e47fa47\": \"62528351\",\n" +
                "        \"3914898cba827c9e914b45388a71e235fb7c8138aab4f2d6d096099fd3fc5671\": \"62528046\",\n" +
                "        \"3914898cba827c9e914b45388a71e235228732a1701e17dc03e2d16748ce94bb\": \"62528678\",\n" +
                "        \"3914898cba827c9e914b45388a71e235613961a913b736199d6131ae72200363\": \"62533178\",\n" +
                "        \"3914898cba827c9e914b45388a71e235103527143\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235c2ee7a1bf4d67934f2b028b7147d5b69\": \"73784723\",\n" +
                "        \"3914898cba827c9e914b45388a71e235fc43cf3e4a107cfe3709ba4819043348\": \"73255174\",\n" +
                "        \"3914898cba827c9e914b45388a71e235106586796\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235e1d8e7bb98f092aecb614b9c2ccf1e40\": \"73794792\",\n" +
                "        \"3914898cba827c9e914b45388a71e235bb363cb28b9566f897c398306b0d8aa3\": \"62633806\",\n" +
                "        \"3914898cba827c9e914b45388a71e23567e978352c4901ca37d2000d48d2acb9\": \"71796817\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105322807\": \"52247377\",\n" +
                "        \"3914898cba827c9e914b45388a71e2357382514e8b4f7387956b46cb85adae42\": \"71253468\",\n" +
                "        \"3914898cba827c9e914b45388a71e23585655cd412de435ef4684a368f28c80e\": \"77091929\",\n" +
                "        \"3914898cba827c9e914b45388a71e2350ee58d00934690caa2b95eab698c693d\": \"62528351\",\n" +
                "        \"3914898cba827c9e914b45388a71e235106827951\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235c9a7e6de3c94e8b9e90aa6c84d0cc646\": \"64122479\",\n" +
                "        \"3914898cba827c9e914b45388a71e2351531ebfba3683797fd50f86580506ea1\": \"63650261\",\n" +
                "        \"3914898cba827c9e914b45388a71e23541c5a39f60457d2ee484c138f79aa860\": \"59527516\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105011168\": \"52188926\",\n" +
                "        \"3679a1a2a41c4f578459adce2dce0d4c7494d275f9851932d557dc294812e45b\": \"62568554\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105730961\": null,\n" +
                "        \"42f927c5995d86584f7f98151245b208d0434d09e9145bfe7bbd3ce07672b1f8\": \"62622988\",\n" +
                "        \"3914898cba827c9e914b45388a71e235e4a03895c3e8c0040553fc13db19efcf\": \"62485144\",\n" +
                "        \"3914898cba827c9e914b45388a71e2351b92e2c811570ee8576504d94f296ece\": \"62528280\",\n" +
                "        \"3914898cba827c9e914b45388a71e235a5a28d1f51d2bba5ae0550acdb58a643\": \"62634825\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105445562\": \"52569027\",\n" +
                "        \"3914898cba827c9e914b45388a71e235db2577cdd6335e924b1e5ffc69ab58ac\": \"75912643\",\n" +
                "        \"3914898cba827c9e914b45388a71e235e96bbb9f60fb1a98bf8cfc0812757c3d\": \"73208594\",\n" +
                "        \"3914898cba827c9e914b45388a71e235939738742cbba15c9f386382a62627b1\": \"62533195\",\n" +
                "        \"3914898cba827c9e914b45388a71e235e07e1c22e0a86bb90c6fc549bbfe25a3\": \"68481106\",\n" +
                "        \"1a2d04b993ffca80b8e70c62224948892a51098440225a873e4ebf9da4fb649c\": \"78484385\",\n" +
                "        \"3914898cba827c9e914b45388a71e235ededcd21819e758bab402920ec04aa5f\": \"62634381\",\n" +
                "        \"f3363a0ef0d86ec3b87dd9cd7e673dca36a4bc8997ef53754b7eb1b6d7e15527\": \"74734346\",\n" +
                "        \"3914898cba827c9e914b45388a71e235dc3511bf2071caeccd7d4c83f4048f40\": \"62633421\",\n" +
                "        \"1a2d04b993ffca80b8e70c6222494889142fb140e5ea47fd3c4c430a40a9f672\": \"62528307\",\n" +
                "        \"3914898cba827c9e914b45388a71e235e51c832879487b96f50635f7319d5955\": \"63959699\",\n" +
                "        \"f3363a0ef0d86ec3b87dd9cd7e673dca7fb24be7e01eb4205b4b019ee37e820d\": \"74734346\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105954388\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235ac546130bc6d34b684833e0cb429e662\": \"62528455\",\n" +
                "        \"3914898cba827c9e914b45388a71e235a43311cc0df7fdaabb8c539b23bc3684\": \"63963375\",\n" +
                "        \"3914898cba827c9e914b45388a71e235104719173\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e2350feb26e55745ff4709fec78c1c55100f\": \"50609598\",\n" +
                "        \"3914898cba827c9e914b45388a71e235b2aae3352ca1c1c3b4ee81861a903ac9\": \"45679786\",\n" +
                "        \"3914898cba827c9e914b45388a71e235598f9256bb1a68786a644b263bb209f2\": \"62534611\",\n" +
                "        \"3914898cba827c9e914b45388a71e235106804897\": null,\n" +
                "        \"e57ad214b75ab3bad1c599797fcc36d3102741932\": \"45681395\",\n" +
                "        \"3914898cba827c9e914b45388a71e23540ce4f9cabbe84078b51cc69cffb8a3d\": \"64082531\",\n" +
                "        \"f3363a0ef0d86ec3b87dd9cd7e673dcac8cf7e8d596e8edb2ba2cd5a9a570632\": \"80111294\",\n" +
                "        \"3914898cba827c9e914b45388a71e23566f88636e248eb383715a6c52206f819\": \"68627707\",\n" +
                "        \"3914898cba827c9e914b45388a71e2351fa3c28f691f24216672f6b6efe54a3d\": \"61841645\",\n" +
                "        \"3914898cba827c9e914b45388a71e2351b00eccda882faabf1e951fcf1779502\": \"77312060\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105259225\": \"52249073\",\n" +
                "        \"3914898cba827c9e914b45388a71e23557d155da748d99157f1e944d0f285343\": \"62485549\",\n" +
                "        \"3914898cba827c9e914b45388a71e2358c471ff787fe2ab4aa086df8268be8ae\": \"75446990\",\n" +
                "        \"3914898cba827c9e914b45388a71e235268ed9daeaf4358af6929dc0a1dcd896\": \"73912881\",\n" +
                "        \"1a2d04b993ffca80b8e70c6222494889682ef3a022263b1274ea3e47b945a00f\": \"62534611\",\n" +
                "        \"3914898cba827c9e914b45388a71e2350308e3a455204278fed5be1ada207bcb\": \"64080342\",\n" +
                "        \"3914898cba827c9e914b45388a71e235637f6e438f03d0a232f0051852a9dfdd\": \"73502600\",\n" +
                "        \"3914898cba827c9e914b45388a71e235104550155\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235a699eed1c6c4e8cfb698ea841dd3c8bb\": \"73904284\",\n" +
                "        \"3914898cba827c9e914b45388a71e2359ca4f1778ee2172b28baf1039dd19c83\": \"64935165\",\n" +
                "        \"3914898cba827c9e914b45388a71e235106348558\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235a90ec62d67ee57c67775512893e381e4\": \"62485429\",\n" +
                "        \"3914898cba827c9e914b45388a71e2350b57a597acf2bc32297e2347bfcad443\": \"78317722\",\n" +
                "        \"3914898cba827c9e914b45388a71e235104637542\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235105404477\": null,\n" +
                "        \"1a2d04b993ffca80b8e70c62224948891accda8ec6753659992144456d88633a\": \"78076725\",\n" +
                "        \"3914898cba827c9e914b45388a71e2359e93f242c42bd1cb64d891a8f1f4c519\": \"75587453\",\n" +
                "        \"3914898cba827c9e914b45388a71e2352c0b109a11bc28f61b88f12f8b036f3d\": \"51038713\",\n" +
                "        \"3914898cba827c9e914b45388a71e235ff9823dd9bc9c38c76570eee9b684c0f\": \"75667690\",\n" +
                "        \"3914898cba827c9e914b45388a71e2358434580d61026b3aa9524d1b5c84efeb\": \"73891206\",\n" +
                "        \"3914898cba827c9e914b45388a71e2351a78f325b496529f79b68b72d2e732d7\": \"71472621\",\n" +
                "        \"3914898cba827c9e914b45388a71e2354b9845842a951a95b286fe64ded0d3b2\": \"71365805\",\n" +
                "        \"3914898cba827c9e914b45388a71e235f0a93e31a453d1291dadf271ea9d46d0\": \"62533634\",\n" +
                "        \"3914898cba827c9e914b45388a71e235a2d11f6d66958b7ce86be2830dde6274\": \"62528307\",\n" +
                "        \"3679a1a2a41c4f578459adce2dce0d4cc2ccb530c395238018c70452817a7c51\": \"62634171\",\n" +
                "        \"3914898cba827c9e914b45388a71e2354e6fb4189139adfdc79cd722f3533cfa\": \"71280686\",\n" +
                "        \"3914898cba827c9e914b45388a71e2359c420af2bd353337aefb3a3cfbaa1d93\": \"74070630\",\n" +
                "        \"3914898cba827c9e914b45388a71e23527c7a3474be5be559c6d3f7cd8b6bdcd\": \"69789628\",\n" +
                "        \"e57ad214b75ab3bad1c599797fcc36d3103053190\": \"46491708\",\n" +
                "        \"3914898cba827c9e914b45388a71e235105599506\": \"52829542\",\n" +
                "        \"3914898cba827c9e914b45388a71e23548d064f36365fce20172c806105eda3f\": \"48380138\",\n" +
                "        \"3914898cba827c9e914b45388a71e235b1ee4054102b09a88457e52ecf820191\": \"48120512\",\n" +
                "        \"3914898cba827c9e914b45388a71e2358fede066f6e06af10d5657b09a791546\": \"62528307\",\n" +
                "        \"3914898cba827c9e914b45388a71e235103629108\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235a53a915965d80ba9fbcc39f825305030\": \"62537856\",\n" +
                "        \"3914898cba827c9e914b45388a71e235e5f73b86db5b76db12d0cc445442c649\": \"69789628\",\n" +
                "        \"3914898cba827c9e914b45388a71e235ee38dc3bc6dbc85a5b437857f6d51652\": \"70437968\",\n" +
                "        \"3914898cba827c9e914b45388a71e235104184778\": null,\n" +
                "        \"3914898cba827c9e914b45388a71e235eb81c2be536c205ced91d16b5e2d9d29\": \"73276315\",\n" +
                "        \"3679a1a2a41c4f578459adce2dce0d4c2a5a6b6d4ed16264aa1903e5a7fb9bdd\": \"67934762\"\n" +
                "    }";




        JSONObject object = JSONObject.parseObject(str);



        List<String> list =  new ArrayList<>();
        for (Object obj : JSONObject.parseObject(str).values()) {
            if (obj != null) {
                list.add(obj.toString());
            }
        }
        System.out.println(list.size());










    }








}
