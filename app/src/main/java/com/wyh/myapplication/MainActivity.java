package com.wyh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Message> list;

    RecyclerView recycler_chat;
    TextView tv_send;
    EditText ed_input;
    ChatRecyclerAdapter chatRecyclerAdapter;

    DaoMaster daoMaster;
    DaoSession daoSession;
    MessageDao messageDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMessageHistory();
        recycler_chat = findViewById(R.id.recycler_chat);
        tv_send = findViewById(R.id.tv_send);
        ed_input = findViewById(R.id.ed_input);

        list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(new ChatRecyclerAdapter.Message("消息" + i, i % 2));
//        }
        List<Message> queryResult = messageDao.queryBuilder().where(MessageDao.Properties.MessageNo.eq("mine")).build().list();
        if (queryResult != null)
            list.addAll(queryResult);
        chatRecyclerAdapter = new ChatRecyclerAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recycler_chat.setHasFixedSize(true);
        recycler_chat.setLayoutManager(linearLayoutManager);
        recycler_chat.setAdapter(chatRecyclerAdapter);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_input.getText() != null) {

                    Message message = new Message();
                    message.setContent(ed_input.getText().toString());
                    message.setMessageNo("mine");
                    message.setType(1);
                    messageDao.insert(message);
                    list.add(message);
                    chatRecyclerAdapter.notifyItemInserted(list.size() - 1);
                    recycler_chat.smoothScrollToPosition(list.size() - 1);
                }
            }
        });


    }


    private void getMessageHistory() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "cache-db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        messageDao = daoSession.getMessageDao();
    }
}