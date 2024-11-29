package fr.esir.omd.ci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Classe des tâches principales avec ses attributs */
public class Task {
    private static final Logger loger = LoggerFactory.getLogger(TaskManager.class);

    private String title;
    private String description;
    private boolean completed;

    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title == "") {
            loger.warn("Atteution, le titre est vide !");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription_(String description) {
        this.description = description;
        if (description == "") {
            loger.warn("Atteution, la description est vide !");
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
