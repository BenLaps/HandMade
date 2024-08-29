package com.handmade.handmade.controllers;

import com.handmade.handmade.AutoFiller;
import com.handmade.handmade.models.Post;

import com.handmade.handmade.repo.PostRepository;
import com.handmade.handmade.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll ();
        model.addAttribute ( "posts", posts );

        boolean isAdmin = userService.currentUserHasRole("ROLE_ADMIN");
        model.addAttribute("isAdmin", isAdmin);
//        boolean isAdmin = userService.isAdmin();
//        model.addAttribute("isAdmin", isAdmin);
        return "main/blog";
    }

    @GetMapping("/blog/search")
    public String searchBlog(@RequestParam("query") String query, Model model) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(query);
        model.addAttribute("posts", posts);
        return "main/blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "post/post-add";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(
            @RequestParam String title,
            @RequestParam String sub_title,
            @RequestParam String img_url,
            @RequestParam String short_description,
            @RequestParam String text,
            Model model) {
        if (short_description == null || short_description.trim().isEmpty()) {
            short_description = AutoFiller.extractShortDescription(text, 2);
        }
        Post post = new Post(title, sub_title, img_url, short_description, text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String fullBlog(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById (id )){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById ( id );
        ArrayList<Post> res= new ArrayList<> (  );
        post.ifPresent ( res::add);
        model.addAttribute("post" ,res);
        return "post/post-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById (id )){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById ( id );
        ArrayList<Post> res= new ArrayList<> (  );
        post.ifPresent ( res::add);
        model.addAttribute("post" ,res);
        return "post/post-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostEdit(
            @PathVariable(value = "id") long id,
            @RequestParam String title,
            @RequestParam String sub_title,
            @RequestParam String img_url,
            @RequestParam String short_description,
            @RequestParam String text,
            Model model) {
        Post post = postRepository.findById ( id ).orElseThrow (  ) ;
        post.setText ( text );
        post.setImg_url ( img_url );
        post.setShort_description ( short_description );
        post.setSub_title ( sub_title );
        post.setTitle ( title );
        postRepository.save ( post );
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}/remove")
    public String postRemove(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById (id )){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById ( id );
        ArrayList<Post> res= new ArrayList<> (  );
        post.ifPresent ( res::add);
        model.addAttribute("post" ,res);
        return "post/post-details";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById ( id ).orElseThrow (  ) ;
        postRepository.delete ( post );
        return "redirect:/blog";
    }





}