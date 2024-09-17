package com.example.SistemaPedidos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SistemaPedidos.dtos.UsuarioRecordDto;
import com.example.SistemaPedidos.entities.UsuarioEntity;
import com.example.SistemaPedidos.repositories.UsuarioRepository;


@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity findUsuarioById(Long id_usuario) throws Exception {
        var usuario = usuarioRepository.findById(id_usuario).orElseThrow(()-> new Exception(" "));
        return usuario;
    }

    public UsuarioEntity addUsuario(UsuarioRecordDto usuarioRecordDto) {
        var usuario = new UsuarioEntity();
        usuario.setCpf(usuarioRecordDto.cpf());
        usuario.setNome(usuarioRecordDto.nome());
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioEntity> getAllUsuario() throws Exception {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public UsuarioEntity updateUsuario(Long id_usuario, UsuarioRecordDto usuarioRecordDto) throws Exception {
        var usuario = findUsuarioById(id_usuario);
        usuario = new UsuarioEntity();
        usuario.setCpf(usuarioRecordDto.cpf());
        usuario.setNome(usuarioRecordDto.nome());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuarioById(Long id_usuario) throws Exception {
        usuarioRepository.deleteById(id_usuario);
    }
}
