package com.test.todoapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.test.todoapplication.R;
import com.test.todoapplication.model.TodoData;
import com.test.todoapplication.view.UpdateTaskActivity;
import com.test.todoapplication.viewmodel.ToDoViewModel;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private ArrayList<TodoData> todoDataList;
    private ToDoViewModel toDoViewModel;
    private Context context;

    public TodoListAdapter(ArrayList<TodoData> todoDataList,
                           ToDoViewModel toDoViewModel) {
        this.todoDataList = todoDataList;
        this.toDoViewModel = toDoViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_pending, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TodoData todoData = todoDataList.get(position);
        holder.mTodo.setText("On " + todoData.getDate() + "\n" + todoData.getTodo());
        if (todoData.getStatus().equals("Pending")) {
            holder.mCheckBox.setChecked(false);
        } else if (todoData.getStatus().equals("Done")) {
            holder.mCheckBox.setChecked(true);
        }
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoData todoData1 = new TodoData();
                todoData1.setId(todoData.getId());
                todoData1.setTodo(todoData.getTodo());
                todoData1.setDate(todoData.getDate());
                todoData1.setStatus("Done");
                toDoViewModel.insert(todoData1);
                Toast.makeText(context, "Task completed", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete todo")
                        .setMessage("Are you sure you want to delete this todo from the list?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                toDoViewModel.delete(todoData);
                                Toast.makeText(context, "Todo item deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
        holder.mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTaskActivity.class);
                intent.putExtra("todo", todoData);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mCheckBox;
        public TextView mTodo;
        public AppCompatImageButton mDeleteBtn, mEditBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mCheckBox = itemView.findViewById(R.id.item_checkbox_pending);
            this.mTodo = itemView.findViewById(R.id.item_tv_todo);
            mDeleteBtn = itemView.findViewById(R.id.btn_delete);
            mEditBtn = itemView.findViewById(R.id.btn_edit);
        }
    }
}