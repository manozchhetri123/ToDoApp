package com.test.todoapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.todoapplication.model.TodoData;
import com.test.todoapplication.repository.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    public MutableLiveData<String> enteredTodo = new MutableLiveData<>();
    public MutableLiveData<String> enteredDate = new MutableLiveData<>();

    private ToDoRepository mRepository;
    private LiveData<List<TodoData>> mAllTodo;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ToDoRepository(application);
        mAllTodo = mRepository.getAllToDo();
    }

    public LiveData<List<TodoData>> getAllTodo() {
        return mAllTodo;
    }

    public void insert(TodoData todoData) {
        mRepository.insert(todoData);
    }

    public void delete(TodoData todoData) {
        mRepository.delete(todoData);
    }
}

