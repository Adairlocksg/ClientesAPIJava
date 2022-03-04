package Clientes.rest;

import Clientes.model.entity.Clientes;
import Clientes.model.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClientesRepository repository;

    @Autowired
    public ClientesController(ClientesRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clientes salvar(@Valid @RequestBody Clientes clientes){
        return repository.save(clientes);
    }

    @GetMapping
    public List<Clientes> acharTodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Clientes acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O cliente " + id + " não existe em nossa Aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(clientes ->{
                    repository.delete(clientes);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O cliente " + id + " não existe em nossa aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Clientes dadoAtualizado){
        repository
                .findById(id)
                .map(clientes -> {
                    clientes.setNome(dadoAtualizado.getNome());
                    clientes.setNome(dadoAtualizado.getNome());
                    clientes.setCnpj(dadoAtualizado.getCnpj());
                    clientes.setEndereco(dadoAtualizado.getEndereco());
                    clientes.setTelefone(dadoAtualizado.getTelefone());
                    return repository.save(clientes);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O cliente " + id + " não existe em nossa aplicação!"));
    }
}
