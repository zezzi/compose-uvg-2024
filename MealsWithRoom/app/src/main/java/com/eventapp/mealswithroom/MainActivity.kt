package com.eventapp.mealswithroom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.eventapp.mealswithroom.navigation.Navigation
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealViewModelFactory
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealsCategoriesViewModel
import com.eventapp.mealswithroom.ui.theme.MealsWithRoomTheme
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track


class MainActivity : ComponentActivity() {

    private lateinit var mealViewModel: MealsCategoriesViewModel
    val clientId = "a95cca899be944edba7405ca10d0b70c\n"
    val redirectUri = "https://localhost:3000"
    var spotifyAppRemote: SpotifyAppRemote? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (applicationContext as MyApp).categoryRepository
        mealViewModel = ViewModelProvider(
            this,
            MealViewModelFactory(repository))[MealsCategoriesViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MealsWithRoomTheme {
                Navigation(navController = rememberNavController(),
                    mealViewModel = mealViewModel)
            }
        }
    }


    override fun onStart() {
        super.onStart()
        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectUri)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d("MainActivity", "Connected! Yay!")
                // Now you can start interacting with App Remote
                connected()
            }

            override fun onFailure(throwable: Throwable) {
                Log.e("MainActivity", throwable.message, throwable)
                // Something went wrong when attempting to connect! Handle errors here
            }
        })
    }

    private fun connected() {
        spotifyAppRemote?.let {
            // Play a playlist
            val playlistURI = "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL"
            it.playerApi.play(playlistURI)
            // Subscribe to PlayerState
            it.playerApi.subscribeToPlayerState().setEventCallback {
                val track: Track = it.track
                Log.d("MainActivity", track.name + " by " + track.artist.name)
            }
        }

    }

    override fun onStop() {
        super.onStop()
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it)
        }

    }
}