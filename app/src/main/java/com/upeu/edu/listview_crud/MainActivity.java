package com.upeu.edu.listview_crud;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import dao.ProductoDaoImp;
import dto.Producto;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayAdapter<Producto> adapter1;
    CRUD crud=new CRUD();
    Dialog d;

    ProductoDaoImp dao = new ProductoDaoImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView) findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });
    }
    private void displayInputDialog(final int pos)
    {
        d=new Dialog(this);
        d.setTitle("LISTVIEW CRUD");
        d.setContentView(R.layout.input_dialog);

        final EditText nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        Button addBtn= (Button) d.findViewById(R.id.addBtn);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);
        final EditText amountEditTxt = (EditText) d.findViewById(R.id.amount);
        final EditText costEditTxt = (EditText) d.findViewById(R.id.cost);


        if(pos== -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
           // nameEditTxt.setText(crud.getNames().get(pos));
            nameEditTxt.setText(""+dao.getProductos().get(pos).getNombre());

            amountEditTxt.setText(""+dao.getProductos().get(pos).getCantidad());
            costEditTxt.setText(""+dao.getProductos().get(pos).getPrecio());
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditTxt.getText().toString();
                int amounts = Integer.parseInt(amountEditTxt.getText().toString());
                Double costs = Double.parseDouble(costEditTxt.getText().toString());
                //VALIDATE
                if(name.length()>0 && name != null && amounts != 0 && costs != 0 )
                {
                    //save
                    Producto p =  new Producto(1,name,costs , amounts);

                    crud.save(name);
                    dao.create(p);
                    nameEditTxt.setText("");
                    amountEditTxt.setText("");
                    costEditTxt.setText("");
                    //adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,crud.getNames());

                    adapter1 =  new ProductAdapter(MainActivity.this, dao.getProductos());

                    lv.setAdapter(adapter1);

                }else
                {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String newName=nameEditTxt.getText().toString();
                int newAmounts = Integer.parseInt(amountEditTxt.getText()+"");
                Double newCosts = Double.parseDouble(costEditTxt.getText()+"");
                //VALIDATE
                if(newName.length()>0 && newName != null && newAmounts != 0 && newCosts != 0 )
                {
                    //save
                    Producto p1 =  new Producto(1,newName, newCosts,newAmounts);
                    if(dao.update(pos,p1))
                    {
                        nameEditTxt.setText(newName);
                        amountEditTxt.setText(""+newAmounts);
                        costEditTxt.setText(""+newCosts);
                        //adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,crud.getNames());
                        adapter1 =  new ProductAdapter(MainActivity.this, dao.getProductos());

                        lv.setAdapter(adapter1);

                    }

                }else
                {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                //crud.delete(pos)
                if(dao.delete(pos) == 1 )
                {
                    nameEditTxt.setText("");
                    amountEditTxt.setText("");
                    costEditTxt.setText("");
                    //adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,crud.getNames());
                    adapter1 =  new ProductAdapter(MainActivity.this, dao.getProductos());

                    lv.setAdapter(adapter1);
                }
            }
        });

        d.show();
    }
}
