package by.bsu.soroka.ea.neko.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.JspTag;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.tagext.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.taglibs.standard.tag.rt.core.ForEachTag;

import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Scanner;

@Slf4j
public class ImgElementTag extends SimpleTagSupport {
    private final static String IMAGE_TAG = "<img" +
            " align=\"center\"" +
            " style=" +
            "\"" +
                "background-size: cover; " +
                "display: block;" +
                "margin-left: auto;" +
                "margin-right: auto; " +
                "width: 60%" +
            "\"" +
            " src=\"{0}\" />";
    private StringWriter body = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(body);
        Scanner sc = new Scanner(body.toString());
        JspWriter out = getJspContext().getOut();
        while (sc.hasNextLine()) {
            String buff = sc.nextLine();
            buff = buff.trim();
            if(!buff.isEmpty()){
                log.error("Out: {}", buff);
                out.println(MessageFormat.format(IMAGE_TAG, buff));
            }
        }
    }

}
