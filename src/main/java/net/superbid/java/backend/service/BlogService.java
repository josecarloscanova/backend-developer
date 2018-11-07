package net.superbid.java.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.superbid.java.backend.model.Message;
import net.superbid.java.backend.model.Post;

@Service
public class BlogService {

	@Autowired
	private PostRepository postRepository;
	
	public Iterable<Post> retrieveAllPosts(){ 
		return postRepository.findAll();
	}
	
	public Message createUpdatePost(Post post) {
		return new Message(postRepository.save(post));
	}
	
	public Message updatePost(Long id , Post post) {
		if (postRepository.existsById(id))
			post.setId(id);
		return createUpdatePost(post);
	}
	
	public void deletePost(Long id) { 
		postRepository.delete(new Post(id , null , null , null));
	}
	
	public Message findPost(Long id) { 
		Optional<Post> result = postRepository.findById(id);
		Message message; 
		if (result.isPresent()) {
			message = new  Message(result.get());
		}else { 
			message = new Message();
		}
		return message;
	}
	
}
