package remi.renard.singleactivitysample.ui.home.users

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import remi.renard.singleactivitysample.R
import remi.renard.singleactivitysample.domain.model.User
import remi.renard.singleactivitysample.extensions.inflate

class UsersAdapter : ListAdapter<User, UsersAdapter.ItemViewHolder>(object :
    DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id && oldItem.login == oldItem.login
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_user))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val userLogin: TextView = itemView.findViewById(R.id.item_user_login)

        fun bind(user: User) {
            userLogin.text = user.login
        }
    }
}
