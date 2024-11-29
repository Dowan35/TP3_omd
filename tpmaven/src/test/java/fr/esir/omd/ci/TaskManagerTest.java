package fr.esir.omd.ci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour la classe TaskManager.
 */
public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        // Création d'une tâche fictive avec Mockito
        Task mockTask = mock(Task.class);
        when(mockTask.getTitle()).thenReturn("Tâche 1");

        // Ajout de la tâche
        taskManager.addTask(mockTask);

        // Vérifications
        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size(), "Le nombre de tâches doit être 1");
        assertEquals("Tâche 1", tasks.get(0).getTitle(), "Le titre de la tâche doit correspondre");
    }

    @Test
    public void testGetTasksWhenEmpty() {
        // Vérifier qu'aucune tâche n'est présente au départ
        List<Task> tasks = taskManager.getTasks();
        assertNotNull(tasks, "La liste des tâches ne doit pas être nulle");
        assertTrue(tasks.isEmpty(), "La liste des tâches doit être vide");
    }

    @Test
    public void testAddMultipleTasks() {
        // Création de plusieurs tâches fictives
        Task mockTask1 = mock(Task.class);
        Task mockTask2 = mock(Task.class);

        when(mockTask1.getTitle()).thenReturn("Tâche 1");
        when(mockTask2.getTitle()).thenReturn("Tâche 2");

        // Ajout des tâches
        taskManager.addTask(mockTask1);
        taskManager.addTask(mockTask2);

        // Vérifications
        List<Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size(), "Le nombre de tâches doit être 2");
        assertEquals("Tâche 1", tasks.get(0).getTitle());
        assertEquals("Tâche 2", tasks.get(1).getTitle());
    }
}
