package com.example.springbootcrudapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import com.example.springbootcrudapp.model.Order;
import com.example.springbootcrudapp.model.OrderDTO;
import com.example.springbootcrudapp.model.Product;
import com.example.springbootcrudapp.model.User;
import com.example.springbootcrudapp.service.OrderService;
import com.example.springbootcrudapp.service.ProductService;
import com.example.springbootcrudapp.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AppController {
	@Autowired
	private ProductService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		/*
		 * List<Product> listProducts = service.listAll();
		 * model.addAttribute("listOfProducts", listProducts); List<Order> listOfOrders
		 * = orderService.listAll(); model.addAttribute("listOfOrders", listOfOrders);
		 */
		User user = new User();
		model.addAttribute("user", user);
		
		return "home";
	}
	
	
	
	@RequestMapping("/dashboard")
	public String viewDashboardPage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listOfProducts", listProducts);
		List<Order> listOfOrders = orderService.listAll();
		model.addAttribute("listOfOrders", listOfOrders);
		return "index";
	}
	
	
	@RequestMapping("/new")
	public String showNewProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new-product";
	}
	
	
	@RequestMapping("/new-user")
	public String showNewUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "new-user";
	}
	
	@RequestMapping("/new-order")
	public String showNewOrder(Model model) {
		OrderDTO order = new OrderDTO();
		List<Product> prodNames = service.listAll();
		System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
		System.out.println(prodNames);
		model.addAttribute("listOfProds", prodNames);
		model.addAttribute("orderDTO", order);
		return "new-order";
		
	}
	
	
	
	@RequestMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product, Model model) {
		service.save(product);
	   	return viewDashboardPage(model);
	}
	
	@RequestMapping("/save-order")
	public String saveOrder(@ModelAttribute("order")  OrderDTO order, BindingResult bindingResult,
            Model model
			) {
		
		System.out.println(" Save Order ");
		
		if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            //return "index"
        } else{
        	
        	System.out.println(order);
        	Product product = service.get(order.getProductid());
        	Order orderObj = new Order();
        	orderObj.setName(order.getName());
        	orderObj.setSelectedProduct(product);
        	orderObj.setCount(order.getCount());
        	orderService.save(orderObj);
        }
        	return viewDashboardPage(model);
	}
	@RequestMapping("/save-order-byid")
	public String saveOrderById(@ModelAttribute("order")  OrderDTO order, BindingResult bindingResult,
            Model model
			) {
		
		System.out.println(" Save Order ");
		
		if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            //return "index"
        } else{
        	
        	System.out.println(order);
        	Product product = service.get(order.getProductid());
        	Order orderObj = orderService.get(order.getId());
        	orderObj.setName(order.getName());
        	orderObj.setSelectedProduct(product);
        	orderObj.setCount(order.getCount());
        	orderService.save(orderObj);
        }
        	//return "index";
	   	return viewDashboardPage(model);
	}
	@RequestMapping("/save-user")
	public String saveUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		System.out.println(bindingResult.hasErrors());
		
		if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            System.out.println(errorsMap);
            System.out.println(errorsMap.toString());
            redirectAttributes.addFlashAttribute("message",errorsMap.toString());
           //return "error";
            return "new-user";
        } else
		{
	     	userService.save(user);
	     	redirectAttributes.addFlashAttribute("message","User Created Successfully!");
	     	return "redirect:/";
        }
		
		//return "new-user";
	}
	
	
	
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable (name="id") long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable (name="id") long id, Model model) {
		service.delete(id);
		//return "index";
	   	return viewDashboardPage(model);
	}
	
	@RequestMapping("/edit-order/{id}")
	public ModelAndView showEditOrderPage(@PathVariable (name="id") long id) {
		ModelAndView mav = new ModelAndView("edit-order");
		Order order = orderService.get(id);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setName(order.getName());
		orderDTO.setCount(order.getCount());
		orderDTO.setProductid(order.getSelectedProduct().getId());
		List<Product> prodNames = service.listAll();
		System.out.println(" @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
		System.out.println(prodNames);
		mav.addObject("listOfProds", prodNames);
		mav.addObject("order", orderDTO);
		return mav;
	}
	
	@RequestMapping("/delete-order/{id}")
	public String deleteOrder(@PathVariable (name="id") long id, RedirectAttributes redirectAttributes, Model model) {
		orderService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Order deleted Successfully !!");
		//return "index";
	   	return viewDashboardPage(model);
	}
	
	@RequestMapping("/login")
	public String validateUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
		/*
		 * List<Product> listProducts = service.listAll();
		 * model.addAttribute("listOfProducts", listProducts); List<Order> listOfOrders
		 * = orderService.listAll(); model.addAttribute("listOfOrders", listOfOrders);
		 */
		User fetchUser = userService.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
		System.out.println(fetchUser);
		if(fetchUser != null) {
			redirectAttributes.addFlashAttribute("message", "User successfully Logged In!!!");
			model.addAttribute("fetchUser", fetchUser);
			return viewDashboardPage(model);
		}else {
			redirectAttributes.addFlashAttribute("message", "Invalid User credentials!!!");
			//return "redirect:/";
			return viewHomePage(model);
		}
	}
	@RequestMapping("/search")
	public String showSearchPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "search";
	}

	@RequestMapping("/search-product")
	public String searchProductResults(@ModelAttribute("product") Product product, Model model) {
		Product productResult = service.get(product.getId());
		System.out.println(productResult);
		model.addAttribute("productResult", productResult);
		
		return "search";
	}
}
