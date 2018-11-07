package net.superbid.java.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Message {

	private Post data;

	public Message () {
	}
	
	public Message (Post data) {
		this();
		this.data = data;
	}

	public Post getData() {
		return data;
	}

	public void setData(Post data) {
		this.data = data;
	}
	
}
