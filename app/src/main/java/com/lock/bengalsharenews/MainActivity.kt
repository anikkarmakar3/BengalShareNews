package com.lock.bengalsharenews


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity(), Newsitemclicked {
    private lateinit var mAdapter: Sharenewsadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView=findViewById(R.id.recylerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchdata()
        mAdapter = Sharenewsadapter(this)
        recyclerView.adapter=mAdapter
    }
    private fun fetchdata() {
        val url ="https://saurav.tech/NewsAPI/everything/cnn.json"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val newsjsonarray = response.getJSONArray("articles")
                val newsarray = ArrayList<News>()
                for (i in 0 until newsjsonarray.length()) {
                    val newsjsobject = newsjsonarray.getJSONObject(i)
                    val news = News(
                        newsjsobject.getString("author"),
                        newsjsobject.getString("urlToImage"),
                        newsjsobject.getString("title"),
                        newsjsobject.getString("url")
                    )
                    newsarray.add(news)
                }
                mAdapter.updatenews(newsarray)
                Log.d("Succ", "the result is $url")
            },
            { error ->
                Toast.makeText(this,"Something Wrong",Toast.LENGTH_LONG).show()
            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    override fun newsclick(items: News) {
//        Toast.makeText(this,"clickitem success",Toast.LENGTH_LONG).show()

        val builder= CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(items.url));
    }

}