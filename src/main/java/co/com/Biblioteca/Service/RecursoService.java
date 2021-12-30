package co.com.Biblioteca.Service;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Model.Recurso;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.Utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;
    RecursoMapper mapper = new RecursoMapper();

    public RecursoDto consultarPorId(String id){
        Recurso recurso = recursoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }

    public Boolean consultarEstado(String id){
        RecursoDto recurso = consultarPorId(id);
       return recurso.isDisponible();
    }

    public Mensaje ConsultarDisponibilidad(String id){
        RecursoDto recursoDto=consultarPorId(id);
        return new Mensaje().imprimirDisponibilidad(recursoDto.isDisponible(),recursoDto.getFechaPrestamo());
    }

    public Mensaje PrestarUnRecurso(String id){
        RecursoDto recursoDto=consultarPorId(id);
        if (recursoDto.isDisponible()){
            recursoDto.setDisponible(false);
            recursoDto.setFechaPrestamo(new Date());
            Recurso recurso = mapper.fromDTO(recursoDto);
            mapper.fromCollection(recursoRepository.save(recurso));
        }
        return new Mensaje().imprimiPrestamo(recursoDto.isDisponible(),recursoDto.getFechaPrestamo());
    }

    public RecursoDto crear(RecursoDto recursoDto) {
        Recurso recurso = mapper.fromDTO(recursoDto);
        return mapper.fromCollection(recursoRepository.save(recurso));
    }

    public void delete(String id){
        recursoRepository.deleteById(id);
    }

    public  Mensaje EntregarRecurso(String id){
        RecursoDto recursoDto = consultarPorId(id);
        Mensaje mensaje = new Mensaje().imprimirDevolucion(recursoDto.isDisponible(),recursoDto.getFechaPrestamo());

        if(!recursoDto.isDisponible()){
            recursoDto.setDisponible(true);
            recursoDto.setFechaPrestamo(null);
            Recurso recurso = mapper.fromDTO(recursoDto);
            mapper.fromCollection(recursoRepository.save(recurso));
        }
        return mensaje;
    }

    public void buscarAreaYTipo(RecursoDto recursoDto){
        recursoRepository.deleteById(recursoDto.getId());
    }
}
