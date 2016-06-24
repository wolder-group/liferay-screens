package com.liferay.mobile.screens.viewsets.defaultviews.assetdisplay;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.liferay.mobile.screens.assetdisplay.view.AssetDisplayViewModel;
import com.liferay.mobile.screens.assetlist.AssetEntry;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.util.LiferayLogger;

/**
 * @author Sarai Díaz García
 */
public class AssetDisplayView extends LinearLayout implements AssetDisplayViewModel {

  public AssetDisplayView(Context context) {
    super(context);
  }

  public AssetDisplayView(Context context, AttributeSet attributes) {
    super(context, attributes);
  }

  public AssetDisplayView(Context context, AttributeSet attributes, int defaultStyle) {
    super(context, attributes, defaultStyle);
  }

  @Override public void showStartOperation(String actionName) {
  }

  @Override public void showFinishOperation(String actionName) {
    throw new UnsupportedOperationException("showFinishOperation(String) is not supported."
      + " Use showFinishOperation(String, AssetEntry) instead.");
  }

  @Override public void showFinishOperation(AssetEntry assetEntry) {
    LiferayLogger.d(assetEntry.getTitle());
  }

  @Override public void showFailedOperation(String actionName, Exception e) {
    LiferayLogger.e("Could not load asset", e);
  }

  @Override public BaseScreenlet getScreenlet() {
    return _screenlet;
  }

  @Override public void setScreenlet(BaseScreenlet screenlet) {
    this._screenlet = screenlet;
  }

  private BaseScreenlet _screenlet;
}
