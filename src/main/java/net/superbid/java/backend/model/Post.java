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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Post implements Base<Long>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	@Lob
	private String content; 
	
	@Column 
	private String description; 
	
	@Column
	private String title;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	@JsonAlias(value="created")
	private Date timeStamp;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	@JsonAlias(value="updated")
	private Date updateTimeStamp;	

	public Post() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	public Date getTimeStamp() {
		return timeStamp;
	}

	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	@PrePersist
    public void prePersist() {
		timeStamp = Calendar.getInstance().getTime();
    }

    @PreUpdate
    public void preUpdate() {
    	updateTimeStamp = Calendar.getInstance().getTime();
    }

	@Override
	public int hashCode() {
		return Objects.hash(id , content , title , description , timeStamp);
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
