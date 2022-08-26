package com.mshetty.bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mshetty.bottomsheet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(FaqFragment.newInstance())
        binding.toolBar.tvTitle.text = this.getText(R.string.faq)
    }

    fun getChildListData(listFaqParent : ArrayList<String>) : HashMap<String , List<String>> {
        val listFaqDataChild = HashMap<String , List<String>>()
        val faq1 = ArrayList<String>()
        faq1.addAll(resources.getStringArray(R.array.faq1))
        val faq2 = ArrayList<String>()
        faq2.addAll(resources.getStringArray(R.array.faq2))
        val faq3 = ArrayList<String>()
        faq3.addAll(resources.getStringArray(R.array.faq2))
        val faq4 = ArrayList<String>()
        faq4.addAll(resources.getStringArray(R.array.faq2))
        listFaqDataChild[listFaqParent[0]] = faq1
        listFaqDataChild[listFaqParent[1]] = faq2
        listFaqDataChild[listFaqParent[2]] = faq3
        listFaqDataChild[listFaqParent[3]] = faq4
        return listFaqDataChild
    }

    private fun loadFragment(fragment: Fragment?) {
        if (!supportFragmentManager.isDestroyed && fragment != null && fragment != supportFragmentManager.findFragmentById(
                R.id.container)) {
            if (fragment.isAdded) return
            val fragmentManager = supportFragmentManager
            if (fragmentManager.fragments.lastOrNull()?.tag == fragment::class.java.simpleName) return
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.container, fragment)
            transaction.addToBackStack(fragment.javaClass.simpleName)
            transaction.commitAllowingStateLoss()
            if (!fragmentManager.isDestroyed) fragmentManager.executePendingTransactions()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}