package com.hilmi.latihangetjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.hilmi.latihangetjson.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = parsingJSON()
        Log.i("DATA", "onCreate : $data")

        val mAdapter = TechAdapter()
        binding.parseJSON.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter.setData(parsingJSON())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun parsingJSON(): ArrayList<Tech> {
        // get the JSON
        val getJSON = getJSONFromAsset()
        Log.i("JSON", "parsingJSON: $getJSON")

        // get the JSON root
        val jsonRoot = JSONObject(getJSON!!)

        // get array from the tech in the news JSON
        val techListArray = jsonRoot.getJSONArray("tech_news")

        // provide a container for JSON parsed results
        val techArray = ArrayList<Tech>()

        // get the data content from the tech list and put it into the tech array variable
        for (i in 0 until techListArray.length()) {
            // get every item in the array
            val getTech = techListArray.getJSONObject(i)
            // provide a placeholder for each item
            val tech = Tech(
                title = getTech.getString("title"),
                thumb = getTech.getString("thumb"),
                author = getTech.getString("author"),
                tag = getTech.getString("tag"),
                time = getTech.getString("time"),
                desc = getTech.getString("desc"),
                key = getTech.getString("key")
            )
            techArray.add(tech)
        }
        return techArray
    }

    // The First Way

    // The function that will get the JSON data's
    private fun getJSONFromAsset(): String? {
        val json: String

        try {
            // To get the JSON from assets manager
            val stream = assets.open("tech_news.json")
            // To check there is stream input or no
            val size = stream.available()
            // Save the byteArray input to read
            val buffer = ByteArray(size)
            // To read the input
            stream.read(buffer)
            // The command that reader will stop reading
            stream.close() // To avoid errors where the reader continues to read data and does not stop

            json = String(buffer, Charsets.UTF_8)
        } catch (e: JSONException) {
            Log.e("JSON", "getJSONFromAssets : $e")
            return null
        }
        return json
    }
}