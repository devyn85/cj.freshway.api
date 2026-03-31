package cjfw.core.web.mvc;

import org.springframework.web.util.HtmlUtils;

public class XssUtils {

    public static String encode(String input) {
        if (input == null) return null;
        return HtmlUtils.htmlEscape(input);
    }

    public static String decode(String input) {
        if (input == null) return null;
        return HtmlUtils.htmlUnescape(input);
    }
}