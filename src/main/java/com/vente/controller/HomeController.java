package com.vente.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vente.entities.User;
import com.vente.dto.ProductDto;
import com.vente.entities.Category;
import com.vente.entities.Product;
import com.vente.service.CategoryService;
import com.vente.service.ProductService;
import com.vente.service.UserService;
import  com.vente.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository repo ;
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/home")
	public String productsIndex(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "home";
	}
	
	@RequestMapping("/admin")
	public String adminHome() {
		return "AdminHome";
	}
	@RequestMapping("/admin/categories")
	public String Categories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "Categories";
	}
	@GetMapping("/admin/categories/add")
	public String addCategories(Model model) {
		model.addAttribute("category", new Category());
		return "AddCategories";
	}
	@PostMapping("/admin/categories/add")
	public String postCategories(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.removeCategory(id);
		
		return "redirect:/admin/categories";
		
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id , Model model) {
		Optional<Category> category = categoryService.getCategory(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "AddCategories";

		}else {
			return "404";
		}
	}
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "Products";
	}
	@GetMapping("/admin/products/add")
	public String productAddG(Model model) {
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "ProductsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String productAddP(@ModelAttribute("productDTO") ProductDto productDto,
			                  @RequestParam("productImage")MultipartFile file,
			                  @RequestParam("imgName") String imgName)throws IOException {
		Product product = new Product();
		product.setProduct_id(productDto.getId());
		product.setName(productDto.getName());
		product.setCategory(categoryService.getCategory(productDto.getCategoryId()).get());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID =file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID=imgName;
		}
		product.setImage(imageUUID);
		productService.addProduct(product);
		return "redirect:/admin/products";
		
	}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.removeProduct(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/update/{id}")
	public String updateProductG(@PathVariable long id, Model model) {
		Product product = productService.getProduct(id).get();
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getProduct_id());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setWeight(product.getWeight());
		productDto.setDescription(product.getDescription());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setImage(product.getImage());
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("productDTO", productDto);
		
		return "ProductsAdd";
	}
	@GetMapping("/admin/list_users")
	public String viewUsersList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers",listUsers);
		return"users";
	}

	@GetMapping("/admin/list_users/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		userService.DeleteUser(id);
		return "redirect:/admin/list_users";
	}
}
