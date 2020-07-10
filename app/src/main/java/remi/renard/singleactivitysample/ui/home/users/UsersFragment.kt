package remi.renard.singleactivitysample.ui.home.users

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import remi.renard.singleactivitysample.R
import remi.renard.singleactivitysample.di.Injectable
import remi.renard.singleactivitysample.domain.model.User
import remi.renard.singleactivitysample.extensions.observeResource
import remi.renard.singleactivitysample.ui.home.HomeViewModel
import javax.inject.Inject

class UsersFragment : Fragment(R.layout.fragment_users), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val activityViewModel by activityViewModels<HomeViewModel> { viewModelFactory }
    private val viewModel by viewModels<UsersViewModel> { viewModelFactory }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.fragment_users_recycler_view)
        progressBar = view.findViewById(R.id.fragment_users_progress_bar)
        setUpAdapter()
        Log.i("Parent viewModel msg : ", activityViewModel.getMessageFromHomeActivity())
        observeResource(viewModel.users, ::hideLoading, ::showLoading, ::onSuccess, ::onError)
        viewModel.getUsers()
    }

    private fun setUpAdapter() {
        adapter = UsersAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun showUsers(users: List<User>) {
        adapter.submitList(users)
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun onSuccess(users: List<User>?) {
        users?.let {
            showUsers(it)
        }
    }

    private fun onError(users: List<User>?, errorMessage: String) {
        users?.let {
            showUsers(it)
        }
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}
