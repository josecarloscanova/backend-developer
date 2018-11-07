package net.superbid.java.backend.service;

import org.springframework.data.repository.CrudRepository;

import net.superbid.java.backend.model.Post;

public interface PostRepository extends CrudRepository<Post , Long>{

}
