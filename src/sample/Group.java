package sample;

import sample.CustomShapes.CustomShape;

import java.util.ArrayList;

public class Group {
    ArrayList<CustomShape> members;
    public Group(){
        members = new ArrayList<>();
    }
    public void addGroupMember(CustomShape shape){
        members.add(shape);
    }
    public void removeGroupMember(CustomShape shape){
        members.remove(shape);
    }
    public ArrayList<CustomShape> getGroupMembers(){
        return this.members;
    }
}
