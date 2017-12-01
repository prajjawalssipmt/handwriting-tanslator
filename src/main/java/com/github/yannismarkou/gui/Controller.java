package com.github.yannismarkou.gui;

import com.github.yannismarkou.gservices.GCloudVision;
import com.github.yannismarkou.gservices.GCustomSearch;
import com.github.yannismarkou.gservices.GTranslate;
import com.github.yannismarkou.resources.Resources;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import static com.github.yannismarkou.resources.Resources.gAPIKey;
import static com.github.yannismarkou.resources.Resources.languageCodes;
import static com.github.yannismarkou.resources.Resources.languages;

/**
 *
 * @author yannis
 */
public class Controller {

    public String imageFileSelector() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setCurrentDirectory(new File("C:\\images"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    public ImageText extractImageText(String imageURL) throws IOException {
        if (imageURL != null) {
            GCloudVision gcVision = new GCloudVision();
            ImageText imageText = new ImageText(gcVision.getImageText(imageURL));
            return imageText;
        }
        return null;
    }

    public ImageText correctImageText(ImageText originalImageText) {
        String correctedText = "";
        try {
            GCustomSearch gcs = new GCustomSearch(gAPIKey);
        correctedText = gcs.search(originalImageText.getLocale(),
                        originalImageText.getText());
        } catch (Exception e) {
            correctedText = originalImageText.getText();
        }
        
        return new ImageText(originalImageText.getLocale(), correctedText);
    }

    public String languageSelector(boolean selectTranslationLang) {
        String uiTitle, uiText;
        if (selectTranslationLang) {
            uiTitle = "Translation Language Selection";
            uiText = "Select language to translate:";
        } else {
            uiTitle = "Original Language Selection";
            uiText = "Select language of original text:";
        }

        String input = (String) JOptionPane.showInputDialog(null, uiText,
                uiTitle, JOptionPane.PLAIN_MESSAGE, null,
                languages, // Array of choices
                languages[21]); // Initial choice

        int languageIndex = Arrays.asList(languages).indexOf(input);
        return languageCodes[languageIndex];
    }

    public String translateText(String textToTranslate,
            String fromLang, String toLang) {
        GTranslate translator = new GTranslate(Resources.gAPIKey);
        return translator.translte(textToTranslate, fromLang, toLang);
    }

    public void showTranslatedImage(final String imageURL, final String text) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    TextOverlay.create(imageURL, text);
                } catch (IOException ex) {
                    Logger.getLogger(TextOverlay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void log(String originalText, String predictedLocale,
            String trueLocale, String correctedText,
            String translateToLang, String translatedText) {
        System.out.println("\tOriginal text:\t" + originalText);
        System.out.println("Predicted language:\t" + predictedLocale);
        System.out.println("\tTrue language:\t" + trueLocale);
        System.out.println("\tCorrected text:\t" + correctedText);
        System.out.println("\tTranslate to:\t" + translateToLang);
        System.out.println("\tTranslation:\t" + translatedText);
    }

    public void runImageTranslator() throws IOException {
        String imageLocation = imageFileSelector();

        ImageText originalImageText = extractImageText(imageLocation);
        String predictedLocal = originalImageText.getLocale();
        String originalTextLocale = languageSelector(false);

        originalImageText = new ImageText(originalTextLocale,
                originalImageText.getText());

        ImageText correctedImageText = correctImageText(originalImageText);
        String locale = originalImageText.getLocale();

        String text;

        if (correctedImageText.getText() != null) {
            text = correctedImageText.getText();
        } else {
            text = originalImageText.getText();
        }

        String translateTo = languageSelector(true);

        String translation = translateText(text, locale, translateTo);

        showTranslatedImage(imageLocation, translation);

        log(originalImageText.getText(), predictedLocal,
                originalImageText.getLocale(), correctedImageText.getText(),
                translateTo, translation);
    }
}
