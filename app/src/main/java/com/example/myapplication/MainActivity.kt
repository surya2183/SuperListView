package com.example.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.*;

import kotlinx.android.synthetic.main.activity_main.*

import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory







class MainActivity : AppCompatActivity() {
  val language = arrayOf<String>("C","C++","Java",".Net","Kotlin","Ruby","Rails","Python","Java Script","Php","Ajax","Perl","Hadoop")
  val description = arrayOf<String>(
    "C programming is considered as the base for other programming languages",
    "C++ is an object-oriented programming language.",
    "Java is a programming language and a platform.",
    ".NET is a framework which is used to develop software applications.",
    "Kotlin is a open-source programming language, used to develop Android apps and much more.",
    "Ruby is an open-source and fully object-oriented programming language.",
    "Ruby on Rails is a server-side web application development framework written in Ruby language.",
    "Python is interpreted scripting  and object-oriented programming language.",
    "JavaScript is an object-based scripting language.",
    "PHP is an interpreted language, i.e., there is no need for compilation.",
    "AJAX allows you to send and receive data asynchronously without reloading the web page.",
    "Perl is a cross-platform environment used to create network and server-side applications.",
    "Hadoop is an open source framework from Apache written in Java."
  )

  val imageId = arrayOf<Int>(
    R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background,
    R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background,
    R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,
    R.drawable.ic_launcher_background
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    val myListAdapter = MyAdapter2(this,language,description,imageId)
    listView.adapter = myListAdapter

    listView.setOnItemClickListener() { adapterView, view, position, id ->
      val itemAtPos = adapterView.getItemAtPosition(position)
      val itemIdAtPos = adapterView.getItemIdAtPosition(position)
      callService();
      Toast.makeText(
        this,
        "Click on item at $itemAtPos its item id $itemIdAtPos",
        Toast.LENGTH_LONG
      ).show()
    }

      /*fab.setOnClickListener { view ->
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
      }*/
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  fun callService(){
    val httpclient=OkHttpClient.Builder().build();
    var gson=GsonBuilder().setLenient().create()
    val builder = Retrofit.Builder().client(httpclient)
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .
        build()
    //var retrofit=builder.build();
    val factsService=builder.create(FactsService::class.java)
val call=factsService.getCoinData().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
  .subscribe(this::handleResponse, this::handleError)




  }
 fun handleResponse(factsService: Facts?) {

    Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
  }
 fun handleError(error: Throwable) {

   // Log.d(TAG, error.localizedMessage)

    Toast.makeText(this, "hello 123", Toast.LENGTH_SHORT).show()
  }

}
