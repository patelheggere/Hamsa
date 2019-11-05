package com.patelheggere.hamsa.executive.network;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.hamsa.executive.HamsaApplication;
import com.patelheggere.hamsa.executive.utils.SharedPrefsHelper;

import static com.patelheggere.hamsa.executive.utils.AppUtils.Constants.SELECTED_LANG;

public class FireBaseDataHandler {
    private DatabaseReference databaseReference;

    public FireBaseDataHandler() {
    }
    public DataSnapshot getNews(String path)
    {
        databaseReference = HamsaApplication.getFireBaseRef();
        String lang = SharedPrefsHelper.getInstance().get(SELECTED_LANG, "ka");

        databaseReference.child("path").child(lang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                   /* try {
                        NewsModel model = snapshot.getValue(NewsModel.class);
                        newsModelList.add(model);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }*/
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return null;
    }
}
