package com.test.todoapplication.fragment;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.todoapplication.R;
import com.test.todoapplication.adapter.TodoListAdapter;
import com.test.todoapplication.model.TodoData;
import com.test.todoapplication.viewmodel.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment {

    private ToDoViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private TodoListAdapter mTodoListAdapter;
    private ArrayList<TodoData> mTodoList;
    private AppCompatTextView mTextViewEmpty;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);
        mRecyclerView = view.findViewById(R.id.rv_todo);
        mTextViewEmpty = view.findViewById(R.id.tv_empty);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);

        mTodoList = new ArrayList<>();
        mTodoListAdapter = new TodoListAdapter(mTodoList, mViewModel);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mTodoListAdapter);

        LiveData<List<TodoData>> allTodo = mViewModel.getAllTodo();
        allTodo.observe(this, new Observer<List<TodoData>>() {
            @Override
            public void onChanged(List<TodoData> todoData) {
                mTodoList.clear();
                mTodoList.addAll(todoData);
                if (mTodoList.isEmpty()) {
                    mTextViewEmpty.setVisibility(View.VISIBLE);
                } else {
                    mTextViewEmpty.setVisibility(View.GONE);
                }
                mTodoListAdapter.notifyDataSetChanged();
            }
        });
    }
}
