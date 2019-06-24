package rfsmproduct.silly.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rfsmproduct.silly.R;
import rfsmproduct.silly.adapter.ProductAdapter;
import rfsmproduct.silly.bean.Product;
import rfsmproduct.silly.dao.ProductDao;
import rfsmproduct.silly.jxl.Jxl;

import static rfsmproduct.silly.utils.PermisionUtils.verifyStoragePermissions;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "sd";
    private ProductDao productDao = new ProductDao(MainActivity.this);


    private List<Product> products = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excel();
            }
        });


        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        productAdapter = new ProductAdapter(products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        recyclerView.setAdapter(productAdapter);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        find();



        final EditText findEt = findViewById(R.id.find_et);
        findViewById(R.id.find_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(findEt.getText().toString());
            }
        });

    }


    public void excel() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            String path = data.getDataString();//得到uri，后面就是将uri转化成file的过程。
            Log.e("acitcitry-path", path);
//            File file = new File(Uri.decode(path).replace("file://", ""));
            File file = new File(Uri.decode(path).replace("content://com.android.externalstorage.documents/document/primary:", ""));
//            Toast.makeText(MainActivity.this, file.toString(), Toast.LENGTH_SHORT).show();
            Log.e("acitcitry", file.toString() + "," + file.exists());
            List<Product> productFromSheet = new Jxl().getUserFromSheet(file);
            for (Product product : productFromSheet) {
                Log.e("acitcitry", product.toString());
                List<Product> products = productDao.QueryProductNum(product.getProductNum());
                if (products.isEmpty()) {
                    productDao.add(product);
                }
            }
        }
    }


    public void find() {
        try {
            List<Product> query = productDao.Query();
            for (Product product : query) {
                Log.d(TAG, "find: " + product);
            }
            products.clear();
            products.addAll(query);
            productAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void find(String productName) {

        if (productName.isEmpty()) {
            find();
            return;
        }

        try {
            List<Product> query = productDao.QueryProductName(productName);
            for (Product product : query) {
                Log.d(TAG, "find: " + product);
            }
            products.clear();
            products.addAll(query);
            productAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void delete() {
        try {
            productDao.detele();
            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    public void shan() {
        //            productDao.detele();
        List<Product> products = productDao.QueryProductNum("4454");
        Log.e("acitcitry", products.toString());
    }

    public void xiu() {
        Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
//        Product product = new Product();
//        product.setId(2);
//        product.setName("李三");
//        productDao.updates(users);
    }


}
