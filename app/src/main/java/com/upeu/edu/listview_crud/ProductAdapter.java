package com.upeu.edu.listview_crud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dto.Producto;

public class ProductAdapter extends ArrayAdapter<Producto> {

    private Context pcontext;
    private List<Producto> productList =  new ArrayList<>();

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        pcontext = context;
        productList = objects;
    }


    public ProductAdapter(@NonNull Context context, @NonNull ArrayList<Producto> list) {
        super(context, 0 , list);
        pcontext = context;
        productList = list;
    }





    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(pcontext).inflate(R.layout.product_item,parent,false);

        Producto currentproduct = productList.get(position);
        TextView name =  (TextView) listItem.findViewById(R.id.tvname);
        name.setText(currentproduct.getNombre() + "  --  " +currentproduct.getCantidad());

        TextView code = (TextView) listItem.findViewById(R.id.tvcode);
        code.setText("" +currentproduct.getPrecio());


        return listItem;
    }

}
