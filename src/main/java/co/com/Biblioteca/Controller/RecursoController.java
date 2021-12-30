package co.com.Biblioteca.Controller;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Recursos")
public class RecursoController {

    @Autowired
    RecursoService recursoService;

    @PostMapping("/crear")
    public ResponseEntity<RecursoDto> create(@RequestBody RecursoDto recursoDto) {
        return new ResponseEntity(recursoService.crear(recursoDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDto> mostrarDisponibilidad(@PathVariable("id") String id) {
        return new ResponseEntity(recursoService.ConsultarDisponibilidad(id), HttpStatus.OK);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<RecursoDto> prestar(@PathVariable String id) {
        return new ResponseEntity(recursoService.PrestarUnRecurso(id), HttpStatus.OK);

    }
    @PutMapping("/devolver/{id}")
    public ResponseEntity<RecursoDto> devolver(@PathVariable String id) {
        return new ResponseEntity(recursoService.EntregarRecurso(id), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            recursoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/buscar")
    public ResponseEntity<RecursoDto> buscarTipoYArea(@RequestBody RecursoDto recursoDto) {
        return new ResponseEntity(recursoService.buscarAreaYTipo(recursoDto), HttpStatus.OK);
    }
}
