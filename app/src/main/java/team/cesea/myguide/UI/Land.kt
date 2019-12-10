package team.cesea.myguide.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import team.cesea.myguide.R

class Land : AppCompatActivity() {
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.land2, true).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_land)
    }
}
