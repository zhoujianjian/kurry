package com.zj.grab;

import com.zj.grab.constants.GrabGoodEnum;
import com.zj.grab.service.GrabGoodService;

/**
 * @author: zhoujian
 * @Datee: 2019/10/8 11:55
 * @Company: youanmi.
 * @Desc:
 */
public class TestApplication {


    public static void main(String[] args) {
//        GrabGoodService barCodeService = GrabGoodEnum.getGrabGoodService(GrabGoodEnum.BAR_CODE);
//        barCodeService.grabGoods("6922266454295");


        GrabGoodService tbService = GrabGoodEnum.getGrabGoodService(GrabGoodEnum.TB);
        String goodId = tbService.parseGetGoodId("【【预售赠Beats耳机】Apple/苹果#iPhone 11 4G全网通苹果手机新款iphone11手机电信官方旗舰店国行128】，椱ァ製这段描述₴vAobYN8qdQZ₴后到◇綯℡寳");
        System.out.println(goodId);


        GrabGoodService jdService = GrabGoodEnum.getGrabGoodService(GrabGoodEnum.JD);
        String goodIdJD = jdService.parseGetGoodId("https://item.m.jd.com/product/51562947890.html?wxa_abtest=o&utm_source=iosapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL&ad_od=share");
        System.out.println(goodIdJD);


        GrabGoodService pddService = GrabGoodEnum.getGrabGoodService(GrabGoodEnum.PDD);
        String goodIdPDD = pddService.parseGetGoodId(" https://mobile.yangkeduo.com/goods1.html?_wvx=10&refer_share_uid=&page_from=35&refer_share_id=ju8ir4xxCJA7VE7ptwwh2XIabKQufsop&_wv=41729&refer_share_channel=copy_link&share_uid=&goods_id=10198796952");
        System.out.println(goodIdPDD);

    }

}
