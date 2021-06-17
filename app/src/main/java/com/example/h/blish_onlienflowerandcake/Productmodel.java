package com.example.h.blish_onlienflowerandcake;

public class Productmodel {

    String imageurl;
    String productname;
    String productprice;
    String productrealprice;
    String productDiscount;
    String alldescription;
    String specificationInthepacket;
    String specificaioncolor;
    String specificaionType;
    String specificaionWeight;
    String specificaionRibboncolor;
    String specificaionflowerqty;
    String moreinfoGenericname;
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Productmodel(String imageurl, String productname, String productprice, String productrealprice, String productDiscount, String alldescription, String specificationInthepacket, String specificaioncolor, String specificaionType, String specificaionWeight, String specificaionRibboncolor, String specificaionflowerqty, String moreinfoGenericname, String category) {
        this.imageurl = imageurl;
        this.productname = productname;
        this.productprice = productprice;
        this.productrealprice = productrealprice;
        this.productDiscount = productDiscount;
        this.alldescription = alldescription;
        this.specificationInthepacket = specificationInthepacket;
        this.specificaioncolor = specificaioncolor;
        this.specificaionType = specificaionType;
        this.specificaionWeight = specificaionWeight;
        this.specificaionRibboncolor = specificaionRibboncolor;
        this.specificaionflowerqty = specificaionflowerqty;
        this.moreinfoGenericname = moreinfoGenericname;
        this.category = category;
    }

    public Productmodel(String imageurl, String productname, String productprice, String productrealprice, String productDiscount, String alldescription, String specificationInthepacket, String specificaioncolor, String specificaionType, String specificaionWeight, String specificaionRibboncolor, String specificaionflowerqty, String moreinfoGenericname) {
        this.imageurl = imageurl;
        this.productname = productname;
        this.productprice = productprice;
        this.productrealprice = productrealprice;
        this.productDiscount = productDiscount;
        this.alldescription = alldescription;
        this.specificationInthepacket = specificationInthepacket;
        this.specificaioncolor = specificaioncolor;
        this.specificaionType = specificaionType;
        this.specificaionWeight = specificaionWeight;
        this.specificaionRibboncolor = specificaionRibboncolor;
        this.specificaionflowerqty = specificaionflowerqty;
        this.moreinfoGenericname = moreinfoGenericname;
    }

    public Productmodel() {
    }


    public String getSpecificationInthepacket() {
        return specificationInthepacket;
    }

    public void setSpecificationInthepacket(String specificationInthepacket) {
        this.specificationInthepacket = specificationInthepacket;
    }

    public String getAlldescription() {
        return alldescription;
    }

    public void setAlldescription(String alldescription) {
        this.alldescription = alldescription;
    }

    public String getSpecificaioncolor() {
        return specificaioncolor;
    }

    public void setSpecificaioncolor(String specificaioncolor) {
        this.specificaioncolor = specificaioncolor;
    }

    public String getSpecificaionType() {
        return specificaionType;
    }

    public void setSpecificaionType(String specificaionType) {
        this.specificaionType = specificaionType;
    }

    public String getSpecificaionWeight() {
        return specificaionWeight;
    }

    public void setSpecificaionWeight(String specificaionWeight) {
        this.specificaionWeight = specificaionWeight;
    }

    public String getSpecificaionRibboncolor() {
        return specificaionRibboncolor;
    }

    public void setSpecificaionRibboncolor(String specificaionRibboncolor) {
        this.specificaionRibboncolor = specificaionRibboncolor;
    }

    public String getSpecificaionflowerqty() {
        return specificaionflowerqty;
    }

    public void setSpecificaionflowerqty(String specificaionflowerqty) {
        this.specificaionflowerqty = specificaionflowerqty;
    }

    public String getMoreinfoGenericname() {
        return moreinfoGenericname;
    }

    public void setMoreinfoGenericname(String moreinfoGenericname) {
        this.moreinfoGenericname = moreinfoGenericname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductrealprice() {
        return productrealprice;
    }

    public void setProductrealprice(String productrealprice) {
        this.productrealprice = productrealprice;
    }
}
