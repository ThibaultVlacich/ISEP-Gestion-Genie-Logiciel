package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String titre;
    private String description;
    private Date date ;
    private String topic ;
    private int id_teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.EXTRA)
    @OrderBy("dateTime ASC")
private List<Comment> comments = new ArrayList<>();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId_teacher() {
		return id_teacher;
	}
	public void setId_teacher(int id_teacher) {
		this.id_teacher = id_teacher;
	}
	

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
