package com.example.news

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(bttm_nav, (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController)
    }

//    val api: TmdbApi by inject()
//
//    val namesFlow = flow {
//        val names = listOf("Jody", "Steve", "Lance", "Joe")
//        for (name in names) {
//            delay(100)
//            emit(name)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView()
//
////         namesFlow
////             .onEach {
////                 Log.d("WTF", "$it")
////             }
////             .launchIn(GlobalScope)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            namesFlow.collect { Log.d("WTF", "AAA $it") }
//
//        }
//
//        GlobalScope.launch(Dispatchers.IO) {
//            api.getPopularMovies()
//                .flatMapConcat {
//                    flow {
//                        for (item in it.results) {
//                            emit(item)
//                        }
//                    }
//                }
//                .collect { Log.d("WTF", "AAA $it") }
//
//        }
//    }

}