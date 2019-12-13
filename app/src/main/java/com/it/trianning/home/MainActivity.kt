package com.it.trianning.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.it.trianning.R
import com.it.trianning.addmember.AddMemberActivity
import com.it.trianning.common.BaseActivity
import com.it.trianning.home.adapter.UserAdapter
import com.it.trianning.home.model.UserListModel
import com.it.trianning.home.model.UserModel
import com.it.trianning.home.presenter.UserContract
import com.it.trianning.home.presenter.UserPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), UserContract.View {

    companion object {
        const val REQUEST_CODE = 999
    }
    private lateinit var presenter: UserPresenterImpl
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //การสร้าง
        presenter = UserPresenterImpl(this@MainActivity)
        presenter.addDefaultEmail()
//        presenter.loadDataFormFirebase()
        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.loadDataFormFirebase()
        }

    }

    override fun updateData(model: UserModel) {
        tv_email?.text = model.email
        userAdapter = UserAdapter(model.userList, object : UserCallback {
            override fun onSelectItem(userListModel: UserListModel?) {
                startActivityForResult(
                    Intent(AddMemberActivity.getStartIntent(this@MainActivity, userListModel)),
                    REQUEST_CODE
                )
            }

            override fun onSelectItemLongClick(userListModel: UserListModel?) {
                presenter.removeItemMember(userListModel?.idUser ?: "")
            }
        })

        rv_user.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    override fun updateList(model: List<UserListModel>) {
        userAdapter.updateDataUserList(model)
    }

    private fun initView() {
        btn_add_member_firebase.setOnClickListener {
            presenter.loadDataFormFirebase()
        }

        btn_add_member_data.setOnClickListener {
            startActivityForResult(
                Intent(this, AddMemberActivity::class.java), REQUEST_CODE
            )
        }
    }

}