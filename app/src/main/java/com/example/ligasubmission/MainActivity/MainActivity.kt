package com.example.ligasubmission.MainActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.ligasubmission.DetilActivity.DetilActivity
import com.example.ligasubmission.Model.Item
import com.example.ligasubmission.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        val DETIL_TRANSACTION = "detil"
    }

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        val mAdapter = RecyclerViewAdapter(items) {
            startActivity<DetilActivity>(DETIL_TRANSACTION to it)
        }

        relativeLayout{
            padding = dip(16)
            lparams (width = matchParent, height = wrapContent)
            recyclerView{
                lparams (width = matchParent, height = wrapContent)
                layoutManager = GridLayoutManager(this@MainActivity,2)
                adapter = mAdapter
            }
        }

//        MainActivityUI(adapter).setContentView(this)
    }


    private fun initData(){
        val id = resources.getStringArray(R.array.club_id)
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_desc)

        items.clear()
        for (i in name.indices) {
            items.add(
                Item(
                    id[i].toInt(), name[i], desc[i],
                    image.getResourceId(i, 0)
                )
            )
        }

        //Recycle the typed array
        image.recycle()
    }

//    class MainActivityUI(val mAdapter: RecyclerViewAdapter) : AnkoComponent<MainActivity> {
//        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
//
//        }
//    }

}
