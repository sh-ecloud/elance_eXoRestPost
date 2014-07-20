package com.yunyi.resources;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
import org.exoplatform.social.core.activity.model.ExoSocialActivityImpl;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.provider.SpaceIdentityProvider;
import org.exoplatform.social.webui.Utils;
import org.exoplatform.social.webui.activity.UIDefaultActivity;
import org.json.JSONException;
import org.json.JSONObject;

import com.yunyi.Message;
import com.yunyi.MessageToSpace;
import com.yunyi.MessageToUser;

@Path("activity")
public class ActivityStreamResources implements ResourceContainer {

	@POST
	@Path("user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postToSingerUser(@Context HttpServletRequest request, MessageToUser msg) throws JSONException,
			IOException {
		if (msg == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		ExoSocialActivity activity = getActivityFromAppMessage(msg);
		activity.setUserId(Utils.getUserIdentity(request.getRemoteUser(), false).getId());
		Identity toUserIdentity = Utils.getUserIdentity(msg.getUserName(), false);
		Utils.getActivityManager().saveActivityNoReturn(toUserIdentity, activity);

		return Response.ok(getActivityJson(activity).toString(), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("space")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postToSingerSpace(@Context HttpServletRequest request, MessageToSpace msg) throws JSONException {
		if (msg == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		ExoSocialActivity activity = new ExoSocialActivityImpl();
		activity.setTitle(msg.getMessage());
		activity.setUserId(Utils.getUserIdentity(request.getRemoteUser(), false).getId());
		Identity spaceIdentity = Utils.getIdentityManager().getOrCreateIdentity(SpaceIdentityProvider.NAME,
				msg.getSpaceName(), false);
		Utils.getActivityManager().saveActivityNoReturn(spaceIdentity, activity);
		return Response.ok(getActivityJson(activity).toString(), MediaType.APPLICATION_JSON).build();
	}

	private ExoSocialActivity getActivityFromAppMessage(Message appMessage) {
		ExoSocialActivity activity = new ExoSocialActivityImpl();
		activity.setTitle(appMessage.getMessage());
		String type = appMessage.getType();
		if (null == type || "".equals(type.trim())) {
			activity.setType(UIDefaultActivity.ACTIVITY_TYPE);
		} else {
			activity.setType(type);
		}
		return activity;
	}

	private JSONObject getActivityJson(ExoSocialActivity activity) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", activity.getId());
		json.put("title", activity.getTitle());
		json.put("lastUpdated", activity.getUpdated().getTime());
		json.put("type", activity.getType());
		json.put("body", activity.getBody());
		return json;
	}

}
