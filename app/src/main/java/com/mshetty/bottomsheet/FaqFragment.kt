package com.mshetty.bottomsheet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mshetty.bottomsheet.databinding.LayoutFaqBinding

class FaqFragment : Fragment() , ExpandableListView.OnGroupExpandListener {

    private var viewType : String? = null
    private var previousGroupPosition : Int = 0
    private lateinit var binding: LayoutFaqBinding

    companion object {
        fun newInstance() : FaqFragment {
            val fragment = FaqFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?) : View {
        binding = LayoutFaqBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        initView()
        initExpandableListView()
    }

    private fun initView() {
        val param = binding.expandableListView.layoutParams as ConstraintLayout.LayoutParams
        val px8 = context?.let { pxFromDp(it , 8F).toInt() }
        px8?.let { param.setMargins(px8 , 0 , it , 0) }
        binding.expandableListView.layoutParams = param
    }

    fun pxFromDp(context: Context , dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    private fun initExpandableListView() {
        val listFaqParent = ArrayList<String>()
        listFaqParent.addAll(resources.getStringArray(R.array.faq_header))
        val faqAdapter = (activity as MainActivity?)?.getChildListData(listFaqParent)?.let {
            FaqAdapter(listFaqParent , it)
        }
        binding.expandableListView.setAdapter(faqAdapter)
        binding.expandableListView.setOnGroupExpandListener(this)
    }

    override fun onGroupExpand(groupPosition : Int) {
        if (previousGroupPosition != groupPosition) {
            binding.expandableListView.collapseGroup(previousGroupPosition)
        }
        previousGroupPosition = groupPosition
    }
}
