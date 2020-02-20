package com.design.建造者;

/**
 * @author: zhoujian
 * @Date: 2019/10/12 11:46
 * @Company: youanmi.
 * @Desc:热干面
 */
public class HotNoodles {

    private boolean jiaChongHua;
    private boolean jiaXiangCai;
    private boolean jiaLajiao;
    private boolean jiaSuanCai;

    public HotNoodles(boolean jiaChongHua, boolean jiaXiangCai, boolean jiaLajiao, boolean jiaSuanCai) {
        this.jiaChongHua = jiaChongHua;
        this.jiaXiangCai = jiaXiangCai;
        this.jiaLajiao = jiaLajiao;
        this.jiaSuanCai = jiaSuanCai;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("老板热干面");

        if (this.jiaChongHua) {
            builder.append("加葱花.");
        }else {
            builder.append("不加葱花.");
        }

        if (this.jiaXiangCai) {
            builder.append("加香菜.");
        }else {
            builder.append("不加香菜.");
        }

        if (this.jiaLajiao) {
            builder.append("加辣椒.");
        }else {
            builder.append("不加葱花.");
        }

        if (this.jiaChongHua) {
            builder.append("加酸菜.");
        }else {
            builder.append("不加酸菜.");
        }

        return builder.toString();

    }

    public static void main(String[] args) {
        // 传统方式 构建参数多 ,每个都要赋值，麻烦
        HotNoodles hotNoodles = new HotNoodles(true,false,true,true);
        System.out.println(hotNoodles.toString());
    }

}
