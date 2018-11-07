package net.superbid.java.backend.api;

import java.util.ArrayList;
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
import net.superbid.java.backend.model.Message;
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
			List<Post> list = new ArrayList<>();
			blogService.retrieveAllPosts().forEach(t -> list.add(t));
			return list;
	}
	
	@ApiResponses(value= {@ApiResponse(code=200 , message="Return a specific blog post by its id" ,  response=Message.class )})
	@ApiOperation(value = "Return a blog post"  , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping(path="/{id}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Message retrieveBlogPost(@PathVariable Long id) {
			return blogService.findPost(id);
	}
	
	@ApiResponses(value= {@ApiResponse(code=201 , message="Post created with success" , response=Message.class)})
	@ApiOperation(value = "Create a new blog post" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping
	public ResponseEntity<Message> createBlogPost(@RequestBody Post post) { 
		Message message  = blogService.createUpdatePost(post);
		return new ResponseEntity<>(message , null , HttpStatus.CREATED);
	}

	@ApiResponses(value= {@ApiResponse(code=200 , message="Update a specific blog post by its id" , response=Message.class)})
	@ApiOperation(value = "Update a blog post" , response=Message.class)
	@PutMapping(path="/{id}" , produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Message updateBlogPost(@PathVariable Long id , @RequestBody Post post) {
		return blogService.updatePost(id , post);
	}
	
	@ApiResponses(value= {@ApiResponse(code=200 , message="Delete an existent blog post otherwise do nothing" , response=Void.class)})
	@ApiOperation(value = "Delete a blog post")
	@DeleteMapping(path="/{id}")
	public void deleteBlogPost(@PathVariable Long id) {
		blogService.deletePost(id);
	}
	
}
