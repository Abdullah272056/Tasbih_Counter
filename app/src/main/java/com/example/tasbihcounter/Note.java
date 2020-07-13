package com.example.tasbihcounter;

public class Note {
    int id;
    String zikirName,time;
          int  countValue;

    public Note(int countValue) {
        this.countValue = countValue;
    }

    public Note(int id, int countValue) {
        this.id = id;
        this.countValue = countValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(int id, String zikirName, String time, int countValue) {
        this.id = id;
        this.zikirName = zikirName;
        this.time = time;
        this.countValue = countValue;
    }

    public Note(int id, String zikirName, int countValue) {
        this.id = id;
        this.zikirName = zikirName;
        this.countValue = countValue;
    }

    public Note(String zikirName, String time, int countValue) {
        this.zikirName = zikirName;
        this.time = time;
        this.countValue = countValue;
    }
    public Note(String zikirName,int countValue) {
        this.zikirName = zikirName;
        this.countValue = countValue;

    }

    public String getZikirName() {
        return zikirName;
    }

    public void setZikirName(String zikirName) {
        this.zikirName = zikirName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }
}
