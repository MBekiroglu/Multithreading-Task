package com.sparta.mb;

public class OtherWayToCreate extends Thread {

    public OtherWayToCreate() {
    }

    public OtherWayToCreate(Runnable target) {
        super(target);
    }

    public OtherWayToCreate(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public OtherWayToCreate(String name) {
        super(name);
    }

    public OtherWayToCreate(ThreadGroup group, String name) {
        super(group, name);
    }

    public OtherWayToCreate(Runnable target, String name) {
        super(target, name);
    }

    public OtherWayToCreate(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public OtherWayToCreate(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    public OtherWayToCreate(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
        super(group, target, name, stackSize, inheritThreadLocals);
    }

}
