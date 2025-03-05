package com.example.myapidogkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.myapidogkotlin.databinding.ActivityMainBinding
import com.example.myapidogkotlin.model.Datos
import com.example.myapidogkotlin.recycler.MyAdapter
import com.example.myapidogkotlin.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MainViewModel by viewModels()
    lateinit var myAdapter: MyAdapter
    lateinit var misDatos: Datos
    var cargaInicial = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding){
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Asignamos la forma en que queremos ver el Recycler
            val mLayout = LinearLayoutManager(this@MainActivity)
            rvPerros.layoutManager = mLayout

            bt2.setOnClickListener{
                val raza = edt2.getText().toString();
                if (raza.isEmpty()){
                    Toast.makeText(applicationContext, "Debe seleccionar una raza", Toast.LENGTH_SHORT)
                        .show();
                    return@setOnClickListener;
                }
                myViewModel.devuelveFotos(raza)
            }


            myViewModel.datos.observe(this@MainActivity){
                if(it.status=="success"){
                    misDatos = it
                    myAdapter = MyAdapter (it)
                    //Y el adaptador se lo asignamos al recycler.
                    rvPerros.adapter = myAdapter
                } else {
                    if (cargaInicial!=0)
                        Toast.makeText(applicationContext, "No hay fotos de esa raza", Toast.LENGTH_SHORT)
                            .show()
                }
                if (cargaInicial==0) cargaInicial++
            }


            rvPerros.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var finalSroll : Boolean = mLayout.findLastVisibleItemPosition() == misDatos.message!!.size
                    if (mLayout.findLastVisibleItemPosition()%10 >=9&&mLayout.findLastVisibleItemPosition()/10==(misDatos.paginaActual!!-1)){
                      //  Log.i("MI TAG ","Final de scroll")
                        finalSroll = true
                    }
                    if(finalSroll&& misDatos.paginaActual!! < misDatos.numPaginas!!){
                        Snackbar.make(main, "Si desea recuperar más fotos pulse: ", Snackbar.LENGTH_LONG)
                            .setAction("Cargar más fotos", View.OnClickListener {
                                myViewModel.scrollFotos(misDatos)
                            } )
                            .show()
                    }

                }
            })
        }
    }
}