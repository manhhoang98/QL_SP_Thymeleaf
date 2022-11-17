package service;

import model.Product;
import model.dao.ProductsDao;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> products = new ArrayList<>();

    public List<Product> getAll(){
        return ProductsDao.getAll();
    }

    public void add(Product product){
        ProductsDao.saveProduct(product);
    }
    public void edit(int id, Product product){
            products.set(id,product);
    }

    public int findIndexById(int id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public void delete(int id){
        int index = findIndexById(id);
        if (index != -1){
            products.remove(index);
        }
    }

//    public  Product getProduct(int id){
//        return products.get(findIndexById(id));
//    }

}
