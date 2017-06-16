package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Comment;
import edu.isep.genielogiciel.models.Post;
import edu.isep.genielogiciel.repositories.CommentRepository;
import edu.isep.genielogiciel.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/comment")
public class CommentController extends GLController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @RequestMapping("**")
    private ModelAndView all() {
        return new ModelAndView("comment/all", "comments", commentRepository.findAll());
    }
    
  
    
    @RequestMapping(value = {"/details", "/details/"}, method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") Integer id) { 
    		Post post = postRepository.findById(id);
    		Comment comment = commentRepository.findByPost(post);
    		
    	   return new ModelAndView("comment/details", "comments", comment);
    }
    
    
    
    
	
    
	@RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    private String create() {
        return "comment/create";
    }


    @RequestMapping(value = {"create"}, method = RequestMethod.POST)
    private ModelAndView create(@RequestParam("post_id") Integer post_id, @RequestParam("commentText") String commentText) {
        Post post = postRepository.findById(post_id);

        if (post == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Comment comment = new Comment();

        comment.setPost(post);
        comment.setUser(getCurrentUser());
        comment.setCommentText(commentText);
        comment.setDateTime(LocalDateTime.now());

        commentRepository.save(comment);

        return new ModelAndView("redirect:/comment?created");
    }
    
    @RequestMapping({"/delete", "/delete/"})
   // @PreAuthorize("hasRole('ROLE_TEACHER')")
    private ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Comment comment = commentRepository.findById(id);

        if (comment == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
        	commentRepository.delete(comment);

            return new ModelAndView("redirect:/comment?deleted");
        }

        return new ModelAndView("comment/delete", "comment", comment);
    }
}

