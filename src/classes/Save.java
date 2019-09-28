package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class Save{
    private String path;
    public Save(){
        
    }

    public void salvar(String path, Dados d) throws IOException{
        File arquivo = new File(path);
        if (!arquivo.exists()) 
            arquivo.createNewFile();
        
        FileOutputStream fos = new FileOutputStream(arquivo);
        
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(d);
        oos.close();
        
    }
    
    public boolean exists(String path) {
        this.path = path;
        File arquivo = new File(path);
        return arquivo.exists();  
    }
    
    public Object recordar(){
        File arquivo = new File(path);
        Object o  = null;
        try{
            FileInputStream fis = new FileInputStream(arquivo);

            ObjectInputStream ois = new ObjectInputStream(fis);

            o = ois.readObject();

            ois.close();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
    
    
}
