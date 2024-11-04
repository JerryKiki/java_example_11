package com.koreait;

import com.koreait.controller.TodoController;
import com.koreait.vo.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public void run() {
        TodoController todoController = new TodoController();
        Scanner sc = new Scanner(System.in);

        System.out.println("===todoApp 시작===");

        while (true) {

            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("종료 명령어를 입력하셨습니다.");
                System.out.println("프로그램이 곧 종료됩니다.");
                break;
            } else if (cmd.equals("list")) {
                ArrayList<Todo> todos = todoController.getTodoList();
                System.out.println("   번호   /   내용   ");
                System.out.println("======================");
//                    for (int i = todoList.size() - 1; i >= 0; i--) {
//                        Todo thisTodo = todoList.get(i);
//                    }
                //21에선 쓸 수 있는 새로운 문법
                todos.forEach(thisTodo -> System.out.printf("  %d  /  %s  \n", thisTodo.getId(), thisTodo.getBody()));
            } else if (cmd.equals("add")) {
                System.out.print("할일 : ");
                String body = sc.nextLine().trim();
                Todo newTodo = todoController.addTodo(body);
                System.out.printf("%d번 할 일이 생성되었습니다.\n", newTodo.getId());
            } else if (cmd.equals("del")) {
                System.out.print("삭제할 일의 번호 : ");
                int delId = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
                boolean rs = todoController.deleteTodo(delId);
                if (rs) {
                    System.out.printf("%d번 할 일이 삭제되었습니다.\n", delId);
                } else {
                    System.out.printf("%d번 할 일은 존재하지 않습니다.\n", delId);
                }
            } else if (cmd.equals("modify")) {
                System.out.print("수정할 할 일의 번호 : ");
                int modId = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
                Todo modTodo = todoController.getModifyTodo(modId);
                if (modTodo != null) {
                    System.out.printf("기존 할 일 : %s\n", modTodo.getBody());
                    System.out.print("새 할일 : ");
                    String newBody = sc.nextLine().trim();
                    todoController.modifyTodo(modTodo, newBody);
                    System.out.printf("%d번 할 일이 수정되었습니다.\n", modId);
                } else {
                    System.out.printf("%d번 할 일은 존재하지 않습니다.\n", modId);
                }
            } else {
                System.out.println("올바른 명령어를 입력하세요.");
            }
        }
    }
}
