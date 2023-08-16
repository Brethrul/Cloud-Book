package com.example.demo.entity.request;

public class SharingRequest {
    private int doc_id;
    private int permission_type;
    private int target_user_id;

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(int permission_type) {
        this.permission_type = permission_type;
    }

    public int getTarget_user_id() {
        return target_user_id;
    }

    public void setTarget_user_id(int target_user_id) {
        this.target_user_id = target_user_id;
    }
}
