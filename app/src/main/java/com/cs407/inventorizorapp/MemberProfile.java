package com.cs407.inventorizorapp;

import java.io.Serializable;

public class MemberProfile implements Serializable{
    private String memberName;
    private String memberPosition;
    private String memberPhone;

    public MemberProfile(String memberName, String memberPosition, String memberPhone) {
        this.memberName = memberName;
        this.memberPosition = memberPosition;
        this.memberPhone = memberPhone;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberPosition() {
        return memberPosition;
    }

    public String getMemberPhone() {
        return memberPhone;
    }
    public void setMemberName(String name) {
        this.memberName = name;
    }
    public void setMemberPosition(String position) {
        this.memberPosition = position;
    }
    public void setMemberPhone(String phone) {
        this.memberPhone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + memberName + "\nPosition: " + memberPosition;
    }
}
