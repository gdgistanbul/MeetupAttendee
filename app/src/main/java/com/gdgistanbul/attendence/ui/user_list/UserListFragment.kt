package com.gdgistanbul.attendence.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gdgistanbul.attendence.MainActivity
import com.gdgistanbul.attendence.R

/**
 * Created by Furkan on 10.07.2019
 */

class UserListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText((activity as MainActivity), getEventID().toString(), Toast.LENGTH_SHORT).show()
    }

    private fun getEventID(): Int {
        return UserListFragmentArgs.fromBundle(this.arguments!!).eventID
    }

}
