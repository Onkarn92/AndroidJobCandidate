package app.storytel.candidate.com.interfaces

import okhttp3.ResponseBody
import retrofit2.Call

interface HttpOperationCallback<T> {
	
	/**
	 * Callback function for any type of response.
	 *
	 * @param call      instance of executed [Call].
	 * @param result    contains response body.
	 * @param errorPair contains human readable exception.
	 * @param errorBody contains error body.
	 */
	fun onResponse(
			call: Call<T>,
			result: T? = null,
			errorPair: Pair<String, Throwable>,
			errorBody: ResponseBody? = null
	)
}