package net.superbid.java.backend.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(Include.NON_NULL)
@Entity
public class Post implements Base<Long>{

	@ApiModelProperty(required=false)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	@ApiModelProperty(required=true)
	@Lob
	private String content; 
	
	@ApiModelProperty(required=true)
	@Column(nullable=false , insertable=true , updatable=true) 
	private String description; 
	
	@ApiModelProperty(required=true)
	@Column(nullable=false , insertable=true , updatable=true)
	private String title;
	
	@ApiModelProperty(required=false)
	@Column(nullable=false , insertable=true , updatable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date created;
	
	@ApiModelProperty(required=false)
	@Column(nullable=true , insertable=true , updatable=true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date updated;	

	public Post() {}
	
	public Post(Long id, String content, String description, String title) {
		super();
		this.id = id;
		this.content = content;
		this.description = description;
		this.title = title;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@PrePersist
	private void prePersist() { 
		this.created = Calendar.getInstance().getTime();
	}
	
	@PreUpdate
	private void preUpdate() { 
		this.updated = Calendar.getInstance().getTime();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id , content , title , description , created);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
