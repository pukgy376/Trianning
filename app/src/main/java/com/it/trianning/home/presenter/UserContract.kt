package com.it.trianning.home.presenter


import com.it.trianning.common.BaseView
import com.it.trianning.home.model.UserListModel
import com.it.trianning.home.model.UserModel

class UserContract {
    interface Presenter {

        fun addDefaultEmail()

        fun loadDataFormFirebase()

        fun removeItemMember(userId: String)
    }

    interface View : BaseView {

        fun updateData(model: UserModel)

        fun updateList(model: List<UserListModel>)

    }
}