package com.example.RojasMarket.service;


import com.example.RojasMarket.Model.IVentaDao;
import com.example.RojasMarket.Repository.ProductoRepository;
import com.example.RojasMarket.Repository.VentaRepository;
import com.example.RojasMarket.entity.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.*;


@Service
public class VentaService implements VentaRepository {

    @Autowired
    private IVentaDao ventaDao;

    @Autowired
    private ProductoRepository productoRepository;
    @Transactional
    public Double calcularTotalEIGV(Venta venta) {
        double total = 0.0;
        for (DetalleVenta detalle : venta.getItems()) {
            long productoId = detalle.getProducto().getId_producto();
            int cantidad = detalle.getCantidad();
            String estado = venta.getEstado();

            Integer productoIdInteger = Math.toIntExact(productoId);

            System.out.println("Cantidad: " + cantidad);

            Producto producto = productoRepository.findById(productoIdInteger).orElse(null);
            if (producto != null) {
                double precioSalida = producto.getPrecio_salida_producto();
                double subtotal = precioSalida * cantidad;
                total += subtotal;

                if ("aprobado".equals(estado)) {
                    int cantidadActual = producto.getCantidad_producto();
                    int resultado = cantidadActual - cantidad;
                    producto.setCantidad_producto(resultado);
                    System.out.println("Cantidad del producto: " + producto.getCantidad_producto());
                    System.out.println("Resultado: " + resultado);
                }


            }
            productoRepository.save(producto);
        }

        double igv = total * 0.18;

        venta.setIgv(igv);
        venta.setTotal(total + igv);

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Venta> findAll() {
        return ventaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Venta findById(Long id) {
        return ventaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Venta save(Venta venta) {
        Venta savedVenta = ventaDao.save(venta);

        return savedVenta;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ventaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] GenerarPdf(Long id){
        byte[] data=null;
        Venta venta=this.findById(id);
        Double rpta = this.ventaDao.totalByIdVenta(id);
        Double precioigv=this.ventaDao.montoIgv(id);

        try{
            venta.reportPdf().forEach(r ->{
                System.out.println(r.getNombre());
                System.out.println(r.getPrecio());
                System.out.println(r.getCantidad());
                System.out.println(r.getSubTotal());
            } );
           final File imgLog= ResourceUtils.getFile("classpath:img/logoo.png");

            Map<String, Object> params = new HashMap();

            params.put("total",rpta);
            params.put("PrecioIGV",precioigv);
           params.put("imgLogo",new FileInputStream(imgLog));

            JRBeanArrayDataSource dsInvoice = new JRBeanArrayDataSource(venta.reportPdf().toArray());
            params.put("dsInvoice",dsInvoice);
            params.put("nombreCliente",venta.getCliente().getNombre_cliente());
            params.put("nombreEmpleado",venta.getEmpleado().getFulldate());

            File file = new ClassPathResource("/Reportes/boletareporte.jasper").getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());

            data = JasperExportManager.exportReportToPdf(print);


        }catch (Exception e){
            e.printStackTrace();
        }


        return data;
    }




}

