package com.liferay.mobile.screens.bookmark.interactor;

import com.liferay.mobile.screens.base.thread.BaseRemoteInteractor;
import com.liferay.mobile.screens.base.thread.event.BasicEvent;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * @author Javier Gamarra
 */
public class AddDeliciousInteractorImpl extends BaseRemoteInteractor<AddBookmarkListener, BasicEvent> {

	/**
	 * This is a personal OAuth Token generated by following this tutorial:
	 * https://github.com/SciDevs/delicious-api/blob/master/api/oauth.md
	 *
	 * If the token is invalidated the API calls will return an HTTP Error 401, Unauthorized Request
	 */
	private static final String OAUTH_TOKEN = "11336429-c2378f3c44a29f593e31aa4f5521e4dd";

	@Override
	public BasicEvent execute(Object[] args) throws Exception {
		final String url = (String) args[0];
		final String title = (String) args[1];

		Headers headers = Headers.of("Authorization", "Bearer " + OAUTH_TOKEN);

		OkHttpClient client = new OkHttpClient();

		Request add =
			new Request.Builder().url("https://api.del.icio.us/api/v1/posts/add?url=" + url + "&description=" + title)
				.headers(headers)
				.build();

		Response response = client.newCall(add).execute();

		LiferayLogger.e(response.body().string());

		//delicious API doesn't return valid information if the bookmark has been added,
		// so we query again to check if everything went well
		Request get = new Request.Builder().url("https://api.del.icio.us/api/v1/posts/get").headers(headers).build();

		response = client.newCall(get).execute();

		String text = response.body().string();

		if (text.contains(url)) {
			LiferayLogger.i(text);
			return new BookmarkAdded(text);
		}
		return new BookmarkAdded(new Exception("sth went wrong"));
	}

	@Override
	public void onFailure(Exception e) {
		getListener().onAddBookmarkFailure(e);
	}

	@Override
	public void onSuccess(BasicEvent event) throws Exception {
		getListener().onAddBookmarkSuccess();
	}

	private static class BookmarkAdded extends BasicEvent {

		private String text;

		BookmarkAdded(String text) {
			this.text = text;
		}

		BookmarkAdded(Exception e) {
			super(e);
		}

		public String getText() {
			return text;
		}
	}
}

