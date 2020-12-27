package com.test.todoapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.test.todoapplication.db.AppDao;
import com.test.todoapplication.db.AppDatabase;
import com.test.todoapplication.model.TodoData;

import java.util.List;

public class ToDoRepository {

    private AppDao mAppDao;
    private LiveData<List<TodoData>> mAllToDoList;

    public ToDoRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mAppDao = db.appDao();
        mAllToDoList = mAppDao.getAllToDo();
    }

    public LiveData<List<TodoData>> getAllToDo() {
        return mAllToDoList;
    }


    public void insert (TodoData todoData) {
        new insertAsyncTask(mAppDao).execute(todoData);
    }

    private static class insertAsyncTask extends AsyncTask<TodoData, Void, Void> {

        private AppDao mAsyncTaskDao;

        insertAsyncTask(AppDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TodoData... params) {
            mAsyncTaskDao.insertTodo(params[0]);
            return null;
        }
    }

    public void delete (TodoData todoData) {
        new deleteAsyncTask(mAppDao).execute(todoData);
    }

    private static class deleteAsyncTask extends AsyncTask<TodoData, Void, Void> {

        private AppDao mAsyncTaskDao;

        deleteAsyncTask(AppDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TodoData... params) {
            mAsyncTaskDao.deleteTodo(params[0]);
            return null;
        }
    }
}