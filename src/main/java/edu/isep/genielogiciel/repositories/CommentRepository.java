package edu.isep.genielogiciel.repositories;


import edu.isep.genielogiciel.models.Comment;
import edu.isep.genielogiciel.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Comment findById(Integer id);
    public Comment findByPost(Post post);
}
