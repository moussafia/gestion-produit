package ma.enset.gestionproduit.controller;

import jakarta.validation.Valid;
import ma.enset.gestionproduit.entities.Product;
import ma.enset.gestionproduit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Product>  products = productRepository.findAll();
        model.addAttribute("productList", products);
        return "products";
    }

    @GetMapping("")
    public String home() {
        return "redirect:/index";
    }
    @GetMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/admin/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/newProduct";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "newProduct";
        productRepository.save(product);
        return "redirect:/index";
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
}
