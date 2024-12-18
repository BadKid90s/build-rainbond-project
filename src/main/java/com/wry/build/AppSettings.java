// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package com.wry.build;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

@State(
    name = "org.intellij.sdk.settings.AppSettings",
    storages = @Storage("SdkSettingsPlugin.xml")
)
final class AppSettings  implements PersistentStateComponent<AppSettings.State> {

  static class State {
    @NonNls
    public String api = "webhook API";
    public String secret = "secret";
    public String api2 = "";
    public String secret2 = "";
  }

  private State myState = new State();

  static AppSettings getInstance() {
    return ApplicationManager.getApplication()
            .getService(AppSettings.class);
  }

  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(@NotNull State state) {
    myState = state;
  }

}