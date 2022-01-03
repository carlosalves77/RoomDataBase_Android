package com.app.roomdemo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.VERTICAL
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.roomdemo.db.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerview_row.*


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListner {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel:: class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setListDAta(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        btnSave.setOnClickListener{
           val name = firstName.text.toString()
            val email = emailInput.text.toString()

            if (btnSave.text.equals("SAVE")) {
                val user = UserEntity(0, name, email)
                viewModel.insertUserInfo(user)
            }
            else {
                val user = UserEntity(firstName.getTag(firstName.id).toString().toInt(), name, email)
                viewModel.updateUserInfo(user)
                btnSave.setText("SAVE")
            }
            firstName.setText("")
            emailInput.setText("")

            Toast.makeText(applicationContext, "Você salvou", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        firstName.setText(user.name)
        emailInput.setText(user.email)
        firstName.setTag(firstName.id, user.id)
        btnSave.setText("Update")
    }


}