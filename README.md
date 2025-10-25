# TinyTasks - CRUDACTIVITY Project

This project is a simple task management application built with a Spring Boot backend and a native HTML, CSS, and JavaScript frontend. It allows users to create, view, toggle, and delete tasks.

## Project Structure

```
TinyTask/
 ├─ src/                     # Backend source code
 ├─ frontend/                # Frontend files
 │   ├─ index.html
 │   └─ app.js
 ├─ pom.xml                  # Maven configuration
 └─ README.md                # Project instructions
```

## Prerequisites

- **Java 21** (or a compatible JDK)
- **Apache Maven**
- An IDE that supports Maven projects (e.g., IntelliJ IDEA, VS Code with Java extensions, Eclipse).
- A modern web browser (e.g., Chrome, Firefox).
- A local web server for the frontend (e.g., VS Code's Live Server extension or Python).

---

## How to Run the Project

To use the application, you need to run the backend and frontend servers simultaneously.

### 1. Running the Backend (Spring Boot API)

1.  **Open the Project**: Open the root folder `TinyTask` in your IDE.

2.  **Load Maven Dependencies**: Your IDE should automatically detect the `pom.xml` file. Allow it to download and sync all the required dependencies. If you see errors like `Cannot resolve symbol 'springframework'`, manually trigger a Maven reload:
    *   **IntelliJ IDEA**: Go to the Maven tool window (View > Tool Windows > Maven) and click the "Reload All Maven Projects" button.
    *   **Eclipse**: Right-click the project > Maven > Update Project.
    *   **VS Code**: Open the command palette (`Ctrl+Shift+P`) and run `Maven: Reload Project`.

3.  **Run the Application**: Locate the main application file at `src/main/java/com/example/TinyTask/TinyTaskApplication.java`.
    - Right-click on the file and select **Run 'TinyTaskApplication.main()'**.

4.  **Verify**: The console will show that the Spring Boot application has started. The API is now running and listening on `http://localhost:8080`.

### 2. Running the Frontend

The frontend must be served by a local web server to avoid CORS issues with the browser.

#### Option A: Using VS Code Live Server (Recommended)

1.  **Open the Frontend Folder**: Open the `frontend` directory in Visual Studio Code.
2.  **Install Live Server**: If you don't have it, install the [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) extension.
3.  **Start the Server**: Right-click on the `index.html` file and select **Open with Live Server**.

Your browser will automatically open to an address like `http://127.0.0.1:5500`.

#### Option B: Using Python's HTTP Server

1.  **Open a Terminal**: Open your command line or terminal.
2.  **Navigate to the Directory**: Change your directory to the `frontend` folder:
    ```sh
    cd path/to/your/project/TinyTask/frontend
    ```
3.  **Run the Server**: Execute the following command:
    ```sh
    python -m http.server 5500
    ```
4.  **Open in Browser**: Manually open your web browser and go to `http://localhost:5500`.

---

## How to Use the Application

With both the backend and frontend running, you can now use TinyTasks:

1.  **View Tasks**: The main page will display the list of current tasks.
2.  **Add a Task**: Type a title (at least 3 characters) into the input field and click the "Add" button.
3.  **Mark a Task as Done/Undo**: Click the `Done` button to mark a task as complete (it will be struck through). Click `Undo` to revert it.
4.  **Delete a Task**: Click the `Delete` button to permanently remove a task from the list.
