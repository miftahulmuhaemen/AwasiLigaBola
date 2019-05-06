package com.example.ligasubmission.DetilActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.example.ligasubmission.MainActivity.MainActivity.Companion.DETIL_TRANSACTION
import com.example.ligasubmission.Model.Item
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetilActivity : AppCompatActivity(), AnkoLogger {

    lateinit var desc: TextView
    lateinit var nama: TextView
    lateinit var ids: TextView
    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val item : Item = intent.getParcelableExtra(DETIL_TRANSACTION)
        info(item.desc)

        scrollView {
            verticalLayout{
                padding = dip(16)
                gravity = Gravity.CENTER_HORIZONTAL
                img = imageView{
                }.lparams{
                    width = dip(100)
                    height = dip(100)
                    setMargins(0, dip(8), 0, 0)
                }

                nama = textView{
                    textSize = sp(9).toFloat()
                }.lparams{
                    width = wrapContent
                    height = wrapContent
                    setMargins(0, dip(8), 0, 0)
                }

                ids = textView{
                    textSize = sp(9).toFloat()
                }.lparams{
                    width = wrapContent
                    height = wrapContent
                    setMargins(0, 0, dip(8), 0)
                }

                desc = textView{
                    textSize = sp(12).toFloat()
                }.lparams{
                    width = matchParent
                    height = wrapContent
                    setMargins(dip(16), dip(16), dip(16), 0)
                }
            }
        }

        item.img?.let { Picasso.get().load(it).into(img) }
        nama.text = item.name
        desc.text = item.desc
        ids.text = item.id.toString()
    }


}
