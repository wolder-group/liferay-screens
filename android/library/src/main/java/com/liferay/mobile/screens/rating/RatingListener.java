package com.liferay.mobile.screens.rating;

import com.liferay.mobile.screens.base.thread.listener.BaseCacheListener;

/**
 * @author Alejandro Hernández
 */
public interface RatingListener extends BaseCacheListener {

	void onRatingOperationSuccess(AssetRating assetRating);
}
