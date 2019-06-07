package rfsmproduct.silly.jxl;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.*;
import rfsmproduct.silly.bean.Product;

public class Jxl {

    // 读取用户表
    public List<Product> getUserFromSheet(File file) {
        //User是一个封装的bean，里面是表格里的每个参数，getset一下
        List<Product> products = new ArrayList<>();

        Workbook book;
        Sheet sheet;
        Cell U_ID, U_NAME, U_NICK_NAME, U_AGE;

        try {
            //hello.xls为要读取的excel文件名
            book = Workbook.getWorkbook(file);

            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(0);
            int Rows = sheet.getRows();//得到当前工作表的行数
            int Cols = sheet.getColumns(); //得到当前工作表的列数
            for (int i = 0; i < Rows; i++) {  // 注意：这里是按列读取的！！！
                Product product = new Product();
                for (int j = 0; j < Cols; j++) {
                    String content = sheet.getCell(j, i).getContents();//结果是String类型的，根据具体需求进行类型转换
                    if (content.equals("商 品 信 息")) {
                        break;
                    }
                    if (content.equals("路径:\\食品类\\")) {
                        break;
                    }

                    if (content.equals("行号")) {
                        break;
                    }
                    if (j == 2) {
                        product.setProductNum(content);
                    }
                    if (j == 3) {
                        product.setProductName(content);
                    }
                    if (j == 4) {
                        product.setFormat(content);
                    }
                    if (j == 5) {
                        product.setModel(content);
                    }
                    if (j == 7) {
                        product.setBarCode(content);
                    }
                    if (j == 8) {
                        product.setUnit(content);
                    }
                    if (j == 10) {
                        product.setPrice(content);
                    }
                }
                if (product.getProductNum() == null || product.getProductNum().equals("") || product.getProductNum().equals("null")) {
                    continue;
                }
                products.add(product);
            }

            book.close();
        } catch (Exception e) {
            Log.e("acitcitry-excepton", e.getMessage());
        }

        return products;
    }
}
