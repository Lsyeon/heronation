package com.example.heronation;

/* 상품 정보를 보여주는 리사이클러뷰를 위해 상품 정보를 받아오기 위한 클래스 */
public class ShopItem {
    private String itemName;
    private String shopName;
    private Integer originalPrice;
    private Integer salePrice;
    
    public ShopItem(String itemName, String shopName, Integer originalPrice,Integer salePrice){
        this.itemName=itemName;
        this.shopName=shopName;
        this.originalPrice=originalPrice;
        this.salePrice=salePrice;
    }

    public ShopItem(String itemName, String shopName, Integer originalPrice){
        this.itemName=itemName;
        this.shopName=shopName;
        this.originalPrice=originalPrice;;
    }

    public String getItemName() {
        return itemName;
    }

    public String getShopName() {
        return shopName;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }
}
