package com.example.SistemaPedidos.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Service;

import com.example.SistemaPedidos.controllers.PedidoController;
import com.example.SistemaPedidos.controllers.ProdutoController;
import com.example.SistemaPedidos.controllers.UsuarioController;
import com.example.SistemaPedidos.dtos.ItemPedidoRecordDto;
import com.example.SistemaPedidos.dtos.PedidoRecordDto;
import com.example.SistemaPedidos.entities.ItemPedidoEntity;
import com.example.SistemaPedidos.entities.PedidoEntity;
import com.example.SistemaPedidos.entities.ProdutoEntity;
import com.example.SistemaPedidos.entities.UsuarioEntity;
import com.example.SistemaPedidos.repositories.ItemPedidoRepository;
import com.example.SistemaPedidos.repositories.PedidoRepository;
import com.example.SistemaPedidos.repositories.ProdutoRepository;
import com.example.SistemaPedidos.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private UsuarioRepository usuarioRepository;
    private ItemPedidoRepository itemPedidoRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository) {
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
        UsuarioEntity usuario = usuarioRepository.findById(pedidoRecordDto.id_usuario()).orElseThrow(() -> new Exception("Usuário não encontrado:/"));
        // Criação de um novo pedido
        PedidoEntity pedido = new PedidoEntity();
        pedido.setUsuario(usuario);
        //inciando uma variavel para salvar o pedido, e assim gerar um id automaticamente
        var pedidoSalvo = pedidoRepository.save(pedido);

        Set<ItemPedidoEntity> itens = new HashSet<>();
        for (ItemPedidoRecordDto item : pedidoRecordDto.itemPedidoRecordDto()) {
            ProdutoEntity produto = produtoRepository.findById(item.id_produto()).orElseThrow(() -> new Exception("ID do produto nao encontrado:/"));
            ItemPedidoEntity itemPedido = new ItemPedidoEntity();
            itemPedido.setProdutoEntity(produto);
            itemPedido.setId_itemPedido(pedido.getId_pedido());
            itemPedido.setQuantidade(item.quantidade());
            itemPedido.setPedidoEntity(pedidoSalvo);
            itens.add(itemPedido);
            itemPedidoRepository.save(itemPedido);
//            item.setProdutoEntity(produto);
//            item.setQuantidade(item.getQuantidade());
//            itemPedidoRepository.save(itens);

        }
        pedido.setItens(itens);
        pedido.setId_pedido(pedidoSalvo.getId_pedido());
//        pedidoSalvo = pedido;
        return pedido;
    }

    //metodos de delete do pedido e item pedido
    public void deletePedidoById(Long id_pedido) {
        pedidoRepository.deleteById(id_pedido);
    }

    //metodos find/findById usuario e pedido
    public PedidoEntity findPedidoById(Long id_pedido) throws Exception {
        var pedidoEncontrado = pedidoRepository.findById(id_pedido).orElseThrow(() -> new Exception("ID do pedido nao encontrado na base de dados:/"));
        pedidoEncontrado.getItens();
        return pedidoEncontrado;
    }

    public List<PedidoEntity> findAllPedidos() {
        return pedidoRepository.findAll();
    }

    public UsuarioEntity findUsuarioById(Long id_usuario) throws Exception {
        var usuario = usuarioRepository.findById(id_usuario).orElseThrow(() -> new Exception("ID do usuario nao encontrado:/"));
        return usuario;
    }

    public PedidoEntity getPedido(Long id_pedido) throws Exception {
        var pedido = findPedidoById(id_pedido);
        return pedido;
    }

    /**
     * Método getAll com implementação dos HATEOAS em cada, pedido, item,
     * usuario.
     *
     * @return
     * @throws Exception
     */
    public List<PedidoEntity> getAllPedido() throws Exception {
        var pedidos = findAllPedidos();

        var usuarios = usuarioRepository.findAll();
        var produtos = produtoRepository.findAll();
        //abaixo segue somente os links que levarao para a localização de cada item
        for (PedidoEntity pedidoFor : pedidos) {
            Long id_pedido = pedidoFor.getId_pedido();
            pedidoFor.add(linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).getPedido(id_pedido)).withSelfRel());
        }
        for (ProdutoEntity produtoFor : produtos) {
            Long id_produto = produtoFor.getId_produto();
            produtoFor.add(linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).getProduto(id_produto)).withSelfRel());
        }
        for (UsuarioEntity usuarioFor : usuarios) {
            Long id_usuario = usuarioFor.getId_usuario();
            usuarioFor.add(linkTo(methodOn(UsuarioController.class).getUsuario(id_usuario)).withSelfRel());
        }

        return pedidos;
    }

    /**
     * Método de delete especificamente para deletar os itens de um pedido.
     *
     * @param id_pedido
     * @throws Exception
     */
//    @Transactional
//    public void deleteItensDoPedido(Long id_pedido) throws Exception {
//        itemPedidoRepository.deleteAllByIdPedido(id_pedido);
//    }

    /**
     * Método de restauração dos itens pedidos do pedido sem deletar o pedido
     * deleta os itens pedidos antigos para os novos itens pedidos colcados no record Dto
     *
     * @param id_pedido
     * @param pedidoRecordDto
     * @throws Exception
     */
    @Transactional
    public void addItensAoPedido(Long id_pedido, PedidoRecordDto pedidoRecordDto) throws Exception {
        PedidoEntity pedidoEncontrado = findPedidoById(id_pedido);
        itemPedidoRepository.deleteAllByIdPedido(id_pedido);
        Set<ItemPedidoEntity> itens = new HashSet();

        for (ItemPedidoRecordDto item : pedidoRecordDto.itemPedidoRecordDto()) {
            ProdutoEntity produto = produtoRepository.findById(item.id_produto())
                    .orElseThrow(() -> new Exception("ID do produto nao encontrado:/"));
            var itemPedido = new ItemPedidoEntity();
            itemPedido.setProdutoEntity(produto);
            itemPedido.setQuantidade(item.quantidade());
            itemPedido.setPedidoEntity(pedidoEncontrado);
            itens.add(itemPedido);
            itemPedidoRepository.save(itemPedido);
        }
        pedidoEncontrado.setItens(itens);
    }

    /**
     * Realiza a busca do usuario pelo id do pedido(fútil)
     *
     * @param id_pedido
     * @return
     * @throws Exception
     */
    public String findUsuarioByIdPedido(Long id_pedido) throws Exception {
        var pedido = pedidoRepository.findById(id_pedido).orElseThrow(() -> new Exception("ID pedido nao econtrado"));
        return pedido.getUsuario().getNome();
    }

//    public ProdutoEntity findProdutoById(UUID idProduto) throws Exception {
//        return produtoRepository.findById(idProduto).orElseThrow(() -> new Exception("ID produto nao encontrado no banco de dados"));
//    }
}
