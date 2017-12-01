/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.yannismarkou.gservices;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

/**
 * @author yannis
 */
public class SpellChecker {

    public static void main(String[] args) throws IOException {
        JLanguageTool langTool = new JLanguageTool(new BritishEnglish());

        List<RuleMatch> matches = langTool.check("speling eror");
        for (RuleMatch match : matches) {
            System.out.println("Potential typo at characters "
                    + match.getFromPos() + "-" + match.getToPos() + ": "
                    + match.getMessage());
            System.out.println("Suggested correction(s): "
                    + match.getSuggestedReplacements());
        }
    }
}
