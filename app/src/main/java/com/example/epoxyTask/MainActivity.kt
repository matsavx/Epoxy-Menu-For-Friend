package com.example.epoxyTask

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.slider.slider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var list: EpoxyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        list = findViewById(R.id.list)

        val banners = listOf("https://unsplash.com/photos/hud8Oa4D5Gs/download?force=true",
                "https://unsplash.com/photos/0r1gjbBrGeM/download?force=true",
                "https://unsplash.com/photos/FOUxD6zodR4/download?force=true",
                "https://unsplash.com/photos/M19_crm09Zw/download?force=true",
                "https://unsplash.com/photos/F-JUGSYNjZU/download?force=true",
                "https://unsplash.com/photos/N2X20_MlF20/download?force=true",
                "https://unsplash.com/photos/OeGImGbmxls/download?force=true",
                "https://unsplash.com/photos/-g7axSVst6Y/download?force=true",
                "https://unsplash.com/photos/KZC7BJo0Cl0/download?force=true",
                "https://unsplash.com/photos/tzzj8LCLujU/download?force=true"
        )

        list.layoutManager = LinearLayoutManager(this)

        list.withModels {
            slider {
                id("slider")

                cycleDelay(3_000)

                indicatorVisible(true)
                indicatorSelectedDotColor(Color.YELLOW)
                indicatorDotColor(Color.BLACK)

                models(
                        banners.mapIndexed { index, s ->
                            BannerViewModel_().apply {
                                id(index.toLong())
                                bindImage(s)
                                listener {
                                    showImageUrl(it)
                                }
                            }
                        }
                )

                infinite(true)

                copier { oldModel ->
                    oldModel as BannerViewModel_
                    BannerViewModel_().apply {
                        id(oldModel.id())
                        bindImage(banners[oldModel.id().toInt()])
                        listener { imageUrl ->
                            showImageUrl(imageUrl)
                        }
                    }
                }
            }
        }
    }

    private fun showImageUrl(imageUrl: String?) {
        Toast.makeText(
                this@MainActivity,
                imageUrl,
                Toast.LENGTH_SHORT
        ).show()
    }
}
