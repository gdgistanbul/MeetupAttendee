package com.gdgistanbul.attendence.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.toast

class UserListFragment : Fragment() {

    private val args: UserListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventID = args.eventID
        // TODO - Just for testing safeArgs
        toast(eventID.toString())
    }


}
