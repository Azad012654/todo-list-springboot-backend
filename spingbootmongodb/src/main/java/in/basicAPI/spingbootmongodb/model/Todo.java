package in.basicAPI.spingbootmongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todolist")
public class Todo {

@Id
private String id;
private String todo;
private String description;
private Boolean status;
private Date createdAt;
private Date updatedAt;
public void setCreatedAt(Date date) {
	
	this.createdAt=date;
	
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTodo() {
	return todo;
}
public void setTodo(String todo) {
	this.todo = todo;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean completed) {
	this.status = completed;
}
public Date getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}
public Date getCreatedAt() {
	return createdAt;
}
}
