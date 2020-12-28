package com.test.todoapplication.fragment;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.test.todoapplication.R;
import com.test.todoapplication.model.TodoData;
import com.test.todoapplication.viewmodel.ToDoViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddToDoFragment extends Fragment {

    private ToDoViewModel mViewModel;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private Calendar calendar;

    private AppCompatEditText appCompatEditTextDate, appCompatEditTextTodo;
    private AppCompatButton mButtonSave;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_do, container, false);
        appCompatEditTextDate = view.findViewById(R.id.et_date);
        appCompatEditTextTodo = view.findViewById(R.id.et_todo);
        mButtonSave = view.findViewById(R.id.btn_save);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendar = Calendar.getInstance();
        mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);

        appCompatEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        appCompatEditTextDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePicker();
                }
            }
        });

        mViewModel.enteredTodo.setValue(appCompatEditTextTodo.getText().toString().trim());

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = appCompatEditTextTodo.getText().toString().trim();
                String date = appCompatEditTextDate.getText().toString().trim();
                if (todo.isEmpty() && date.isEmpty()) {
                    Toast.makeText(getActivity(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    //save to db
                    TodoData todoData = new TodoData();
                    todoData.setTodo(todo);
                    todoData.setDate(date);
                    todoData.setStatus("Pending");
                    mViewModel.insert(todoData);
                    Toast.makeText(getActivity(), "Saved todo successfully", Toast.LENGTH_SHORT).show();
                    appCompatEditTextTodo.setText("");
                    appCompatEditTextDate.setText("");
                }
            }
        });
    }

    private void showDatePicker() {
        new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mViewModel.enteredDate.setValue(simpleDateFormat.format(calendar.getTime()));
            appCompatEditTextDate.setText(mViewModel.enteredDate.getValue());
        }
    };
}
