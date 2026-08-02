package com.tacuc.learningenglishweb.entity;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

public class Classroom {
    protected UID classroomId;
    protected String name;
    protected List<User> userList = new ArrayList<>();
    public Classroom(UID classroomId, String name, List<User> userList){
        this.classroomId = classroomId;
        this.name = name;
        this.userList = userList;
    }
    public Classroom(UID classroomId, String name) {
        this.classroomId = classroomId;
        this.name = name;
    }

    public UID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UID classroomId) {
        this.classroomId = classroomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
