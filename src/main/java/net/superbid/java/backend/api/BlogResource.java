package net.superbid.java.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.superbid.java.backend.model.BlogMessage;
import net.superbid.java.backend.model.Post;
import net.superbid.java.backend.service.BlogService;

@RestController
@RequestMapping(path="/blog")
@Api(value = "/blog")
public class BlogResource {

	@Autowired
	private BlogService blogService;
	
	@ApiOperation(value = "Retrieve an array with all blog posts" , response=BlogMessage.class)
	@GetMapping(produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public BlogMessage retrieveBlogPost() {
			return blogService.retriveAllPosts();
	}
	
	@ApiOperation(value = "Retrieve a specific blog post by its id"  , response=BlogMessage.class)
	@GetMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public BlogMessage retrieveBlogPost(@PathVariable Long id) {
			return blogService.findPost(id);
	}
	
	@ApiOperation(value = "Create a new blog post" , response=BlogMessage.class)
	@PostMapping(produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public BlogMessage createBlogPost(@RequestBody Post post) { 
		return blogService.createUpdatePost(post);
	}

	@ApiOperation(value = "Update an existent blog post" , response=BlogMessage.class)
	@PutMapping(produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public BlogMessage updateBlogPost(@RequestBody Post post) {
		return blogService.createUpdatePost(post);
	}
	
	@ApiOperation(value = "Delete an existent blog post" , response=Void.class)
	@DeleteMapping(produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public void deleteBlogPost(@RequestBody Post post) {
		blogService.deletePost(post);
	}
	
}
