package com.koreait.controller;

import com.koreait.vo.Todo;

import java.util.ArrayList;

public class TodoController {

    private ArrayList<Todo> todos = new ArrayList<>();
    private int lastId = 0;

    public Todo addTodo(String body) {
        int id = lastId + 1;
        Todo newTodo = new Todo(id, body);
        todos.add(newTodo);
        lastId++;
        return newTodo;
    }

    public boolean deleteTodo(int id) {

        //끝까지 순회하면서 대응하는 것을 모두 지운다. (우리는 id값이 고유하니까 그냥 쓰고있음!)
        //java 람다식 표현법에 대해 공부해보기. (요약하자면, 좀 생략해서 쓰자는 것)
        return todos.removeIf(todo -> todo.getId() == id);

//        for(Todo todo : todos) {
//            if(todo.getId() == id) {
//                todos.remove(todo);
//                return true;
//            }
//        }
//        return false;
    }

    public Todo getModifyTodo(int id) {

        Todo thisTodo = todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst() //맨 처음거 찾으면 stream은 끝내고 찾은 놈을 반환
                .orElse(null);

//        for(Todo todo : todos) {
//            if(todo.getId() == id) {
//                return todo;
//            }
//        }

        return thisTodo;
    }

    public void modifyTodo(Todo todo, String newBody) {
        todo.setBody(newBody);
    }

    public ArrayList<Todo> getTodoList() {
        return todos;
    }

    public int getLastId() {
        return lastId;
    }
}
