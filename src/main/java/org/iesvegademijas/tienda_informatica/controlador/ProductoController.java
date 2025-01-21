package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Producto;
import org.iesvegademijas.tienda_informatica.servicio.FabricanteService;
import org.iesvegademijas.tienda_informatica.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping("/productos")
    public String listar(Model model) {
        List<Producto> productos = productoService.listAll();
        model.addAttribute("productos", productos);

        return "productos";
    }

    @GetMapping("/productos/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {
        Producto producto = productoService.one(id);
        model.addAttribute("producto", producto);

        return "detalle-producto";
    }

    @GetMapping("/productos/crear")
    public String crear(Model model) {
        Producto producto = new Producto();
        List<Fabricante> fabricantes = fabricanteService.listAll();

        model.addAttribute("producto", producto);
        model.addAttribute("fabricantes", fabricantes);

        return "crear-producto";
    }

    @PostMapping("/productos/crear")
    public String submitCrear(@ModelAttribute("producto") Producto producto) {
        productoService.crearProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {
        Producto producto = productoService.one(id);
        List<Fabricante> fabricantes = fabricanteService.listAll();

        model.addAttribute("producto", producto);
        model.addAttribute("fabricantes", fabricantes);

        return "editar-producto";
    }

    @PostMapping("/productos/editar/{id}")
    public String submitEditar(@ModelAttribute("producto") Producto producto) {
        productoService.actualizarProducto(producto);
        return "redirect:/productos";
    }

    @PostMapping("/productos/borrar/{id}")
    public String submitBorrar(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
