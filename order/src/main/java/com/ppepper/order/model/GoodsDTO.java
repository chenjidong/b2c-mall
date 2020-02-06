package com.ppepper.order.model;

/**
 * Created with ChenJiDong
 */
public class GoodsDTO {
    private Long goodsId;
    private String title;
    private String pic;
    private String desc;
    private Float price;


    public GoodsDTO() {
    }

    public GoodsDTO(Long goodsId, String title, String pic, String desc, Float price) {
        this.goodsId = goodsId;
        this.title = title;
        this.pic = pic;
        this.desc = desc;
        this.price = price;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}
