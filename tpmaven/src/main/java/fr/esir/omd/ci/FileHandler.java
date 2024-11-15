package fr.esir.omd.ci;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class FileHandler {
    public void saveAsJson(List<Task> tasks, String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(tasks, writer);
        } catch (Exception e) {
            loger.error("Impossible de sauvegarder les taches en jason.");
        }
    }

    public void saveAsCsv(List<Task> tasks, String filePath) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath),
                CSVFormat.DEFAULT.builder().setHeader("Title", "Description", "Completed").build())) {
            for (Task task : tasks) {
                printer.printRecord(task.getTitle(), task.getDescription(), task.isCompleted());
            }
        } catch (Exception e) {
            loger.error("Impossible de sauvegarder les taches en csv.");
        }
    }

    public void generatePdf(List<Task> tasks, String filePath) throws DocumentException, IOException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            for (Task task : tasks) {
                document.add(new Paragraph("Title: " + task.getTitle()));
                document.add(new Paragraph("Description: " + task.getDescription()));
                document.add(new Paragraph("Completed: " + task.isCompleted()));
                document.add(new Paragraph("\n"));
            }
        } catch (Exception e) {
            loger.error("Impossible de sauvegarder les taches en pdf.");
        }
        document.close();
    }
}
