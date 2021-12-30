package co.com.Biblioteca.Mapper;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Model.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDto dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setNombre(dto.getNombre());
        recurso.setDisponible(dto.isDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setAreaTematica(dto.getAreaTematica());
        recurso.setAreaTematica(dto.getAreaTematica()
        );
        return recurso;
    }

    public RecursoDto fromCollection(Recurso collection) {
        RecursoDto recursoDto = new RecursoDto();
        recursoDto.setId(collection.getId());
        recursoDto.setNombre(collection.getNombre());
        recursoDto.setDisponible(collection.isDisponible());
        recursoDto.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDto.setAreaTematica(collection.getAreaTematica());
        recursoDto.setTipoRecurso(collection.getTipoRecurso());
        return recursoDto;
    }


    public List<RecursoDto> fromCollectionList(List<Recurso> collection){
        if(collection==null){
            return null;
        }
        List<RecursoDto> list = new ArrayList<>(collection.size());
        Iterator listTrack = collection.iterator();

        while(listTrack.hasNext()){
            Recurso recurso = (Recurso) listTrack.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }
}
