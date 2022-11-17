package controller;

import model.Product;
import model.dao.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("products",productService.getAll());
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        for (Product p: ProductsDao.getAll()) {
            if (p.getId() == id){
                modelAndView.addObject("manh",p);
            }
        }
        return modelAndView;
    }

    @PostMapping ("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Product product, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        ProductsDao.editProduct(productService.findIndexById(id),product);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createProduct(){
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product){
        productService.add(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }

        @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ProductsDao.deleteProduct(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }
}
