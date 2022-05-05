package com.team3.forcemajeure.util;

import java.util.Map;

public class NPC {
    private String name;
    private String description;
    private Map<String,String> dialogue;
    private Map<String,String> endings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getDialogue() {
        return dialogue;
    }

    public void setDialogue(Map<String, String> dialogue) {
        this.dialogue = dialogue;
    }

    public Map<String, String> getEndings() {
        return endings;
    }

    public void setEndings(Map<String, String> endings) {
        this.endings = endings;
    }

    public void speak() {
        System.out.println("Hello I am " + this.getName());
    }
}