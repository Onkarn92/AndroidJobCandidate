package app.storytel.candidate.com.interfaces

import androidx.annotation.MainThread
import okhttp3.ResponseBody

interface HttpEventTracker<T> {
	
	/**
	 * Callback function.
	 * Call when current HTTP request executes successfully.
	 *
	 * @param response contains respective response model.
	 */
	@MainThread
	fun onCallSuccess(response: T)
	
	/**
	 * Callback function.
	 * Call when current HTTP request fails or response code is not 200 (HTTP OK).
	 *
	 * @param cause        of the request failure.
	 * @param throwable    contains cause of the failure.
	 * @param responseBody contains error body of response.
	 */
	@MainThread
	fun onCallFail(
			cause: String,
			throwable: Throwable,
			responseBody: ResponseBody? = null
	)
}