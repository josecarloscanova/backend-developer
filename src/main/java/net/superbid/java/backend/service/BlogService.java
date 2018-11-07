package net.superbid.java.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.superbid.java.backend.model.BlogMessage;
import net.superbid.java.backend.model.Post;

@Service
public class BlogService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> retrieveAllPosts(){ 
		return postRepository.findAll();
	}
	
	public BlogMessage createUpdatePost(Post post) {
		return new BlogMessage(postRepository.save(post));
	}
	
	public void deletePost(Post post) { 
		postRepository.delete(post);
	}
	
	public BlogMessage findPost(Long id) { 
		Optional<Post> result = postRepository.findById(id);
		BlogMessage message; 
		if (result.isPresent()) {
			message = new  BlogMessage(result.get());
		}else { 
			message = new BlogMessage();
		}
		return message;
	}
	
}
