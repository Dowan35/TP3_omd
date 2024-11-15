package fr.esir.omd.ci;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);

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
