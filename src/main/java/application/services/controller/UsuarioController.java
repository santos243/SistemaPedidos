package application.services.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaPedidos.dtos.UsuarioRecordDto;

import application.services.UsuarioService;
import domain.entities.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioEntity> addUsuario(@RequestBody@Valid UsuarioRecordDto usuarioRecordDto) {
        var usuario = usuarioService.addUsuario(usuarioRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<UsuarioEntity> getUsuario(@PathVariable(value="idUsuario")Long id_usuario) throws Exception {
        var usuarioEncontrado = usuarioService.findUsuarioById(id_usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioEncontrado);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioEntity>> getAllUsuarios() throws Exception {
        var usuarios = usuarioService.getAllUsuario();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PutMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="idUsuario")Long id_usuario,
                                                @RequestBody@Valid UsuarioRecordDto usuarioRecordDto) throws Exception {
        var usuario = usuarioService.updateUsuario(id_usuario, usuarioRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
     }

     @DeleteMapping("/usuario/{idUsuario}")
     public ResponseEntity<Object> deleteUsuario(@PathVariable(value="idUsuario")Long id_usuario) throws Exception {
        usuarioService.deleteUsuarioById(id_usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Deleção efetuada com sucesso");
     }
}
