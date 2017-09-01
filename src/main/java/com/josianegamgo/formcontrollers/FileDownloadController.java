/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author Josiane Gamgo
 */



 
@Named("filedownloadcontroller")
@SessionScoped
public class FileDownloadController implements Serializable{
    
     
    private final StreamedContent file;
     
    public FileDownloadController() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/file/Alice_in_Wonderland.pdf");
        file = new DefaultStreamedContent(stream, "file/pdf", "Alice_in_Wonderland.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}