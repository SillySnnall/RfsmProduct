package rfsmproduct.silly.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import rfsmproduct.silly.bean.Product;
import rfsmproduct.silly.helper.Basesql;

public class ProductDao {

    private final Basesql instance;
    private Dao getdao = null;


    public ProductDao(Context context) {
        this.instance = Basesql.getInstance(context);
        try {
            this.getdao = instance.getdao(Product.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //添加操作
    public void add(Product product) {
        try {
            getdao.create(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除
    public void detele() throws SQLException {
        getdao.delete(Query());
    }

    //查询
    public List<Product> Query() {
        List list = null;
        try {
            list = getdao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    ;

    // productNum查询
    public List<Product> QueryProductNum(String productNum) {
        List list = null;
        try {
            list = getdao.queryBuilder().where().eq("product_num", productNum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // productNum查询
    public List<Product> QueryProductName(String name) {
        List list = null;
        try {
            list = getdao.queryBuilder().where().like("product_name", "%" + name + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // barCode查询
    public List<Product> QueryBarCode(String barCode) {
        List list = null;
        try {
            list = getdao.queryBuilder().where().like("bar_code", "%" + barCode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updates(Product product) {
        try {

            getdao.createOrUpdate(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 构建BUilder 删除条件比较复杂
     */
//    public void shanchu(Product user){
//        try {
//            UpdateBuilder updateBuilder = getdao.updateBuilder();
//            //updateBuilder.where()
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
