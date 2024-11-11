package com.example.recurso_drawable

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.ImageViewCompat

class MainActivity : AppCompatActivity() {
    private var activo=true
    private lateinit var imagen: ImageView
    private lateinit var handler: Handler
    private lateinit var mirunable:Runnable
    var arrayImages= arrayOf(R.drawable.wp4470362_1440x2880_wallpapers,R.drawable.wp4470363_1440x2880_wallpapers,R.drawable.wp4470366_1440x2880_wallpapers,R.drawable.androidparty)
    var i=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        var imagen=findViewById<ImageView>(R.id.imageView)

        //Escuchador para la imagen
        imagen.setOnClickListener {
            mirunable.let {
                if(activo)

                //Elimino las tareas pendientes del handler, por lo que para el runable
                    handler.removeCallbacks(it)
            else
                //Comienzan de nuevo el salvapantallas
                handler.post(mirunable)
            this.activo=!this.activo
            }

        }
        //Instancio el handler
       handler=Handler(Looper.getMainLooper())

        //Defino un runable
        mirunable=object :Runnable{
            //Metodo que se ejecuta
            override fun run() {
                //Establezco la imagen

                imagen.setImageDrawable(ContextCompat.getDrawable(this@MainActivity,arrayImages.get(i)))

                //Otra forma de establecer la imagen, pero más lento
              //  imagen.setImageResource(arrayImages[i])
                //Paso a la siguiente imagen
                i=++i%arrayImages.size

                //Definir cada cuanto tiempo se ejecuta este manejador
                handler.postDelayed(this,3000)
            }

        }
        //Ejecutar la tarea inmediatamente
        handler.post(mirunable)


    }
}