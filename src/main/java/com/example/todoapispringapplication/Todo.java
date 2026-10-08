package com.example.todoapispringapplication;

public class Todo {
    private int id;
    private boolean completed;
    private String title;
    private int userId;

    public Todo (int id, boolean completed, String title, int userId) {
       this.id = id;
       this.completed = completed;
       this.title = title;
       this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String printTodo() {
        return "Todo id : " + id + " isCompleted : " + completed + " userId : " + userId + " title : " + title;
    }
}
