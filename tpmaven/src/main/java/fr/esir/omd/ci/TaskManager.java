package fr.esir.omd.ci;

import java.util.ArrayList;
import java.util.List;
//import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/** Gere les tâches je crois */
public class TaskManager {
    private List<Task> tasks;
    private static final Logger loger = LoggerFactory.getLogger(TaskManager.class);

    // Test code mort
    private int je_suis_une_variable_longue_et_inutile;

    private int fonctionDead() {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += "ORA ";
        }
        for (int i = 0; i < 10; i++) {
            a += "ORA ";
        }
        return 1;
    }

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /** Cette magnifique fonction sert à ajouter une tâche à au tableau de tâches */
    public void addTask(Task task) {
        tasks.add(task);
        loger.info("Nouvelle tâche ajoutée {}", task.getTitle());
    }

    /** Cette fonction permet de récupérer les tâches stockées */
    public List<Task> getTasks() {
        loger.debug("Récupération de la liste des {} tâches", tasks.size());
        return tasks;
    }

}
