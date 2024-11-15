package fr.esir.omd.ci;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        TaskManager manager = new TaskManager();
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);

        // Exemple simple pour ajouter une unique tâche
        System.out.println("Ajoutez une nouvelle tâche:");
        System.out.print("Titre: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Task task = new Task(title, description, false);
        manager.addTask(task);

        // Sauvegarder en JSON, CSV et générer un PDF
        try {
            fileHandler.saveAsJson(manager.getTasks(), "tasks.json");
            fileHandler.saveAsCsv(manager.getTasks(), "tasks.csv");
            fileHandler.generatePdf(manager.getTasks(), "tasks.pdf");
            System.out.println("Fichiers exportés avec succès!");
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
