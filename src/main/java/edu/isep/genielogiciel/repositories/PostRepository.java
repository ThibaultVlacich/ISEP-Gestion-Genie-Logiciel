package edu.isep.genielogiciel.repositories;


import edu.isep.genielogiciel.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    public Post findById(Integer id);
    /*
    public Post findByTopic(String topic);
    */
    @Query("SELECT p FROM Post p WHERE p.topic like %:topic%")
    public List<Post> findByTopic(@Param("topic") String topic);
    
    
  /*
   public List<Post> findByTitre(String titre);*/
}
