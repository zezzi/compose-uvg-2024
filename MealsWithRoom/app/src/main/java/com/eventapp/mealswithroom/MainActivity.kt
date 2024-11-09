package com.eventapp.mealswithroom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealsCategoriesViewModel
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import okhttp3.Call


class MainActivity : ComponentActivity() {

    private lateinit var mealViewModel: MealsCategoriesViewModel
    val clientId = "YOURCLIENTID"
    var spotifyAppRemote: SpotifyAppRemote? = null
    val AUTH_CODE_REQUEST_CODE: Int = 0x11

    private var mAccessToken: String? = null
    private var mAccessCode: String? = null
    private val mCall: Call? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onRequestCodeClicked()
//        val repository = (applicationContext as MyApp).categoryRepository
//        mealViewModel = ViewModelProvider(
//            this,
//            MealViewModelFactory(repository))[MealsCategoriesViewModel::class.java]
//
//        enableEdgeToEdge()
//        setContent {
//            MealsWithRoomTheme {
//                Navigation(navController = rememberNavController(),
//                    mealViewModel = mealViewModel)
//            }
//        }
    }


    override fun onStart() {
        super.onStart()
    }

    fun onRequestCodeClicked() {
        val request = getAuthenticationRequest(AuthorizationResponse.Type.CODE)
        AuthorizationClient.openLoginActivity(this, AUTH_CODE_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
        return AuthorizationRequest.Builder(clientId, type, getRedirectUri().toString())
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .setCampaign("your-campaign-token")
            .build()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)

        Log.d("SpotifyAuth", "Response Type: ${response.type}")
        Log.d("SpotifyAuth", "Response Code: ${response.code}")
        Log.d("SpotifyAuth", "Response Error: ${response.error}")

        if (response.type == AuthorizationResponse.Type.TOKEN) {
            mAccessToken = response.accessToken
            updateTokenView()
        } else if (response.type == AuthorizationResponse.Type.CODE) {
            mAccessCode = response.code
            updateCodeView()
        } else if (response.type == AuthorizationResponse.Type.ERROR) {
            setResponse(response.error)
        }
    }

    private fun setResponse(text: String) {
        runOnUiThread {
            Log.d("ZEZZIresponse", text)
        }
    }

    private fun updateTokenView() {
        mAccessToken?.let { Log.d("ZEZZIresponsetoken", it)
            testConnection()
        }
    }

    private fun updateCodeView() {
        mAccessCode?.let {
            Log.d("ZEZZImAccessCodetoken", it)
            testConnection()
        }
    }

    fun testConnection() {
        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri("https://localhost:3000")
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

    private fun cancelCall() {
        mCall?.cancel()
    }

    override fun onStop() {
        super.onStop()
        cancelCall()
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it)
        }
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

    private fun getRedirectUri(): Uri {
        return Uri.parse("https://localhost:3000")
    }
}