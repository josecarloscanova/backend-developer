package java_backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.superbid.java.backend.model.Post;

public class PostGenerator {

	public static void main(String[] args) throws JsonProcessingException { 
		ObjectMapper mapper = new ObjectMapper();
		Post post = new Post();
		post.setTitle("Title");
		post.setDescription("Description");
		post.setContent("The Content of the Post");
		System.out.println(mapper.writeValueAsString(post));
	}
}
