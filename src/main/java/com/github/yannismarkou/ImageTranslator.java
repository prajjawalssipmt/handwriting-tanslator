package com.github.yannismarkou;

import com.github.yannismarkou.gui.Controller;

import java.io.IOException;

/**
 *
 * @author yannis
 */
public class ImageTranslator {

    public static void main(String[] args) throws IOException {
        Controller imageTController = new Controller();
        imageTController.runImageTranslator();
    }
}
