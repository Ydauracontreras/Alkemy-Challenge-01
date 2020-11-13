package ar.alkemy.com.postcrud.constrollers;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

     @PostMapping("/post/add")
    public String addPost(@Valid Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new";
        }
        postServiceImp.newPost(post);
        return "redirect:/post";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable("id") Integer id, Model model) {
        Post post = postServiceImp.findById(id);
        if (post != null) {
            model.addAttribute("post", postServiceImp.findById(id));
            return "post";
        } else {
            model.addAttribute("notFound", true);
            return "404";
        }
    }

    @GetMapping("post/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id, Model model) {
        Post post = postServiceImp.findById(id);
        if (post == null) {
            model.addAttribute("notFound", true);
            return "404";
        }
        postServiceImp.delete(id);
        return "redirect:/post";
    }

    @GetMapping("/post/update/{id}")
    public String postEdit(@PathVariable("id") int id, Model model) {
        Post post = postServiceImp.findById(id);
        if (post != null) {
            model.addAttribute("notFound", false);
            model.addAttribute("post", post);
            return "update";
        } else {
            model.addAttribute("notFound", true);
            return "404";
        }
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, @Valid Post post, BindingResult result, Model model) {
        try {
            postServiceImp.updatePost(id, post);
            model.addAttribute("post", postServiceImp.listPost());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/post";

    }

}
