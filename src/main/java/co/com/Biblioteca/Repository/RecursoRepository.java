package co.com.Biblioteca.Repository;

import co.com.Biblioteca.Model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends MongoRepository<Recurso,String> {

}
