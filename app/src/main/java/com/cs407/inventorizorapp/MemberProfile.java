package com.cs407.inventorizorapp;

public class MemberProfile {
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
}
