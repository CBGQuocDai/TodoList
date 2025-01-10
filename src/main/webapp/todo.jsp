<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task List</title>
    <link rel="stylesheet" href="./css/todo.css">
</head>
<body>
    <div class="header">
        <a href="/TodoList_war_exploded/changepassword"> change password</a>
        <a href="/TodoList_war_exploded/logout"> Logout</a>
    </div>
    <div class="body">
        <h1>add task</h1>
        <form method="POST" action="/TodoList_war_exploded/t">
            <input type="text" placeholder="enter the task name" name="task">
            <button type="submit">add</button>
        </form>
        <h1>List task</h1>
        <c:if test="${not empty tasks}">
            <ul>
                <c:forEach items="${tasks}" var="task">
                    <li id="task_<c:out value="${task.id}"/>">
                        <input style="border: none" id =<c:out value="${task.id}"/> type="text" value = "<c:out value="${task.name}"/>" readonly>
                        <button class="done" id="done<c:out value="${task.id}"/>" onclick="deleteTask('<c:out value="${task.id}"/>')"> done</button>
                        <button class="edit" id="edit<c:out value="${task.id}"/>" onclick="editTask('<c:out value="${task.id}"/>',this)"> edit</button>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

    </div>
    <script>

    </script>
    <script>
        function editTask(inputId, button) {
            const taskInput = document.getElementById(inputId);
            taskInput.removeAttribute('readonly');
            taskInput.focus();
            // Change the edit button to a save button
            button.textContent = 'save';
            button.setAttribute('onclick', `saveTask('${inputId}', this)`);

            // Add event listener for Enter key
            taskInput.addEventListener('keydown', function(event) {
                if (event.key === 'Enter') {
                    saveTask(inputId, button);
                }
            });
        }
        function saveTask(inputId, button) {
            const taskInput = document.getElementById(inputId);
            taskInput.setAttribute('readonly', true);
            const data = {
                id: inputId,
                name: taskInput.value,
                status:"process"
            }
            fetch('http://localhost:8080/TodoList_war_exploded/t',{
                method: 'PUT',
                headers: {
                    'content-type':'application/json'
                },
                body: JSON.stringify(data)
            }).then()
            // Change the save button back to an edit button
            button.textContent = 'edit';
            button.setAttribute('onclick', `editTask('${inputId}', this)`);
        }
        function deleteTask(inputId) {
            let task = document.getElementById('task_'+inputId);
            task.innerHTML = '';
            const data = {
                id: inputId,
                name: 'abc',
                status:"process"
            }
            fetch('http://localhost:8080/TodoList_war_exploded/t',{
                method: 'DELETE',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify(data)
            })
        }
    </script>
    <script>
        const url = new URL (window.location.href)
        const params = new URLSearchParams(url.search);
        const message = params.get('message')
        const err= document.getElementById('err')
        if(message){
            alert(message)
        }
    </script>
</body>
</html>
