package com.gutani.dominandooandroid

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_test_text_view.*
import java.io.IOException

class TestTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_text_view)


        //Adiciona Strike no Texto do TextView
        txtStrike.paintFlags = txtStrike.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        val htmlText = """
            <html>
            <body>
            HTML EM <b>Negrito</b>, <i>Itálico</i> e <u>Sublinhado</u><br>
            <br>
            <br>
            <img src='mario.png'/> <br>
            <br>
            <br>
            <img src='yoshi.png'/> <br>
            <br>
            <br>
            <br>
            Understand five key Python concepts related to these magic methods<br>
            </body>
            </html>            
        """.trimIndent()

        val imgGetter = Html.ImageGetter { source ->
            try {
                val bmp = BitmapFactory.decodeStream(assets.open(source))
                val drawable = BitmapDrawable(resources, bmp)
                drawable.setBounds(0,0,bmp.width/2, bmp.height/2)
                drawable
            }catch (e :IOException){
                e.printStackTrace()
                null
            }
        }
        txtHtml.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT, imgGetter, null)
        }else{
            Html.fromHtml(htmlText, imgGetter, null)
        }


        val textTitle = "        Tipos e Possibilidades Com o Spanned"
        val textLarge = "Big Text"
        val textBold = "Negrito"
        val textUnderline = "Sublinhado"
        val textColored = "Colorido"
        val textBackground = "BackGround"
        val textClick = "Click Here"
        val textUrl = "https://www.google.com.br"
        val textComplete = """
            $textTitle
            $textLarge
            $textBold 
            $textUnderline
            $textColored 
            $textBackground
            $textClick
            $textUrl
        """.trimIndent()

        val spannableString = SpannableString(textComplete)
        spannableString.setSpan(RelativeSizeSpan(4f),
            textComplete.indexOf(textLarge), textComplete.indexOf(textLarge) + textLarge.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        spannableString.setSpan(StyleSpan(Typeface.BOLD),
            textComplete.indexOf(textBold),
            textComplete.indexOf(textBold) + textBold.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        spannableString.setSpan(UnderlineSpan(),
            textComplete.indexOf(textUnderline),
            textComplete.indexOf(textUnderline) + textUnderline.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        spannableString.setSpan(ForegroundColorSpan(Color.BLUE), textComplete.indexOf(textColored), textComplete.indexOf(textColored) + textColored.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(BackgroundColorSpan(Color.YELLOW), textComplete.indexOf(textBackground), textComplete.indexOf(textBackground) + textBackground.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(this@TestTextViewActivity, "Click", Toast.LENGTH_SHORT).show()
            }
        }, textComplete.indexOf(textClick), textComplete.indexOf(textClick) + textClick.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE )
        spannableString.setSpan(URLSpan("http://$textUrl"), textComplete.indexOf(textUrl), textComplete.indexOf(textUrl) + textUrl.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(ImageSpan(this, R.mipmap.ic_launcher), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        txtSpannable.movementMethod = LinkMovementMethod.getInstance()
        txtSpannable.text = spannableString



    }
}