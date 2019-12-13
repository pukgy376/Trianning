package com.it.trianning.home


import com.it.trianning.home.model.UserListModel

interface UserCallback {
    fun onSelectItem(userListModel: UserListModel?)
    fun onSelectItemLongClick(userListModel: UserListModel?)
}