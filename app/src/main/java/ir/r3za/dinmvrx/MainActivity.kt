package ir.r3za.dinmvrx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.r3za.dinmvrx.presentation.menu.MenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MenuFragment::class.java, null, null)
            .commitAllowingStateLoss()

    }


}