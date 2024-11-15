package fr.esir.omd.ci;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskController {
    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button saveJsonButton;
    @FXML
    private Button saveCsvButton;
    @FXML
    private Button savePdfButton;

    private TaskManager manager;
    private FileHandler fileHandler;

    public TaskController() {
        manager = new TaskManager();
        fileHandler = new FileHandler();
    }

    public void initialize() {
        saveJsonButton.setOnAction(e -> saveTask("json"));
        saveCsvButton.setOnAction(e -> saveTask("csv"));
        savePdfButton.setOnAction(e -> saveTask("pdf"));
    }

    /** Fonction principale qui gère les cas pour sauvegarder les tâches */
    private void saveTask(String format) {
        String title = titleField.getText();
        String description = descriptionField.getText();

        if (title.isEmpty() || description.isEmpty()) {
            System.out.println("Les champs Titre et Description doivent être remplis.");
            return;
        }

        Task task = new Task(title, description, false);
        manager.addTask(task);

        try {
            switch (format) {
                case "json":
                    fileHandler.saveAsJson(manager.getTasks(), "tasks.json");
                    break;
                case "csv":
                    fileHandler.saveAsCsv(manager.getTasks(), "tasks.csv");
                    break;
                case "pdf":
                    fileHandler.generatePdf(manager.getTasks(), "tasks.pdf");
                    break;
                default:
                    System.out.println("Format non supporté : " + format);
                    loger.error("Format non supporté : " + format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
