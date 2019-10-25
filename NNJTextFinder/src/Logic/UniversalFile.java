/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Jose
 */
interface UniversalFile {
    
    public String read(TXT file)throws FileNotFoundException, IOException;

    public String read(DOCX file)throws FileNotFoundException, IOException;
    
    public String read(PDF file)throws FileNotFoundException, IOException;

    }
