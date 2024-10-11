package com.example.SistemaPedidos.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.SistemaPedidos.application.services.exceptions.SemNomeException;
import com.example.SistemaPedidos.dtos.UsuarioRecordDto;
import com.example.SistemaPedidos.dtos.repositories.UsuarioRepository;
import com.example.SistemaPedidos.entities.UsuarioEntity;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        List<UsuarioEntity> usuarios = new ArrayList<>();
        UsuarioEntity usuario = new UsuarioEntity(1L, "Arnaldo", "008598130369");
        UsuarioEntity usuario2 = new UsuarioEntity(2L, "Arnaldo", "008598130369");
        usuarios.add(usuario2);
        usuarios.add(usuario);

        lenient().when(usuarioRepository.findAll()).thenReturn(usuarios);
        lenient().when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    }

    @Test
    @DisplayName("Deve retornar uma lista com dois usuarios")
    public void deveRetornarDoisUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        assertEquals(2, usuarios.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    @DisplayName("Deve retornar um usuario pelo id")
    public void deveRetornarUmUsuarioPeloId() throws Exception {

    }

    @Test
    @DisplayName("Deve retornar um exception para usuario com nome vazio")
    public void deveLancarUmaExceptionParaStringVazia() {
         final var usuario = new UsuarioRecordDto(" ", "008958923980");
         assertThrows(SemNomeException.class, () -> usuarioService.addUsuario(usuario));
    }
}
