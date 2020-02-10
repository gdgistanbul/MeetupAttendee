package com.gdgistanbul.attendence.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.content.getSystemService
import androidx.core.widget.doOnTextChanged
import com.gdgistanbul.attendence.R
import kotlinx.android.synthetic.main.search_view.view.*

class CustomSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val view = View.inflate(context, R.layout.search_view, this)

    var onQueryTextChange: (newText: String) -> Unit = {}
    var onQueryTextSubmit: (query: String) -> Unit = {}

    init {
        initAttrs(context.obtainStyledAttributes(attrs, R.styleable.CustomSearchView))
        initViews()
    }

    private fun initAttrs(attrs: TypedArray) {
        try {
            view.searchEditText.hint = attrs.getString(R.styleable.CustomSearchView_hint)
        } finally {
            attrs.recycle()
        }
    }

    private fun initViews() {
        with(view.searchEditText) {
            doOnTextChanged { text, _, _, _ ->
                text?.let { onQueryTextChange(it.toString()) }
            }

            setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        clearFocus()
                        context.getSystemService<InputMethodManager>()
                            ?.hideSoftInputFromWindow(windowToken, 0)
                        onQueryTextSubmit(text.toString())
                        true
                    }
                    else -> false
                }
            }
        }
    }
}