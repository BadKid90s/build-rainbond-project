package com.wry.build;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.Objects;

public class BuildAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        String msg;
        try {
            msg = "The build process has started.<br>";
            msg += sendPostRequest(state.api, state.secret);
            msg += sendPostRequest(state.api2, state.secret2);
        } catch (Exception ex) {
            msg = "The build process has error.<br>" + ex.getMessage();
        }
        //        String msg="Build Project HTTP Status Code: 200";
        System.out.println("state.api :" + state.api);
        System.out.println("state.secret :" + state.secret);

        Notification notification = new Notification(
                "com.wry.build.BuildAction",
                "Build rainbond project",
                msg,
                NotificationType.INFORMATION
        );
        Notifications.Bus.notify(notification);
    }


    private String sendPostRequest(String api, String secret) {
        if (api == null || api.isEmpty() || secret == null || secret.isEmpty()) {
            return "";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("secret_key", secret);

        int statusCode;
        try (HttpResponse response = HttpRequest.post(api)
                .body(jsonObject.toString())
                .execute()
        ) {

            statusCode = response.getStatus();
        }
        return "Build Project HTTP Status Code: " + statusCode + ".<br>";
    }
}
