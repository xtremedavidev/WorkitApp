package com.example.workit;

import java.io.Serializable;

public class exercise implements Serializable {

    private String instruction, description;
    private  int giflink, time;

    public exercise() {
    }

    public exercise(String instruction, String description, int giflink, int time) {
        this.instruction = instruction;
        this.description = description;
        this.giflink = giflink;
        this.time = time;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGiflink() {
        return giflink;
    }

    public void setGiflink(int giflink) {
        this.giflink = giflink;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
