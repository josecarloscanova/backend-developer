package net.superbid.java.backend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.superbid.java.backend.model.BlogMessage;
import net.superbid.java.backend.model.Post;
import net.superbid.java.backend.service.BlogService;

@RestController
@RequestMapping(path="/blog")
@Api(value = "/blog")
@ResponseStatus(code=HttpStatus.OK)
public class BlogResource {

	@Autowired
	private BlogService blogService;
	
	
	@ApiResponses(value= {@ApiResponse(code=200 , message="Return an array of Posts" , response=Post.class , responseContainer="List")})
	@ApiOperation(value = "Return all posts")
	@GetMapping(produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<Post> retrieveBlogPost() {
			return blogService.retrieveAllPosts();
	}
	
	@ApiResponses(value= {@ApiResponse(code=200 , message="Return a specific blog post by its id" ,  response=BlogMessage.class )})
	@ApiOperation(value = "Return a blog post"  , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public BlogMessage retrieveBlogPost(@PathVariable Long id) {
			return blogService.findPost(id);
	}
	
	@ApiResponses(value= {@ApiResponse(code=201 , message="Post created with success" , response=BlogMessage.class)})
	@ApiOperation(value = "Create a new blog post" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping
	public ResponseEntity<BlogMessage> createBlogPost(@RequestBody Post post) { 
		BlogMessage message  = blogService.createUpdatePost(post);
		return new ResponseEntity<>(message , null , HttpStatus.CREATED);
	}

	@ApiResponses(value= {@ApiResponse(code=200 , message="Return a specific blog post by its id" , response=BlogMessage.class)})
	@ApiOperation(value = "Update a blog post" , response=BlogMessage.class )
	@PutMapping(produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public BlogMessage updateBlogPost(@RequestBody Post post) {
		return blogService.createUpdatePost(post);
	}
	
	@ApiResponses(value= {@ApiResponse(code=200 , message="Delete an existent blog post otherwise do nothing" , response=Void.class)})
	@ApiOperation(value = "Delete a blog post")
	@DeleteMapping(produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public void deleteBlogPost(@RequestBody Post post) {
		blogService.deletePost(post);
	}
	
}
