package in.basicAPI.spingbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.basicAPI.spingbootmongodb.model.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

}
