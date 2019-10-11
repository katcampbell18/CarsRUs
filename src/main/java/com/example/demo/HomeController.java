package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/add_car")
    public String carForm(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/process_car")
    public String processForm(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/car_list")
    public String carList(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "carlist";
    }

    @GetMapping("/add_categ")
    public String categForm(Model model){
        model.addAttribute("category", new Category());
        return "categform";
    }

    @PostMapping("/process_catg")
    public String processcatgForm(@ModelAttribute Category catg){
        categoryRepository.save(catg);
        return "redirect:/";
    }

    @RequestMapping("/catg_list")
    public String catgList(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "categlist";
    }

    @RequestMapping("/detail_car/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcar";
    }

    @RequestMapping("/update_car/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("delete_car/{id}")
    public String delCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }
}

