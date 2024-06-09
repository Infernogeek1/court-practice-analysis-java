package ru.inferno_geek.arbitrage_analysis;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.lang.RuntimeException;
import java.nio.file.Path;
import java.nio.file.Files;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.ooxml.POIXMLException;
public class DocxParser {

    public void parseDocx(Path path) throws IOException, RuntimeException, POIXMLException{
        
        InputStream docxFile = null;
        XWPFDocument doc = null;
        XWPFWordExtractor extractor = null;
        FileOutputStream out = null;
        Path newTxt = null;

        try 
        {
            docxFile = new FileInputStream(path.toString());
            doc = new XWPFDocument(docxFile);
            extractor = new XWPFWordExtractor(doc);
            String text = extractor.getText();
            System.out.println(text);
            newTxt = Files.createFile(path.resolveSibling(path.getFileName() + ".txt"));
            out = new FileOutputStream(newTxt.toFile());
            out.write(text.getBytes());
        } 

        catch (IOException | RuntimeException e)
        {
            e.printStackTrace();
        }

        finally 
        {
            docxFile.close();
            doc.close();
            extractor.close();
            out.close();
        }
    }
}
