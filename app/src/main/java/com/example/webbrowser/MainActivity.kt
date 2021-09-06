package com.example.webbrowser

import android.content.ContentValues
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    lateinit var web_browser : WebView
    lateinit var search : ImageButton
    lateinit var ref : ImageButton
    lateinit var bck_btn : ImageButton
    lateinit var url_txt : EditText
    lateinit var ListView: ListView
    lateinit var list : ArrayList<String>
    lateinit var ArrayAdapter :ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val helper=MyDBhelper(applicationContext)
        // val db=helper.readableDatabase
        var visible: Boolean = false



        web_browser = findViewById<WebView>(R.id.web_browser)
        ref = findViewById<ImageButton>(R.id.ref)
        search = findViewById<ImageButton>(R.id.search)
        bck_btn = findViewById<ImageButton>(R.id.bck_btn)
        url_txt = findViewById<EditText>(R.id.url_txt)
        ListView = findViewById<ListView>(R.id.ListView)
        list = ArrayList()
        list.add("android")
        list.add("kotlin")
//        for(item in list)
//        {
//            println("Pariyojna== "+ "list[$item] : " +item)
//        }


        ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        ListView.adapter = ArrayAdapter


        ListView.visibility = View.GONE



        web_browser.settings.javaScriptEnabled = true
        // web_browser.canGoBack()
        web_browser.webViewClient = myWebClient(url_txt)

        search.setOnClickListener {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            ListView.visibility = View.GONE
            //val cv= ContentValues()
//            val pref=PreferenceManager.getDefaultSharedPreferences(this)
//            val editor=pref.edit()
//            editor.apply()
//            {
//                putString("items", list.toString())
//            }
//            SharedPreferences sharedpreferences=getSharedPreferences("MySharedPref",Mo)
//            SharedPreferences.Editor myedit=sharedPreferences.edit()
//            myedit.apply()
//        {
//                putString("items", list.toString())
//            }


//            for(item in pref)
//            {
//                println("Pariyojna== "+ "list[$item] : " +item)
//            }


            val URL = url_txt.text.toString()
            web_browser.loadUrl("https://www.google.com/search?q=" + URL)
            url_txt.setText(web_browser.url)
            visible = true
            list.add(url_txt.text.toString())
//             ListView.adapter=ArrayAdapter
            ArrayAdapter.notifyDataSetChanged()
            for(item in list)
            {
                println("Pariyojna== "+ "list[$item] : " +item)
            }





            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()

            // cv.put("USERTEXT",url_txt.text.toString())
            // db.insert("USERS",null,cv)
            //Toast.makeText(this, "this", Toast.LENGTH_SHORT).show()


        }
//        bck_btn.setOnClickListener {
//           // Log.d("yo", "here")
//
//            if (web_browser.canGoBack())
//                web_browser.goBack()
//            else
//                Toast.makeText(this, "can't go further", Toast.LENGTH_SHORT).show()
//        }

          ref.setOnClickListener( {
           web_browser.reload()
        })
        bck_btn.setOnClickListener({
            if (web_browser.canGoBack())
                web_browser.goBack()
            else
                web_browser.loadUrl("https://www.google.com/")
        })






        url_txt.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                if(s?.length!!>3 && visible==false) {
//                      ListView.visibility=View.VISIBLE
//                     }
//                else
//                {
//                    ListView.visibility=View.GONE
//                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! > 3 && visible == false) {
                    ListView.visibility = View.VISIBLE
                } else {
                    ListView.visibility = View.GONE
                }


            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        )


    }
    class myWebClient( url_txt: EditText): WebViewClient(){
        val text=url_txt
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true


        }
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return true

        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            text.setText(view?.url)

        }
    }


}



