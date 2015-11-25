package com.liferay.mobile.screens.audiencetargeting.interactor.requestcontent.services;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.wikipage.WikiPageService;
import com.liferay.mobile.screens.audiencetargeting.interactor.AudienceTargetingResult;
import com.liferay.mobile.screens.audiencetargeting.interactor.requestcontent.AudienceTargetingJSONRequestedCallback;
import com.liferay.mobile.screens.context.SessionContext;

/**
 * @author Javier Gamarra
 */
public class WikiPageContentService extends AudienceTargetingContentService {
	@Override
	public void retrieveBySessionAndClassPK(final AudienceTargetingResult result, Integer screenletId) {
		Session session = SessionContext.createSessionFromCurrentSession();
		session.setCallback(new AudienceTargetingJSONRequestedCallback(screenletId, result));
		WikiPageService wikiPageService = new WikiPageService(session);
		//TODO does not work
//		wikiPageService.getPage();
	}
}