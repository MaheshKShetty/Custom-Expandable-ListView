package com.mshetty.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

class FaqAdapter(private var faqHeaderData : List<String> , private var hashMapData : HashMap<String , List<String>>) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition : Int , childPosition : Int) : Any? {
        return hashMapData[faqHeaderData[groupPosition]]?.get(childPosition)
    }

    override fun getChildId(groupPosition : Int , childPosition : Int) : Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition : Int , childPosition : Int , isLastChild : Boolean ,
        convertView : View? , parent : ViewGroup?) : View? {
        val view :View ?= LayoutInflater.from(parent?.context)
            .inflate(R.layout.layout_child_faq , parent , false)
        val title = view?.findViewById<AppCompatTextView>(R.id.tvFaq)
        title?.text = getChild(groupPosition , childPosition) as String
        return view
    }

    override fun getChildrenCount(groupPosition : Int) : Int {
        return hashMapData[faqHeaderData[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition : Int) : Any {
        return faqHeaderData[groupPosition]
    }

    override fun getGroupCount() : Int {
        return faqHeaderData.size
    }

    override fun getGroupId(groupPosition : Int) : Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition : Int , isExpanded : Boolean , convertView : View? ,
        parent : ViewGroup?) : View? {
        val view : View ? = LayoutInflater.from(parent?.context)
            .inflate(R.layout.layout_group_faq , parent , false)
        val title = view?.findViewById<AppCompatTextView>(R.id.tvFaqHeader)
        val textColor = if (isExpanded) parent?.context?.let { ContextCompat.getColor(it ,R.color.faq_group_text_color_purple_blue) } else
            parent?.context?.let {
            ContextCompat.getColor(it ,R.color.faq_group_text_color_black_87)
        }
        if (textColor != null) {
            title?.setTextColor(textColor)
        }
        title?.text = getGroup(groupPosition) as String
        view?.findViewById<AppCompatImageView>(R.id.imgArrow)?.isSelected = isExpanded
        return view
    }

    override fun isChildSelectable(groupPosition : Int , childPosition : Int) : Boolean {
        return true
    }

    override fun hasStableIds() : Boolean {
        return false
    }
}