package com.randez_trying.SB_App.controller;

import com.randez_trying.SB_App.model.User;
import com.randez_trying.SB_App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", repository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Optional<User> user = repository.findById(id);
        ArrayList<User> list = new ArrayList<>();
        user.ifPresent(list::add);
        model.addAttribute("users", list);
        return "show";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") long id) {
        Optional<User> user = repository.findById(id);
        ArrayList<User> list = new ArrayList<>();
        user.ifPresent(list::add);
        model.addAttribute("user", list.get(0));
        return "edit";
    }

    @RequestMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        repository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        user.setId(id);
        repository.save(user);
        return "redirect:/";
    }
}
