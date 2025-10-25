document.addEventListener('DOMContentLoaded', () => {
    const taskTitleInput = document.getElementById('task-title');
    const addTaskButton = document.getElementById('add-task');
    const taskList = document.getElementById('task-list');

    const API_URL = 'http://localhost:8080/api/todos';

    // Function to fetch and render tasks
    async function fetchAndRenderTasks() {
        try {
            const response = await fetch(API_URL);
            const tasks = await response.json();
            taskList.innerHTML = ''; // Clear existing tasks
            tasks.forEach(task => {
                renderTask(task);
            });
        } catch (error) {
            console.error('Error fetching tasks:', error);
        }
    }

    // Function to render a single task
    function renderTask(task) {
        const listItem = document.createElement('li');
        listItem.className = 'list-group-item d-flex justify-content-between align-items-center';
        listItem.dataset.id = task.id;

        const taskText = document.createElement('span');
        taskText.textContent = task.title;
        if (task.done) {
            taskText.classList.add('text-decoration-line-through');
        }

        const actionsDiv = document.createElement('div');

        const toggleButton = document.createElement('button');
        toggleButton.className = `btn btn-sm me-2 ${task.done ? 'btn-warning' : 'btn-success'}`;
        toggleButton.textContent = task.done ? 'Undo' : 'Done';
        toggleButton.addEventListener('click', () => toggleTask(task.id));

        const deleteButton = document.createElement('button');
        deleteButton.className = 'btn btn-danger btn-sm';
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', () => deleteTask(task.id));

        actionsDiv.appendChild(toggleButton);
        actionsDiv.appendChild(deleteButton);

        listItem.appendChild(taskText);
        listItem.appendChild(actionsDiv);
        taskList.appendChild(listItem);
    }

    // Function to add a new task
    addTaskButton.addEventListener('click', async () => {
        const title = taskTitleInput.value.trim();
        if (title.length < 3) {
            alert('Title must be at least 3 characters long.');
            return;
        }

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ title }),
            });

            if (response.ok) {
                taskTitleInput.value = '';
                fetchAndRenderTasks();
            } else {
                const errorData = await response.json();
                alert(`Error: ${errorData.error}`);
            }
        } catch (error) {
            console.error('Error adding task:', error);
            alert('Failed to add task.');
        }
    });

    // Function to toggle task status
    async function toggleTask(id) {
        try {
            const response = await fetch(`${API_URL}/${id}/toggle`, {
                method: 'PUT',
            });

            if (response.ok) {
                fetchAndRenderTasks();
            } else {
                const errorData = await response.json();
                alert(`Error: ${errorData.error}`);
            }
        } catch (error) {
            console.error('Error toggling task:', error);
            alert('Failed to toggle task.');
        }
    }

    // Function to delete a task
    async function deleteTask(id) {
        if (!confirm('Are you sure you want to delete this task?')) {
            return;
        }
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                fetchAndRenderTasks();
            } else {
                const errorData = await response.json();
                alert(`Error: ${errorData.error}`);
            }
        } catch (error) {
            console.error('Error deleting task:', error);
            alert('Failed to delete task.');
        }
    }

    // Initial fetch and render of tasks
    fetchAndRenderTasks();
});
