package co.com.biblioteca.Service;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Model.Recurso;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.Service.RecursoService;
import co.com.Biblioteca.Utils.Mensaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class RecursoServiceTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private RecursoService recursoService;

    @Test
    @DisplayName("Test findAll Success")
    public void buscarTodos() {
        var Array=new ArrayList<Recurso>();
        Array.add(new Recurso("Hombres de negro",AreaTematica.HISTORIA, TipoRecurso.DOCUMENTAL));
        Array.add(new Recurso("Futurama",AreaTematica.CIENCIAS,TipoRecurso.LIBRO));
        Array.add(new Recurso("Spiderman",AreaTematica.NATURALEZA,TipoRecurso.PELICULA));

        Mockito.when(recursoRepository.findAll()).thenReturn(Array);

        var resultado = recursoService.findAll();

        Assertions.assertEquals(3, resultado.size());
        Assertions.assertEquals(Array.get(0).getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(Array.get(1).getNombre(), resultado.get(1).getNombre());
        Assertions.assertEquals(Array.get(2).getTipoRecurso(), resultado.get(2).getTipoRecurso());
        Assertions.assertEquals(Array.get(0).getAreaTematica(), resultado.get(0).getAreaTematica());
        Mockito.verify(recursoRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test findById Success")
    public void buscarPorId() {

        var recurso=new Recurso("SSS","lendRecurso",AreaTematica.CIENCIAS,TipoRecurso.LIBRO,true);

        Mockito.when(recursoRepository.findById(any())).thenReturn(java.util.Optional.of(recurso));

        var resultado = recursoService.findById("SSS");

        Assertions.assertEquals(recurso.getNombre(), resultado.getNombre());
        Assertions.assertEquals(recurso.getId(), resultado.getId());
        Assertions.assertEquals(recurso.getTipoRecurso(), resultado.getTipoRecurso());
        Assertions.assertEquals(recurso.getAreaTematica(), resultado.getAreaTematica());
        Mockito.verify(recursoRepository, Mockito.times(1)).findById("SSS");
    }

    @Test
    @DisplayName("Test lendRecurso Success")
    public void prestarRecurso(){
        var fecha=  new Date();

        var recursoA = new Recurso("SSS","lendRecurso",AreaTematica.CIENCIAS,TipoRecurso.LIBRO,true);
        var recursoB = new Recurso("SSS","lendRecurso",AreaTematica.CIENCIAS,TipoRecurso.LIBRO,false,fecha);

        var mensaje =new Mensaje(true,"El recurso estaba disponible, y se te fue prestado el: "+fecha);

        Mockito.when(recursoRepository.findById("SSS")).thenReturn(Optional.of(recursoA));
        Mockito.when(recursoRepository.save(any())).thenReturn(recursoB);

        var resultado = recursoService.lendRecurso(recursoA.getId());

        Assertions.assertEquals(mensaje.getMensaje(),resultado.getMensaje());
        Assertions.assertEquals(mensaje.isEstado(),resultado.isEstado());

        Mockito.verify(recursoRepository, Mockito.times(1)).findById("SSS");
        Mockito.verify(recursoRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Test backRecurso Success")
    public void devolverRecurso(){
        var fecha=  new Date();

        var recursoA = new Recurso("SSS","lendRecurso",AreaTematica.CIENCIAS,TipoRecurso.LIBRO,false,fecha);
        var recursoB = new Recurso("SSS","lendRecurso",AreaTematica.CIENCIAS,TipoRecurso.LIBRO,true);

        var mensaje =new Mensaje(true,"El recurso fue entregado con exito");

        Mockito.when(recursoRepository.findById("SSS")).thenReturn(Optional.of(recursoA));
        Mockito.when(recursoRepository.save(any())).thenReturn(recursoB);

        var resultado = recursoService.backRecurso(recursoA.getId());

        Assertions.assertEquals(mensaje.getMensaje(),resultado.getMensaje());
        Assertions.assertEquals(mensaje.isEstado(),resultado.isEstado());

        Mockito.verify(recursoRepository, Mockito.times(1)).findById("SSS");
        Mockito.verify(recursoRepository, Mockito.times(1)).save(any());

    }


    @Test
    @DisplayName("Test findByAreaTipo Success")
    public void filtrarPorTipoArea(){

        var recursoA = new Recurso("SSS","Celulas",AreaTematica.CIENCIAS,TipoRecurso.LIBRO,true);
        var Array=new ArrayList<Recurso>();
        Array.add(recursoA);

        Mockito.when(recursoRepository.findByTipoRecursoAndAreaTematica(TipoRecurso.LIBRO,AreaTematica.CIENCIAS)).thenReturn(Array);

        RecursoMapper mapper = new RecursoMapper();
        var resultado = recursoService.findByAreaTipo(mapper.fromCollection(recursoA));

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(recursoA.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(recursoA.getId(), resultado.get(0).getId());

        Mockito.verify(recursoRepository,Mockito.times(1)).findByTipoRecursoAndAreaTematica(TipoRecurso.LIBRO,AreaTematica.CIENCIAS);

    }


}