package app.storytel.candidate.com.interfaces

import app.storytel.candidate.com.models.Comment
import app.storytel.candidate.com.models.Photo
import app.storytel.candidate.com.models.Post
import app.storytel.candidate.com.utilities.NetworkUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
	
	@GET(NetworkUtils.ENDPOINT_POSTS)
	fun getPosts(): Call<List<Post>>
	
	@GET(NetworkUtils.ENDPOINT_PHOTOS)
	fun getPhotos(): Call<List<Photo>>
	
	@GET(NetworkUtils.ENDPOINT_COMMENTS)
	fun getComments(@Path("id") id: Int): Call<List<Comment>>
}