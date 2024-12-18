// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.wry.build;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

/**
 * Provides controller functionality for application settings.
 */
final class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent mySettingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Rainbond Build Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        return !mySettingsComponent.getApiText().equals(state.api) ||
                !mySettingsComponent.getSecretText().equals(state.secret) ||
                !mySettingsComponent.getApiText2().equals(state.api2) ||
                !mySettingsComponent.getSecretText2().equals(state.secret2);
    }

    @Override
    public void apply() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        state.api = mySettingsComponent.getApiText();
        state.secret = mySettingsComponent.getSecretText();
        state.api2 = mySettingsComponent.getApiText2();
        state.secret2 = mySettingsComponent.getSecretText2();
    }

    @Override
    public void reset() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        mySettingsComponent.setApiText(state.api);
        mySettingsComponent.setSecretText(state.secret);
        mySettingsComponent.setApiText2(state.api2);
        mySettingsComponent.setSecretText2(state.secret2);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}