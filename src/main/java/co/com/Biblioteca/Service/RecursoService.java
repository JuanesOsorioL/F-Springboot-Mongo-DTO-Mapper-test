package co.com.Biblioteca.Service;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Model.Recurso;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.Utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;
    RecursoMapper mapper = new RecursoMapper();

    public RecursoDto findById(String id){
        Recurso recurso = recursoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }

    public List<RecursoDto> findAll(){
        return mapper.fromCollectionList(recursoRepository.findAll());
    }

    /*public Boolean consultarEstado(String id){
        RecursoDto recurso = findById(id);
       return recurso.isDisponible();
    }*/

    public Mensaje findByAvailability(String id){
        RecursoDto recursoDto = findById(id);
        return new Mensaje().printAvailability(recursoDto.isDisponible(),recursoDto.getFechaPrestamo());
    }

    public Mensaje lendRecurso(String id){
        RecursoDto recursoDto = findById(id);
        if (recursoDto.isDisponible()){
            recursoDto.setDisponible(false);
            recursoDto.setFechaPrestamo(new Date());
            Recurso recurso = mapper.fromDTO(recursoDto);
            mapper.fromCollection(recursoRepository.save(recurso));
        }
        return new Mensaje().printLoan(recursoDto.isDisponible(),recursoDto.getFechaPrestamo());
    }

    public RecursoDto crear(RecursoDto recursoDto) {
        Recurso recurso = mapper.fromDTO(recursoDto);
        return mapper.fromCollection(recursoRepository.save(recurso));
    }

    public void delete(String id){
        recursoRepository.deleteById(id);
    }

    public  Mensaje backRecurso(String id){
        RecursoDto recursoDto = findById(id);
        Mensaje mensaje = new Mensaje().printBack(recursoDto.isDisponible(),recursoDto.getFechaPrestamo());

        if(!recursoDto.isDisponible()){
            recursoDto.setDisponible(true);
            recursoDto.setFechaPrestamo(null);
            Recurso recurso = mapper.fromDTO(recursoDto);
            mapper.fromCollection(recursoRepository.save(recurso));
        }
        return mensaje;
    }

    public List<RecursoDto> findByAreaTipo(RecursoDto recursoDto){
        return (Objects.nonNull(recursoDto.getTipoRecurso()) && Objects.nonNull(recursoDto.getAreaTematica())) ?
                mapper.fromCollectionList(recursoRepository
                        .findByTipoRecursoAndAreaTematica(recursoDto.getTipoRecurso(),recursoDto.getAreaTematica())):
        mapper.fromCollectionList( recursoRepository
                .findByTipoRecursoOrAreaTematica(recursoDto.getTipoRecurso(),recursoDto.getAreaTematica()));
    }

}
