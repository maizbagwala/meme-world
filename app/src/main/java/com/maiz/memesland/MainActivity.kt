package com.maiz.memesland

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MyAdapter
    private var memelist = mutableListOf<memes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.title ="Maiz`s meme world"
        setContentView(R.layout.activity_main)
        adapter = MyAdapter(memelist, this@MainActivity)
        rv.adapter = adapter
//        rv.layoutManager = LinearLayoutManager(this@MainActivity)


        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                if (layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {
                    getmemes()
                }
            }
        })

        rv.layoutManager = layoutManager

        getmemes()


    }

    private fun getmemes() {
        val result: Call<ApiResponse> = MyApi.ApiService.memeInstance.getpost(50)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val respons = response.body()
                if (respons != null) {
                    Log.d("bantai", "onResponse: ${respons.memes}")
                    memelist.addAll(respons.memes)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("bantai", "onFailure: ", t)
            }
        })
    }
}