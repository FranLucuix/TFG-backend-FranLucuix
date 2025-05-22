package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.*;
import com.example.FranLucuixProyectoIntegrado.entities.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author francis
 */
@Service
public class DTOConverter {

    // ===== Usuario =====
    public UsuarioDTO usuarioToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }

    public Usuario usuarioToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        return usuario;
    }

    // ===== Producto =====
    public ProductoDTO productoToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getPrecioRebaja(),
                producto.getStock(),
                producto.getDescripcion(),
                producto.getCategoria()
        );
    }

    public Producto productoToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setIdProducto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setPrecioRebaja(dto.getPrecioRebajado());
        producto.setStock(dto.getStock());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

    // ===== Carrito =====
    public Carrito carritoToEntity(CarritoDTO dto) {
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(dto.getIdCarrito());

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        carrito.setUsuario(usuario);

        return carrito;
    }

    public CarritoDTO carritoToDTO(Carrito carrito) {
        return new CarritoDTO(
                carrito.getIdCarrito(),
                carrito.getUsuario().getIdUsuario()
        );
    }

    // ===== Pedido =====
    public PedidoDTO pedidoToDTO(Pedido pedido) {
        return new PedidoDTO(
                pedido.getIdPedido(),
                pedido.getUsuario().getIdUsuario(),
                pedido.getMetodoPago().getIdPago(),
                pedido.getFechapedido(),
                pedido.getTotal(),
                pedido.getEstado(),
                pedido.getDireccion(),
                pedido.getFechaEntrega()
        );
    }

    public Pedido pedidoToEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(dto.getIdPedido());
        pedido.setUsuario(new Usuario(dto.getIdUsuario()));
        pedido.setMetodoPago(new MetodoPago(dto.getIdPago()));
        pedido.setFechapedido(dto.getFechaPedido());
        pedido.setTotal(dto.getTotal());
        pedido.setEstado(dto.getEstado());
        pedido.setDireccion(dto.getDireccion());
        pedido.setFechaEntrega(dto.getFechaEntrega());
        return pedido;
    }

    // ===== MetodoPago =====
    public MetodoPagoDTO metodoPagoToDTO(MetodoPago metodoPago) {
        return new MetodoPagoDTO(
                metodoPago.getIdPago(),
                metodoPago.getTipo()
        );
    }

    public MetodoPago metodoPagoToEntity(MetodoPagoDTO dto) {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setIdPago(dto.getIdPago());
        metodoPago.setTipo(dto.getTipo());
        return metodoPago;
    }

    // ===== DetallePedido =====
    public DetallePedidoDTO detallePedidoToDTO(DetallePedido detalle) {
        return new DetallePedidoDTO(
                detalle.getId().getIdPedido(),
                detalle.getId().getIdProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario()
        );
    }

    public DetallePedido detallePedidoToEntity(DetallePedidoDTO dto) {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(new DetallePedidoId(dto.getIdPedido(), dto.getIdProducto()));
        detalle.setPedido(new Pedido(dto.getIdPedido()));
        detalle.setProducto(new Producto(dto.getIdProducto()));
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        return detalle;
    }

    // ===== CarritoProducto =====
    public CarritoProductoDTO carritoProductoToDTO(CarritoProducto cp) {
        return new CarritoProductoDTO(
                cp.getId().getIdCarrito(),
                cp.getId().getIdProducto(),
                cp.getCantidad()
        );
    }

    public CarritoProducto carritoProductoToEntity(CarritoProductoDTO dto) {
        CarritoProducto cp = new CarritoProducto();
        cp.setId(new CarritoProductoId(dto.getIdCarrito(), dto.getIdProducto()));
        cp.setCarrito(new Carrito(dto.getIdCarrito()));
        cp.setProducto(new Producto(dto.getIdProducto()));
        cp.setCantidad(dto.getCantidad());
        return cp;
    }
}
