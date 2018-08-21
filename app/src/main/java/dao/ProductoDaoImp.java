package dao;

import java.util.ArrayList;

import dto.Producto;
import interfaces.ProductoDao;

public class ProductoDaoImp implements ProductoDao{


    private ArrayList<Producto> lista;


    public ProductoDaoImp(){
        lista = new ArrayList<>();
    }

    @Override
    public int create(Producto p) {
        try {
            lista.add(p);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int delete(int pos) {

        try {
            lista.remove(pos);
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    @Override
    public Producto search(int cod) {
        return lista.get(cod);

    }

    @Override
    public Boolean update(int pos, Producto p) {
        try {
            lista.remove(pos);
            lista.add(p);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Producto> getProductos() {
        return lista;
    }
}
