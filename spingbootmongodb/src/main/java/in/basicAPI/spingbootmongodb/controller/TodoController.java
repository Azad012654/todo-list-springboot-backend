package in.basicAPI.spingbootmongodb.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.basicAPI.spingbootmongodb.model.Todo;
import in.basicAPI.spingbootmongodb.repository.TodoRepository;

@RestController
public class TodoController {
	@Autowired
	private TodoRepository todoRepo;
	
	
	//Retrieving all the items from db
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos(){
		System.out.println("Hello2");
		List<Todo> todos = todoRepo.findAll();
		if(todos.size()>0) {
			return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No todos Found",HttpStatus.NOT_FOUND);
		}
	}
	
	//Creating and Saving items permanently in DB
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody Todo todo){
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
		    todoRepo.save(todo);
		    return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Retrieving single item from DB
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id")String id){
		Optional<Todo> todoOptional = todoRepo.findById(id);
		if(todoOptional.isPresent()) {
			return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Todo Item Not FOund"+id, HttpStatus.NOT_FOUND);
		}
	}
	
	//Updating the fields 
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id")String id, @RequestBody Todo todo){
		Optional<Todo> todoOptional = todoRepo.findById(id);
		if(todoOptional.isPresent()) {
			Todo todoSave = todoOptional.get();
			todoSave.setStatus(todo.getStatus()!=null?todo.getStatus():todoSave.getStatus());
			todoSave.setTodo(todo.getTodo()!=null?todo.getTodo():todoSave.getTodo());
			todoSave.setDescription(todo.getDescription()!=null?todo.getDescription():todoSave.getDescription());
			todoSave.setUpdatedAt(todo.getUpdatedAt()!=null?todo.getUpdatedAt():todoSave.getUpdatedAt());
			todoRepo.save(todoSave);
			return new ResponseEntity<>(todoSave, HttpStatus.OK);
			//			return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Todo Item Not FOund"+id, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	//Deleting Specific items in DB using ID of the item object
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		try {
			todoRepo.deleteById(id);
			return new ResponseEntity<>("Item Deleted with Id :"+id, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
