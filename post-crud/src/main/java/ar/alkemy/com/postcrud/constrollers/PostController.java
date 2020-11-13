package ar.alkemy.com.postcrud.constrollers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ar.alkemy.com.postcrud.entities.Post;
import ar.alkemy.com.postcrud.services.PostServiceImp;

@Controller
public class PostController {

    @Autowired
    PostServiceImp postServiceImp;

    @GetMapping("/post")
    public String listPost(Model model) {
        model.addAttribute("list", postServiceImp.listPost());
        return "index";
    }

    @GetMapping("/post/new")
    public String newPost(Post post) {
        return "new";
    }

}