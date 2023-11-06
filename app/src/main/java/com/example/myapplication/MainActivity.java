package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RadioButton tools;
    RadioButton employees;
    RadioButton notes;
    TextView textView;
    Button search;


    private ToolV2 getTool(JSONObject toolJSON) {
        ToolV2 result = null;
        try {
            result = new ToolV2(toolJSON.getString("idCode"),
                    toolJSON.getString("name"), toolJSON.getString("manufactured"),
                    toolJSON.getBoolean("sharpen"), toolJSON.getBoolean("good"), ToolType.valueOf(toolJSON.getString("type")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private EmployeeV2 getEmployee (JSONObject employeeJSON) throws JSONException {
        EmployeeV2 result = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            result =  new EmployeeV2(employeeJSON.getInt("id"), employeeJSON.getString("name"),
                     employeeJSON.getString("lastname"), employeeJSON.getString("patronymic"),
                     LocalDate.parse(employeeJSON.getString("joinDate")),
                     Department.valueOf(employeeJSON.getString("department")),
                     EmployeeType.valueOf(employeeJSON.getString("type")));
        }
        return result;

    }



    class APIQueryTools extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            URL urlTools = NetworkUtils.generateURLTools();
            try {
                response = NetworkUtils.getResponseFromUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            List<ToolV2> tools = new ArrayList<>();
            try {
                JSONArray toolsArray = new JSONArray(response);
                for (int i = 0; i < toolsArray.length(); i++) {
                    JSONObject toolJSON = toolsArray.getJSONObject(i);
                    tools.add(getTool(toolJSON));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            textView.setText("Начнем Движ!!!\n");
            for (ToolV2 tool : tools) {
                textView.append(tool.toString());
                textView.append("\n" + "\n");

            }
        }
    }

    class APIQueryEmployees extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            URL urlTools = NetworkUtils.generateURLTools();
            try {
                response = NetworkUtils.getResponseFromUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            List<EmployeeV2> employees = new ArrayList<>();
            try {
                JSONArray employeesArray = new JSONArray(response);
                for (int i = 0; i < employeesArray.length(); i++) {
                    JSONObject employeeJSON = employeesArray.getJSONObject(i);
                        employees.add(getEmployee(employeeJSON));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            textView.setText("Начнем Движ!!!\n");
            for (EmployeeV2 tool : employees) {
                textView.append(tool.toString());
                textView.append("\n" + "\n");

            }
        }
    }

    class APIQueryNotes extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            URL urlTools = NetworkUtils.generateURLTools();
            try {
                response = NetworkUtils.getResponseFromUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            List<NoteToolAndAmountV2> notes = new ArrayList<>();
            try {
                JSONArray notesArray = new JSONArray(response);
                for (int i = 0; i < notesArray.length(); i++) {
                    JSONObject noteJSON = notesArray.getJSONObject(i);
                        notes.add(new NoteToolAndAmountV2(noteJSON.getLong("id"),
                               getEmployee( noteJSON.getJSONObject("employeesId")),
                                getTool(noteJSON.getJSONObject("toolsId")),
                                noteJSON.getInt("amount")));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            textView.setText("Начнем Движ!!!\n");
            for (NoteToolAndAmountV2 note : notes) {
                textView.append(note.toString());
                textView.append("\n" + "\n");

            }
        }
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tools = findViewById(R.id.toolsButton);
        employees = findViewById(R.id.employeesButton);
        notes = findViewById(R.id.notesButton);
        search = findViewById(R.id.search);
        textView = findViewById(R.id.text);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tools.isChecked()) {

                    URL urlTools = NetworkUtils.generateURLTools();
                    new APIQueryTools().execute(urlTools);

                } else if (employees.isChecked()) {
                    URL urlEmployees = NetworkUtils.generateURLEmployees();
                    new APIQueryEmployees().execute(urlEmployees);
                } else if (notes.isChecked()) {
                    URL urlNotes = NetworkUtils.generateURLNotes();
                    new APIQueryNotes().execute(urlNotes);
                }
            }
        });


    }
}