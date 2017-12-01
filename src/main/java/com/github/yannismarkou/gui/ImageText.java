/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.yannismarkou.gui;

/**
 *
 * @author yannis
 */
public class ImageText {
    private final String locale;
    private final String text;

    public ImageText(String locale, String text) {
        this.locale = locale;
        this.text = text;
    }

    public ImageText(ImageText imageText)  {
        this.locale = imageText.getLocale();
        this.text = imageText.getText();
    }

    public String getLocale() {
        return locale;
    }

    public String getText() {
        return text;
    }
}
