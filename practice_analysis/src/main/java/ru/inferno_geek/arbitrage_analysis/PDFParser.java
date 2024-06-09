package ru.inferno_geek.arbitrage_analysis;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class PDFParser {

    public void parsePDF(Path path) throws IOException {
        File file = new File(path.toString());
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        Path newTxt = Files.createFile(path.resolveSibling(path.getFileName() + ".txt"));
        Files.write(newTxt, text.getBytes());
        document.close();
    }
}

