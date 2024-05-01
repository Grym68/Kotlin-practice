package co.uk.practice.compose_practice.data.repositories

import co.uk.practice.compose_practice.data.api.AccessToken
import co.uk.practice.compose_practice.network.SpotifyHandler

class SpotifyManager(): SpotifyHandler {
    override suspend fun fetchAccessToken(): Result<AccessToken> {
        TODO("Not yet implemented")
    }


}