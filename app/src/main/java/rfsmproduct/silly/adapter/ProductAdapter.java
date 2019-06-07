package rfsmproduct.silly.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import rfsmproduct.silly.R;
import rfsmproduct.silly.bean.Product;

public class ProductAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    public ProductAdapter(List data) {
        super(R.layout.adapter_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        helper.setText(R.id.name, item.getProductName())
                .setText(R.id.price,item.getPrice())
                .setText(R.id.format,item.getFormat())
                .setText(R.id.model,item.getModel());
    }
}
