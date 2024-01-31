/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.*;

/**
 *
 * @author Martín
 */
public class ControlFrame {

    
    private static ControlFrame control; 
    
    private ControlFrame() {
        
        MainFrame.getInstance().setVisible(true);
    }
    
    
    public static ControlFrame getInstance(){
        if (control == null) {
            control = new ControlFrame();
        }
        return control;
    }
    
    public void openFile(){
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JSON", "json");
        selectorArchivos.setFileFilter(filtro);
        int resultado = selectorArchivos.showOpenDialog(MainFrame.getInstance());
        if(resultado==0){
            String path = selectorArchivos.getSelectedFile().getAbsolutePath();
            if(path.contains(".json")){
                int iiii = path.lastIndexOf("\\");
                String title = path.substring(iiii);
                System.out.println(title);
                ControlPanel control = new ControlPanel(title, path);
                }
            else{
                System.out.println("No se pudo abrir el archivo.");
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "No se pudo abrir el archivo.", "---", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void openFileDefault(){
        String nombreArchivo = "/2022_APRIL.json";
        // Obtener la ruta del directorio "src"
        String directorioSrc = System.getProperty("user.dir") + File.separator + "src";
        // Combinar la ruta del directorio "src" con el nombre del archivo
        String rutaAbsoluta = directorioSrc + File.separator + nombreArchivo;
        ControlPanel control = new ControlPanel("PRUEBA", rutaAbsoluta);
    }
    
}
