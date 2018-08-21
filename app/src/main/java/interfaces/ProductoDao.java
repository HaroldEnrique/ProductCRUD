package interfaces;

import java.util.ArrayList;

import dto.Producto;

public interface ProductoDao {

    public int create(Producto p);
    public int delete(int pos);
    public Producto search(int cod);
    public Boolean update(int pos, Producto p);
    public ArrayList<Producto> getProductos();

}
