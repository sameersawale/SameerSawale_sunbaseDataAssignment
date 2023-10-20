package com.example.sunbaseData.Controller;

import com.example.sunbaseData.Model.Customer;
import com.example.sunbaseData.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/newCustomer")
    public String newCustomer(Model model){
        Customer customer=new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @PostMapping("/add")
    public RedirectView addEmployee(@ModelAttribute("customer") Customer customer){
        customerService.addCustomer(customer);
        return new RedirectView("/", true);

    }

    @GetMapping("/")
    public ModelAndView homePageView(){
        ModelAndView mv=new ModelAndView("index");
        List<Customer>customerList=customerService.getCustomerList();
        mv.addObject("customerList", customerList);
        return mv;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model){
        Customer customer=customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "updateCustomer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id){
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

}
