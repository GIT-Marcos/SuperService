package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author Usuario
 */
public class Impresor {

    public static void imprimirNotaRetiro(String rutaArchivo) {
        //TO-DO: ORDENAR TIPOS IMPRESORAS
    }

    /*PARA IMPRESORAS GENÉRICAS
//        //fis busca un archivo y abre una conexión
//        //doc es todo archivo que es posible imprimirlo
//        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
//            DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
//            PrintService impresora = PrintServiceLookup.lookupDefaultPrintService();
//            if(impresora == null){
//                System.out.println("No se encontró impresora predeterminada en el sistema.");
//                return;
//            }
//            
//            Doc doc = new SimpleDoc(fis, flavor, null);
//            DocPrintJob printJob = impresora.createPrintJob();
//            printJob.print(doc, null);
//            System.out.println("Archivo impreso exitosamente.");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
     */
 /*  PARA IMPRESORAS TÉRMICAS
        try {
            // Leer el archivo como byte[]
            byte[] datos = Files.readAllBytes(Path.of(rutaArchivo));

            // Usar un flavor genérico, confiable
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

            // Obtener impresora predeterminada
            PrintService impresora = PrintServiceLookup.lookupDefaultPrintService();
            if (impresora == null) {
                System.out.println("No se encontró impresora predeterminada.");
                return;
            }

            // Enviar trabajo de impresión
            DocPrintJob printJob = impresora.createPrintJob();
            Doc doc = new SimpleDoc(datos, flavor, null);
            printJob.print(doc, null);

            System.out.println("Impresión enviada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */

    //usa el SO para imprimir
    public static void imprimirConSistema(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                System.out.println("El archivo no existe.");
                return;
            }

            Desktop.getDesktop().print(archivo); // Envia a imprimir con el programa predeterminado
            System.out.println("Impresión enviada desde el sistema.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
