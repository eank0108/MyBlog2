package com.example.myblog2.controller;

import com.example.myblog2.model.Post;
import com.example.myblog2.repository.PostRepository;
import com.example.myblog2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
    PostRepository postRepository;
    @Autowired
    public ViewController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/login")
    public String signinView(){
        return "login";
    }
    @GetMapping("/signup")
    public String signupView(){
        return "signup";
    }
    @GetMapping("/addpost")
    public String addpostView(Model model,  @AuthenticationPrincipal UserDetailsImpl authUser){
        if (authUser != null) {
            model.addAttribute("userName", authUser.getUser().getUsername());
        }
        return "postadd";
    }
    @GetMapping("/detail/{id}")
    public String postdetailView(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl authUser){
        model.addAttribute("postId", id);
        if (authUser != null) {
            model.addAttribute("userId", authUser.getUser().getId());
            model.addAttribute("userName", authUser.getUser().getUsername());
        }
        return "postdetail";
    }
    @GetMapping("/edit/{id}")
    public String postEditView(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl authUser) throws IllegalAccessException {

        Post post = postRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 Post 는 존재하지 않습니다."));
        if (post.getUser().getId() != authUser.getUser().getId()) {
            throw new IllegalAccessException("잘못된 접근 입니다");
        }
        model.addAttribute("post", post);
        if (authUser != null) {
            model.addAttribute("userId", authUser.getUser().getId());
            model.addAttribute("userName", authUser.getUser().getUsername());
        }
        return "postedit";
    }
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl authUser){
        if (authUser != null) {
            model.addAttribute("userName", authUser.getUser().getUsername());
        }
        return "index";
    }


}
