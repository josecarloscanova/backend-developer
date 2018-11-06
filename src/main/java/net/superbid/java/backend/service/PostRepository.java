package net.superbid.java.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.superbid.java.backend.model.Post;

public interface PostRepository extends CrudRepository<Post , Long>{

	public List<Post> findAll();
	
	public Optional<Post> findById(Long id);
	
}
