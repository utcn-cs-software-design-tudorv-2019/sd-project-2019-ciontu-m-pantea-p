package com.paulpantea.chat.web.requests;

import java.util.List;

public class CreateConversationRequest {

    private String name;
    private List<String> members;

    public CreateConversationRequest(String name, List<String> members) {
        this.name = name;
        this.members = members;
    }

    public CreateConversationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}

