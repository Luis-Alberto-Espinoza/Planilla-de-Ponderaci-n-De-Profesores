package iesmb.pp3.planillaProfesores.controller;

import java.util.List;

public class APIResponse <T>{

	private int status;
    private List <String> messages;
    private T data;

    public APIResponse(int status, List <String> messages, T data) {
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public List <String> getMessage() {
        return messages;
    }

    public T getData() {
        return data;
    }
}
