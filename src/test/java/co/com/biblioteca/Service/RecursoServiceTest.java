package co.com.biblioteca.Service;

import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Model.Recurso;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.Service.RecursoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class RecursoServiceTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private RecursoService recursoService;

    @Test
    @DisplayName("Test findAll Success")
    public void prestarRecurso() {
    //var recurso1=new Recurso
    }

}