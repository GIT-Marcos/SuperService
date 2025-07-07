/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.test2.superservice;

import entities.DetalleRetiro;
import entities.NotaRetiro;
import entities.Pago;
import entities.Repuesto;
import entities.Stock;
import entities.VentaRepuesto;
import enums.EstadoVentaRepuesto;
import enums.MetodosPago;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.junit.jupiter.api.Assertions.*;
import util.Util;

/**
 * Test para poblar la base de datos
 *
 * @author ia
 */
public class SuperServiceTest {

    private Session session = Util.getHibernateSession();

    public SuperServiceTest() {
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testMain() {

        Transaction tx = session.beginTransaction();

        // Crear repuestos base
        List<Repuesto> repuestos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Stock stock = new Stock();
            stock.setCantidad(100.0);
            stock.setCantMinima(10.0);
            stock.setUnidadMedida("unidad");
            stock.setUbicacion("Depósito A" + i);
            stock.setActivo(true);

            Repuesto repuesto = new Repuesto();
            repuesto.setCodBarra("REP" + i);
            repuesto.setMarca("Marca" + i);
            repuesto.setDetalle("Detalle del repuesto " + i);
            repuesto.setPrecio(BigDecimal.valueOf(1500 + i * 100));
            repuesto.setActivo(true);
            repuesto.setStock(stock);

            session.persist(repuesto);
            repuestos.add(repuesto);
        }

        int anio = 2025;
        int totalVentas = 50;
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < totalVentas; i++) {
            // Generar mes y día válidos aleatorios
            int mes = random.nextInt(12) + 1; // 1 a 12
            int diaMax = LocalDate.of(anio, mes, 1).lengthOfMonth();
            int dia = random.nextInt(diaMax) + 1; // 1 a diaMax

            LocalDate fechaVenta = LocalDate.of(anio, mes, dia);

            // Crear nota de retiro
            NotaRetiro nota = new NotaRetiro();
            nota.setFecha(fechaVenta);

            for (int d = 0; d < 2; d++) {
                DetalleRetiro detalle = new DetalleRetiro();
                detalle.setCantidad(2.0);
                detalle.setRepuesto(repuestos.get((i + d) % repuestos.size()));
                nota.getDetallesRetiro().add(detalle);
            }

            // Calcular monto total según los detalles
            BigDecimal montoTotal = nota.getDetallesRetiro().stream()
                    .map(d -> d.getRepuesto().getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            VentaRepuesto venta = new VentaRepuesto();
            venta.setFechaVenta(fechaVenta);
            venta.setMontoTotal(montoTotal);
            venta.setMontoFaltante(BigDecimal.ZERO);
            venta.setActivo(true);
            venta.setEstadoVenta(EstadoVentaRepuesto.PAGADO);
            venta.setNotaRetiro(nota);

            // Agregar 1 o 2 pagos aleatoriamente
            int pagosCount = (random.nextBoolean()) ? 1 : 2;
            for (int p = 0; p < pagosCount; p++) {
                Pago pago = new Pago();
                pago.setActivo(true);
                pago.setFechaPago(fechaVenta.plusDays(p));
                pago.setMontoPagado(montoTotal.divide(BigDecimal.valueOf(pagosCount)));
                pago.setDni("12345678" + p);
                pago.setMarcaTarjeta("Visa");
                pago.setBanco("Banco Ejemplo");
                pago.setReferencia("REF" + UUID.randomUUID());
                pago.setDescuento(BigDecimal.ZERO);
                pago.setUltimos4("1234");
                pago.setMetodosPago((p % 2 == 0) ? MetodosPago.TARJETA_DEBITO : MetodosPago.TRANSFERENCIA);
                pago.setVentaRepuesto(venta);

                venta.getPagosList().add(pago);
            }

            session.persist(venta);
        }

        tx.commit();
        System.out.println("Se insertaron " + totalVentas + " ventas con fechas aleatorias en " + anio + " correctamente.");

    }

}
