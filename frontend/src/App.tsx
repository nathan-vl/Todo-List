import { useEffect, useState } from "react";
import "./App.css";

class Task {
  id: number;
  description: string;
  done: boolean;

  constructor(id: number, description: string, done: boolean) {
    this.id = id;
    this.description = description;
    this.done = done;
  }
}

function deleteTask(task: Task) {
  return fetch(`http://127.0.0.1:8080/task/${task.id}`, { method: "DELETE" });
}

function saveTask(task: Task) {
  return fetch('http://127.0.0.1:8080/task',
    {
      headers: { "Content-Type": "application/json" },
      method: "POST",
      body: JSON.stringify(task),
    });
}

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  function updateTasks() {
    fetch('http://127.0.0.1:8080/task')
      .then((res) => res.json())
      .then((res) => setTasks(res));
  }

  function deleteTaskAndUpdate(task: Task) {
    if (task.id !== -1) {
      deleteTask(task).then(() => updateTasks());
    }
  }

  function saveTaskAndUpdate(task: Task) {
    saveTask(task).then(() => updateTasks());
  }

  useEffect(updateTasks, [])

  const [editTaskId, setEditTaskId] = useState(-1);

  return (
    <div className="flex flex-col items-center p-4">
      <div className="flex flex-col items-center gap-4 w-full">
        <div className="flex gap-4">
          <h1 className="font-bold text-lg">To-do</h1>

          <button
            className="bg-blue-500 px-3 py-1 rounded-md shadow-sm text-white font-semibold"
            onClick={() => {
              const taskId = -1;
              setTasks([...tasks, new Task(taskId, "", false)]);
              setEditTaskId(taskId);
            }}>+</button>
        </div>

        <div className="flex flex-col gap-1 bg-neutral-300 px-2 py-3 rounded-lg w-full max-w-md">
          {tasks.length === 0 ? (
            <p className="text-center bg-white rounded shadow py-3">Não há tarefas cadastradas, que tal cadastrar uma agora?</p>
          ) : (tasks.map((task) => (
            <div
              key={task.id}
              className="flex flex-row gap-2 bg-white px-3 py-2 rounded shadow">
              <input
                type="checkbox"
                checked={task.done}
                onChange={(e) => {
                  setTasks(tasks.map((t) => (
                    t.id === task.id ? { ...t, done: e.target.checked } : t
                  )));
                  task.done = e.target.checked;
                  saveTaskAndUpdate(task)
                }}
              />

              <span className="break-all w-full">
                {task.id === editTaskId
                  ? (
                    <textarea
                      className="outline-none border-2 border-blue-500 resize-none"
                      autoFocus
                      value={task.description}
                      onChange={(e) => {
                        setTasks(tasks.map((t) => (
                          t.id === task.id ? { ...t, description: e.target.value } : t
                        )));
                      }}
                      onBlur={(e) => {
                        setEditTaskId(0);
                        if (e.target.value !== '') {
                          saveTaskAndUpdate(task);
                        } else if (task.id !== -1) {
                          deleteTaskAndUpdate(task)
                        } else {
                          setTasks(tasks.filter((t) => t.id !== task.id));
                        }
                      }}>
                    </textarea>
                  )
                  : (
                    <span
                      style={{ textDecoration: task.done ? 'line-through' : 'none' }}
                      onClick={() => setEditTaskId(task.id)}>
                      {task.description}
                    </span>)
                }
              </span>

              <button className="text-blue-500" onClick={() => deleteTaskAndUpdate(task)}>
                <i className="bi bi-trash"></i>
              </button>
            </div>
          )))}
        </div>
      </div>
    </div>
  );
}

export default App;
