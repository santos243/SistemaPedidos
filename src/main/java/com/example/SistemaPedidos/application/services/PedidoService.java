package com.example.SistemaPedidos.application.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.SistemaPedidos.dtos.ItemPedidoRecordDto;
import com.example.SistemaPedidos.dtos.PedidoRecordDto;
import com.example.SistemaPedidos.dtos.repositories.ItemPedidoRepository;
import com.example.SistemaPedidos.dtos.repositories.PedidoRepository;
import com.example.SistemaPedidos.dtos.repositories.ProdutoRepository;
import com.example.SistemaPedidos.dtos.repositories.UsuarioRepository;
import com.example.SistemaPedidos.entities.ItemPedidoEntity;
import com.example.SistemaPedidos.entities.PedidoEntity;
import com.example.SistemaPedidos.entities.ProdutoEntity;
import com.example.SistemaPedidos.entities.UsuarioEntity;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private UsuarioRepository usuarioRepository;
    private ItemPedidoRepository itemPedidoRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository,
            ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    /**
     * Método de criação de pedido
     *
     * @param pedidoRecordDto
     * @return
     * @throws Exception
     */
    public PedidoEntity createPedido(PedidoRecordDto pedidoRecordDto) throws Exception {
        // Busca o usuario pelo ID
        UsuarioEntity usuario = usuarioRepository.findById(pedidoRecordDto.idUsuario())
                .orElseThrow(() -> new Exception("Usuário não encontrado:/"));

        if (pedidoRecordDto.itens().isEmpty()) {
            throw new NullPointerException("O campo de itens nao pode estar vazio");
        }
        // Criação de um novo pedido
        PedidoEntity pedido = new PedidoEntity();
        pedido.setUsuario(usuario);
        // inciando uma variavel para salvar o pedido, e assim gerar um id
        // automaticamente
        var pedidoSalvo = pedidoRepository.save(pedido);

        Set<ItemPedidoEntity> itens = new HashSet<>();
        for (ItemPedidoRecordDto item : pedidoRecordDto.itens()) {
            ProdutoEntity produto = produtoRepository.findById(item.idProduto())
                    .orElseThrow(() -> new Exception("ID do produto nao encontrado:/"));
            ItemPedidoEntity itemPedido = new ItemPedidoEntity();
            itemPedido.setProdutoEntity(produto);
            itemPedido.setQuantidade(item.quantidade());
            itemPedido.setPedidoEntity(pedidoSalvo);
            itens.add(itemPedido);
            itemPedidoRepository.save(itemPedido);
        }
        pedido.setItens(itens);
        pedido.setIdPedido(pedidoSalvo.getIdPedido());
        return pedido;
    }

    // metodos find/findById usuario e pedido
    public PedidoEntity findPedidoById(Long id_pedido) throws Exception {
        var pedidoEncontrado = pedidoRepository.findById(id_pedido)
                .orElseThrow(() -> new Exception("ID do pedido nao encontrado na base de dados:/"));
        return pedidoEncontrado;
    }

    public List<PedidoEntity> findAllPedidos() {
        return pedidoRepository.findAll();
    }

    public UsuarioEntity findUsuarioById(Long id_usuario) throws Exception {
        var usuario = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new Exception("ID do usuario nao encontrado:/"));
        return usuario;
    }

    public PedidoEntity getPedido(Long id_pedido) throws Exception {
        var pedido = findPedidoById(id_pedido);
        return pedido;
    }

    /**
     * metodo getAll para pedios
     *
     * @return
     * @throws Exception
     */
    public List<PedidoEntity> getAllPedido() throws Exception {
        var pedidos = findAllPedidos();
        return pedidos;
    }

    // Delete itens do pedido, sem afetar o pedido
    @Transactional
    public void deleteItensDoPedido(Long id_pedido) throws Exception {
        itemPedidoRepository.deleteAllByIdPedido(id_pedido);
    }

    /*
     * @param id_pedido
     *
     * @param pedidoRecordDto
     *
     * @throws Exception
     */
    @Transactional
    public void addItensAoPedido(Long id_pedido, PedidoRecordDto pedidoRecordDto) throws Exception {
        PedidoEntity pedidoEncontrado = findPedidoById(id_pedido);
        deleteItensDoPedido(id_pedido);
        // itemPedidoRepository.deleteAllByIdPedido(id_pedido);
        Set<ItemPedidoEntity> itens = new HashSet<>();

        for (ItemPedidoRecordDto item : pedidoRecordDto.itens()) {
            ProdutoEntity produto = produtoRepository.findById(item.idProduto())
                    .orElseThrow(() -> new Exception("Produto nao encontrado no banco de dados"));
            var itemPedido = new ItemPedidoEntity();
            itemPedido.setPedidoEntity(pedidoEncontrado);
            itemPedido.setProdutoEntity(produto);
            itemPedido.setQuantidade(item.quantidade());
            itens.add(itemPedido);
            itemPedidoRepository.save(itemPedido);
        }
        pedidoEncontrado.setItens(itens);
    }

    // método para deletar o pedido inteiro.
    public void deletePedidoById(Long id_pedido) {
        pedidoRepository.deleteById(id_pedido);
    }
}
