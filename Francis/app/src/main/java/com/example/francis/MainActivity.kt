package com.example.francis

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Logando em um endereço web
        wvFrancis.loadUrl("https://www.google.com.br")


        //Habilitando JavaScript para as paginas web
        wvFrancis.settings.javaScriptEnabled = true

        //para selecionar url quando clicar no edt
        edtLink.setOnClickListener {
            edtLink.selectAll()
        }


        //Ao clicar no enter carrega a página
        edtLink.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                wvFrancis.loadUrl("https://" + edtLink.text.toString())

                return@OnKeyListener true}
            false

        } )



        //Zoom na webView
        wvFrancis.settings.setSupportZoom(true)
        wvFrancis.settings.builtInZoomControls = true
        wvFrancis.settings.displayZoomControls = true



        //Configurando os botões

        //Botão ir para o link escrito
        btnIr.setOnClickListener {

            wvFrancis.loadUrl("https://" + edtLink.text.toString())}

            //botão voltar
            btnVoltar.setOnClickListener {

                if (wvFrancis.canGoBack())
                    wvFrancis.goBack()

                //Toast
                else Toast.makeText(this, "Sem histórico disponível", Toast.LENGTH_SHORT).show()

            }

        //para colocar a url no edt url
            fun ColocarUrl() {
            val url: String = wvFrancis.url
            edtLink.setText(url, TextView.BufferType.EDITABLE)

        }

            //Botão ir
        btnAvancar.setOnClickListener {

            if (wvFrancis.canGoForward())
                wvFrancis.goForward()

            //Toast
            else Toast.makeText(this, "Sem histórico disponível", Toast.LENGTH_SHORT).show()

        }

        //Botão home
        btnHome.setOnClickListener {
            wvFrancis.loadUrl("https://www.google.com.br")
        }

        //atualizando página
        btnAtt.setOnClickListener {
            wvFrancis.reload()
        }

        //Mecher na internet pelo app, sem abrir outro navegador

        @Suppress("OverridingDeprecatedMember")
        class Francis : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                ColocarUrl()
            }

        }

        wvFrancis.webViewClient = Francis()









    }
}
