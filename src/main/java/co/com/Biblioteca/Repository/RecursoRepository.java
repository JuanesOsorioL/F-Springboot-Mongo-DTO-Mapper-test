package co.com.Biblioteca.Repository;

import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends MongoRepository<Recurso,String> {
    List<Recurso> findByTipoRecursoAndAreaTematica(TipoRecurso tipoRecuerso , AreaTematica areaTematica);
    List<Recurso> findByTipoRecursoOrAreaTematica(TipoRecurso tipoRecuerso , AreaTematica areaTematica);
}
