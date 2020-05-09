package com.example.library

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ShowBookPlan : AppCompatActivity() {
    private lateinit var planList:MutableList<NewBookPlanModel>
    private lateinit var back: Button
    private lateinit var myRef:DatabaseReference
    private lateinit var listView: ListView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_book_plan)

        listView=findViewById(R.id.list)

        planList= mutableListOf()

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, StudentMainPage::class.java)
            startActivity(intent)
            finish()
        }


        myRef = FirebaseDatabase.getInstance().getReference("BookPlan") //at time to read value from database


        myRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    planList.clear()
                    for(i in p0.children){
                        val plan=i.getValue(NewBookPlanModel::class.java)
                        planList.add(plan!!)


                    }
                    val adapter=NewBookAdapter(this@ShowBookPlan, R.layout.new_book_plan_model,planList)
                    listView.adapter=adapter
                }
            }

        })
    }


}