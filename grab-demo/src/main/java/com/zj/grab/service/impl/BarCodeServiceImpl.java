package com.zj.grab.service.impl;

import com.zj.grab.dto.CrawlerProductDto;
import com.zj.grab.service.GrabGoodService;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * @author: zhoujian
 * @Date: 2019/10/8 10:13
 * @Company: youanmi.
 * @Desc:通过条形码查询商品信息/中国商品信息服务平台接口
 */
public class BarCodeServiceImpl extends GrabGoodService {


    @Override
    public CrawlerProductDto grab(String barCode) throws IOException{
        try {
            Connection con1 = HttpConnection.connect("http://search.anccnet.com/writeSession.aspx?responseResult=check_ok");
            con1.header("Referrer", "http://www.gds.org.cn/");
            con1.header("Host","search.anccnet.com");
            con1.header("Connection","Keep-Alive");
            con1.get();
            Connection.Response response =  con1.response();
            String ASP_SESSION = "ASP.NET_SessionId";
            String sessionId = response.cookie(ASP_SESSION);
            Connection con2 =  HttpConnection.connect("http://search.anccnet.com/searchResult2.aspx?keyword="+barCode);
            con2.header("Referer", "http://www.gds.org.cn/");
            con2.header("Host", "search.anccnet.com");
            con2.cookie(ASP_SESSION,sessionId);
            Document document2 = con2.get();
            Element elInfo = document2.getElementById("results");
            //商品信息
            List<Element> ddInfo =  elInfo.getElementsByTag("dd");
            if ( ddInfo !=null && ddInfo.size()> 6 ){
                 //品牌名
                String  originalOrgName = ddInfo.get(0).getElementsByTag("dd").get(0).html();
                String name = ddInfo.get(6).getElementsByTag("dd").get(0).html();
                String standard = ddInfo.get(7).getElementsByTag("dd").get(0).html();
                for (int i = 0; i < ddInfo.size(); i++) {
                    System.out.println(ddInfo.get(i).toString());
                }
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
