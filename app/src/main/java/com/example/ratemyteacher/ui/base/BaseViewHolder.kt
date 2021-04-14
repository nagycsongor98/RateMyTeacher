package com.example.ratemyteacher.ui.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author  Csongor Nagy
 * @since  14.04.2021
 */

open class BaseViewHolder(parentView: ViewGroup, @LayoutRes resourceId: Int) :
    RecyclerView.ViewHolder(parentView.inflate(resourceId, false))