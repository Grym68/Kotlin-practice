package co.uk.practice.compose_practice.network

import co.uk.practice.compose_practice.data.api.AccessToken

interface SpotifyHandler {

    suspend fun fetchAccessToken(): Result<AccessToken>
}