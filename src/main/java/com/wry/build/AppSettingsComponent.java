// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package com.wry.build;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

    private final JPanel myMainPanel;
    private final JBTextField apiText = new JBTextField();
    private final JBTextField secretText = new JBTextField();

    private final JBTextField apiText2 = new JBTextField();
    private final JBTextField secretText2 = new JBTextField();
    public AppSettingsComponent() {
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("API:"), apiText, 1, false)
                .addLabeledComponent(new JBLabel("Secret:"), secretText, 2, false)
                .addLabeledComponent(new JBLabel("API2:"), apiText2, 3, false)
                .addLabeledComponent(new JBLabel("Secret2:"), secretText2, 4, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return apiText;
    }

    @NotNull
    public String getApiText() {
        return apiText.getText();
    }

    public void setApiText(@NotNull String newText) {
        apiText.setText(newText);
    }

    @NotNull
    public String getSecretText() {
        return secretText.getText();
    }

    public void setSecretText(@NotNull String newText) {
        secretText.setText(newText);
    }

    public String getApiText2() {
        return apiText2.getText();
    }

    public String getSecretText2() {
        return secretText2.getText();
    }
    public void setApiText2(@NotNull String newText) {
        apiText2.setText(newText);
    }

    public void setSecretText2(@NotNull String newText) {
        secretText2.setText(newText);
    }
}