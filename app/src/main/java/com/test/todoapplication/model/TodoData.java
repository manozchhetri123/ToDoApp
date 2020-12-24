package com.test.todoapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class TodoData implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "todo")
    private String todo = "";

    @NonNull
    @ColumnInfo(name = "date")
    private String date = "";

    @NonNull
    @ColumnInfo(name = "status")
    private String status;

    public TodoData(){}

    public TodoData(Parcel in) {
        id = in.readInt();
        todo = in.readString();
        date = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(todo);
        dest.writeString(date);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TodoData> CREATOR = new Creator<TodoData>() {
        @Override
        public TodoData createFromParcel(Parcel in) {
            return new TodoData(in);
        }

        @Override
        public TodoData[] newArray(int size) {
            return new TodoData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
