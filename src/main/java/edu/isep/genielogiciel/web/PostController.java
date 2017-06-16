package edu.isep.genielogiciel.web;


import edu.isep.genielogiciel.models.Post;
import edu.isep.genielogiciel.repositories.CommentRepository;
import edu.isep.genielogiciel.repositories.PostRepository;
import edu.isep.genielogiciel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/post")
public class PostController extends GLController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
   
    @RequestMapping("**")
    private ModelAndView all() {
        return new ModelAndView("post/post", "posts", postRepository.findAll());
    }
    
  
    
    @RequestMapping(value = "/search")
    public ModelAndView Search(@RequestParam("topic") String topic) {    	   
    	   return new ModelAndView("post/search", "posts", postRepository.findByTopic(topic));
    }
    
   
  
    
    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
   // @PreAuthorize("hasRole('ROLE_TEACHER')")
    private String create() {
        return "post/create";
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
   // @PreAuthorize("hasRole('ROLE_TEACHER')")
    private ModelAndView create(@RequestParam("titre") String titre, @RequestParam("description") String description, @RequestParam("topic") String topic) {
        Post post = new Post();
        
        post.setTitre(titre);
        post.setDescription(description);
        post.setTopic(topic);
        post.setId_teacher(getCurrentUser().getId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        
        post.setDate(date);

        

        postRepository.save(post);

        return new ModelAndView("redirect:/post?created");
    }
    
    @RequestMapping({"/delete", "/delete/"})
    //@PreAuthorize("hasRole('ROLE_TEACHER')")
    private ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Post post = postRepository.findById(id);

        if (post == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
        	postRepository.delete(post);

            return new ModelAndView("redirect:/post?deleted");
        }

        return new ModelAndView("post/delete", "post", post);
    }
    

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.GET)
   // @PreAuthorize("hasRole('ROLE_TEACHER')")
    private ModelAndView edit(@RequestParam("id") Integer id) {             
        Post post = postRepository.findById(id);


        Map<String, Object> model = new HashMap<>();
        model.put("post", post);
        model.put("posts", postRepository.findAll());

        return new ModelAndView("post/edit", model);
    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
  //  @PreAuthorize("hasRole('ROLE_TEACHER')")
    private ModelAndView edit(@RequestParam("id") Integer id, @RequestParam("titre") String titre, @RequestParam("description") String description, @RequestParam("topic") String topic) {
        Post post = postRepository.findById(id);
        post.setTitre(titre);
        post.setDescription(description);
        post.setTopic(topic);
        
        postRepository.save(post);

       

        return new ModelAndView("redirect:/post");
    }
   
    
    
   
}
