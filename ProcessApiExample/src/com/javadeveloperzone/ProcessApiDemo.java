package com.javadeveloperzone;


import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nitin on 4/1/2017.
 */
public class ProcessApiDemo {

    private void printProcessId(){
        //ProcessHandle.current() method will return current process's ProcessHandle
        ProcessHandle currentProcessHandleImpl =  ProcessHandle.current();
        //java 9 ProcessHandleImpl class has static block, which look like below
        /*static {
            initNative();
            long pid = getCurrentPid0(); //it's native method
            current = new ProcessHandleImpl(pid, isAlive0(pid));
        }*/

        //getid method return current process Native PID
        System.out.println("Current Process Native PID : "+currentProcessHandleImpl.getPid());
    }
    private static final String NULL = "NULL";
    private void printProcessSnapShot(){
        ProcessHandle currentProcessHandleImpl =  ProcessHandle.current();
        ProcessHandle.Info processInfo = currentProcessHandleImpl.info();
        String [] defaults = new String[]{"-"};
        System.out.print("Start arguments : ");
        for(String argument : processInfo.arguments().orElse(defaults)){
            System.out.print(argument+" - ");
        }
        System.out.println("Command : "+processInfo.command().orElse(NULL));
        System.out.println("CommandLine : "+processInfo.commandLine().orElse(NULL));
        System.out.println("User : "+processInfo.user().orElse(NULL));
        System.out.println("Start Time : " + processInfo.startInstant().
                orElse(Instant.now()).toString());

    }


    public void printChildrenProcess(){
        ProcessHandle currentProcessHandleImpl =  ProcessHandle.current();
        try {
            Process notepadProcessOne = new ProcessBuilder("notepad.exe").start();
            Process notepadProcessTwo = new ProcessBuilder("notepad.exe").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ProcessHandle> childrenProcess = currentProcessHandleImpl.children().collect(Collectors.toList());
       int childrenCount=1;
       for(ProcessHandle processHandle : childrenProcess){
           System.out.println("Children process "+(childrenCount++) + " "+processHandle.getPid());
       }
    }

    public void printParentProcess(){
        try {
            Process notepadProcessOne = new ProcessBuilder("notepad.exe").start();
            ProcessHandle parenthandle = notepadProcessOne.toHandle().parent().get();
            System.out.println("Parent Process Native PID : "+parenthandle.getPid());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkProcessIsAlive(){
        try {
            Process notepadProcessOne = new ProcessBuilder("notepad.exe").start();
            ProcessHandle notepadProcessHandle = notepadProcessOne.toHandle();
            System.out.println("Notepad Process is running : "+notepadProcessHandle.isAlive());
            notepadProcessHandle.destroy();
            System.out.println("Notepad Process is running : "+notepadProcessHandle.isAlive());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void killProcess(){
        try {
            Process notepadProcessOne = new ProcessBuilder("notepad.exe").start();
            ProcessHandle notepadProcessHandle = notepadProcessOne.toHandle();
            System.out.println("Notepad Process is running : "+notepadProcessHandle.isAlive());
            boolean isKilled = notepadProcessHandle.destroy();
            if(isKilled){
                System.out.println("Notepad process killed by destroy() !!!");
            }
            System.out.println("Notepad Process is running : "+notepadProcessHandle.isAlive()+System.lineSeparator());

            notepadProcessOne = new ProcessBuilder("notepad.exe").start();
            notepadProcessHandle = notepadProcessOne.toHandle();
            System.out.println("Notepad Process is running : "+notepadProcessHandle.isAlive());
            isKilled = notepadProcessHandle.destroyForcibly();
            if(isKilled){
                System.out.println("Notepad process killed by destroyForcibly() !!!");
            }
            System.out.println("Notepad Process is running : "+notepadProcessHandle.isAlive()+System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProcessApiDemo apiDemo = new ProcessApiDemo();
        //apiDemo.printProcessSnapShot();
        //apiDemo.printChildrenProcess();
        //apiDemo.printParentProcess();
        //apiDemo.checkProcessIsAlive();
        apiDemo.killProcess();
    }
}
