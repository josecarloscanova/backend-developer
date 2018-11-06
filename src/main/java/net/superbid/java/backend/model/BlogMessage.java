package net.superbid.java.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BlogMessage {

	private Object data;

	public BlogMessage () {
	}
	
	public BlogMessage (Object data) {
		this();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
