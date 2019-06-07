package rfsmproduct.silly.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user_info")//创建表名
public class Product {
    public Product() {
    }

    @DatabaseField(generatedId = true)// 自增id
    private int id;
    @DatabaseField(columnName = "product_num")
    private String productNum;// 商品编码
    @DatabaseField(columnName = "product_name")
    private String productName;// 商品名称
    @DatabaseField(columnName = "format")
    private String format;// 规格
    @DatabaseField(columnName = "model")
    private String model;// 型号
    @DatabaseField(columnName = "bar_code")
    private String barCode;// 条码
    @DatabaseField(columnName = "unit")
    private String unit;// 单位
    @DatabaseField(columnName = "price")
    private String price;// 价格

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", format='" + format + '\'' +
                ", model='" + model + '\'' +
                ", barCode='" + barCode + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}