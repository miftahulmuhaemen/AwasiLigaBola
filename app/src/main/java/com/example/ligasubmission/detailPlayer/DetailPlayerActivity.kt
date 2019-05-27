package com.example.ligasubmission.detailPlayer

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.ligasubmission.R
import com.example.ligasubmission.model.Player
import com.example.ligasubmission.util.AnkoLayoutConst
import com.example.ligasubmission.util.Util
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailPlayerActivity : AppCompatActivity() {


    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        player = intent.getParcelableExtra(Util.DETAIL_TRANSACTION)

        scrollView {
            verticalLayout {
                padding = dip(AnkoLayoutConst.Margin_16dp)

                Picasso.get().load(player.strThumb).into(imageView().lparams{
                    width = matchParent
                    height = dip (200)
                })

                textView {
                    text = "Name : " + player.strPlayer + " / " + player.dateBorn
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView {
                    text = "Nationality : " + player.strNationality
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView {
                    text = "Team National : " + player.strTeamNational
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView {
                    text = "Wage : " + player.strWage
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView{
                    text = "Position : " + player.strPosition + " / Back Numbeer " + player.strNumber
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView{
                    text = "Description"
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView(player.strDescriptionEN)
            }
        }
    }
}
