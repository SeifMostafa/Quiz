package com.seif.quiz;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.seif.quiz.model.Question;
import com.seif.quiz.model.QuestionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, Question> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareQuestions();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.listview_questions);

        // preparing list data
        prepareQuestions();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareQuestions() {
        listDataHeader = new ArrayList<>();

        listDataChild = new HashMap<>();
        // get questions from server
        String Q_title = "xmlns stands for?";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title, QuestionType.SINGLE_CHOICE,
                new int[]{3}, new String[]{"Extensible Markup Language Numerous",
                "Execution Model Language Numerous",
                "Extensible Markup Language Namespaces",
                "Extensible Markup Language Android Names"}));

        Q_title = "Order of views is matter in Android XML layouts?";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title
                , QuestionType.SINGLE_CHOICE, new int[]{0}, new String[]{"Yes", "No"}));

        Q_title ="What's R class?";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title, QuestionType.TEXT));

        Q_title ="Function signature includes ..";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title, QuestionType.MULTIPLE_CHOICE,
                new int[]{1, 2}, new String[]{"Function name", "Input parameters",
                "Return value", "Access modifiers"}));


        Q_title = "Are padding and margin affecting by the same way on a view?";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title, QuestionType.SINGLE_CHOICE
                , new int[]{0}, new String[]{"Yes", "No"}));

        Q_title = "What's Gradle?";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title, QuestionType.TEXT));

        Q_title = "Are Fragments defined inside Manifest?";
        listDataHeader.add(Q_title);
        listDataChild.put(Q_title,new Question(Q_title, QuestionType.SINGLE_CHOICE,
                new int[]{1}, new String[]{"Yes", "No"}));

    }

    public void submitAnswers(View view) {


    }
    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, Question> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, Question> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition));
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final Question question = (Question) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.layout_question, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.textview_question_title);

            txtListChild.setText(question.getTitle());
            switch (question.getType()) {

                case TEXT:
                    ListView listView_answers = convertView.findViewById(R.id.listview_answers);
                    listView_answers.setVisibility(View.INVISIBLE);
                    EditText editText_answer = convertView.findViewById(R.id.edittext_answer);
                    editText_answer.setVisibility(View.VISIBLE);
                    break;
                case MULTIPLE_CHOICE:
                    ListView listView_answers2 = convertView.findViewById(R.id.listview_answers);
                    listView_answers2.setVisibility(View.VISIBLE);
                    EditText editText_answer2 = convertView.findViewById(R.id.edittext_answer);
                    editText_answer2.setVisibility(View.INVISIBLE);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context,
                            android.R.layout.simple_list_item_1, android.R.id.text1, question.getAnswers());
                    listView_answers2.setAdapter(adapter);

                    break;
                case SINGLE_CHOICE:
                    ListView listView_answers3 = convertView.findViewById(R.id.listview_answers);
                    listView_answers3.setVisibility(View.VISIBLE);
                    EditText editText_answer3 = convertView.findViewById(R.id.edittext_answer);
                    editText_answer3.setVisibility(View.INVISIBLE);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(_context,
                            android.R.layout.simple_list_item_1, android.R.id.text1, question.getAnswers());
                    listView_answers3.setAdapter(adapter2);
            }
                    return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).getAnswers().length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.layout_expndedlstview, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
/*    private class QuestionsListAdapter extends BaseAdapter {

        Activity activity;
        LayoutInflater inflater;
        Question question;

        public QuestionsListAdapter(Activity a) {
            this.activity = a;
            inflater = (LayoutInflater) this.activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return questions.size();
        }

        @Override
        public Object getItem(int i) {
            return questions.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View vi = convertView;
            ViewHolder holder;

            if (convertView == null) {

                *//****** Inflate tabitem.xml file for each row ( Defined below ) *******//*
                vi = inflater.inflate(R.layout.layout_question, null);

                *//****** View Holder Object to contain tabitem.xml file elements ******//*

                holder = new ViewHolder();
                holder.prepare(vi);

                *//************  Set holder with LayoutInflater ************//*
                vi.setTag(holder);
            } else
                holder = (ViewHolder) vi.getTag();

            if (questions.size() <= 0) {
                holder.textView_title = null;
                holder.editText_answer = null;
                holder.listView_answers = null;

            } else {
                *//***** Get each Model object from Arraylist ********//*
                this.question = null;
                question = questions.get(position);

                *//************  Set Model values in Holder elements ***********//*

                holder.textView_title.setText(question.getTitle());
                switch (question.getType()) {

                    case TEXT:
                        holder.listView_answers.setVisibility(View.INVISIBLE);
                        holder.editText_answer.setVisibility(View.VISIBLE);
                        break;
                    case MULTIPLE_CHOICE:
                        holder.editText_answer.setVisibility(View.INVISIBLE);
                        holder.listView_answers.setVisibility(View.VISIBLE);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                                android.R.layout.simple_list_item_1, android.R.id.text1, question.getAnswers());
                        holder.listView_answers.setAdapter(adapter);

                        break;
                    case SINGLE_CHOICE:
                        holder.editText_answer.setVisibility(View.INVISIBLE);
                        holder.listView_answers.setVisibility(View.VISIBLE);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(activity,
                                android.R.layout.simple_list_item_1, android.R.id.text1, question.getAnswers());
                        holder.listView_answers.setAdapter(adapter2);

                        break;
                }
                //holder.imageView.setImageURI(Uri.parse(imageview_movie.getImageView()));
                *//******** Set Item Click Listner for LayoutInflater for each row *******//*

            }
            return vi;
        }

        public class ViewHolder {
            TextView textView_title;
            EditText editText_answer;
            ExpandableListView listView_answers;

            public void prepare(View view) {
                textView_title = (TextView) view.findViewById(R.id.textview_question_title);
                editText_answer = (EditText) view.findViewById(R.id.edittext_answer);
                listView_answers = (ExpandableListView) view.findViewById(R.id.listview_answers);
            }
        }
    }*/
}
