package util;

import entities.Repuesto;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Usuario
 */
public class ExportadorCSV {

    public static void exportarRepuestosXLSX(List<Repuesto> listaRepuestos, String rutaArchivo) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Repuestos");
        
        // Estilo encabezado
        CellStyle estiloEncabezado = workbook.createCellStyle();
        Font fuenteNegrita = workbook.createFont();
        fuenteNegrita.setBold(true);
        estiloEncabezado.setFont(fuenteNegrita);
        
        // Crear encabezado
        String[] columnas = {
            "COD BARRA", "DETALLE", "MARCA", "PRECIO", "CANTIDAD STOCK", "STOCK MÍNIMO"
        };
        
        Row filaEncabezado = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            Cell celda = filaEncabezado.createCell(i);
            celda.setCellValue(columnas[i]);
            celda.setCellStyle(estiloEncabezado);
        }
        
        // Formato para precio
        CellStyle estiloMoneda = workbook.createCellStyle();
        DataFormat formato = workbook.createDataFormat();
        estiloMoneda.setDataFormat(formato.getFormat("#,##0.00"));
        
        // Rellenar datos
        int rowNum = 1;
        for (Repuesto r : listaRepuestos) {
            Row fila = sheet.createRow(rowNum++);
            fila.createCell(0).setCellValue(r.getCodBarra());
            fila.createCell(1).setCellValue(r.getDetalle());
            fila.createCell(2).setCellValue(r.getMarca());

            Cell celdaPrecio = fila.createCell(3);
            celdaPrecio.setCellValue(String.valueOf(r.getPrecio()));
            celdaPrecio.setCellStyle(estiloMoneda);

            fila.createCell(4).setCellValue(r.getStock().getCantidad());
            fila.createCell(5).setCellValue(r.getStock().getCantMinima());
        }
        
        // Autoajustar columnas
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Guardar archivo
        try (FileOutputStream salida = new FileOutputStream(rutaArchivo)) {
            workbook.write(salida);
            workbook.close();
            JOptionPane.showMessageDialog(null, "Archivo Excel creado exitosamente en:\n" + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * ***********************************************************************************
     */
    
    public static void exportarRepuestosCSV(List<Repuesto> listaRepuestos, String ruta) {
        try (Writer w = new OutputStreamWriter(
                new FileOutputStream(ruta), StandardCharsets.UTF_8)) {
            //escribe encavezado
            w.write("COD BARRA;DETALLE;MARCA;PRECIO;CANTIDAD STOCK;STOCK MÍNIMO\n");
            //escribe repuestos
            for (Repuesto r : listaRepuestos) {
                w.write(escapaeCSV(r.getCodBarra()) + ";"
                        + escapaeCSV(r.getDetalle()) + ";"
                        + escapaeCSV(r.getMarca()) + ";"
                        + String.valueOf(r.getPrecio()) + ";"
                        + r.getStock().getCantidad() + ";"
                        + r.getStock().getCantMinima() + "\n"
                );
            }
            JOptionPane.showMessageDialog(null, "Archivo Excel creado exitosamente en:\n" + ruta);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
        }
    }

    //previene errores en valores que tengan comas, comillas, etc.
    private static String escapaeCSV(String valor) {
        if (valor == null) {
            return "";
        }
        if (valor.contains(",") || valor.contains("\"") || valor.contains("\n")) {
            valor.replace("\"", "\"\"");
            return "\"" + valor + "\"";

        }
        return valor;
    }

}
