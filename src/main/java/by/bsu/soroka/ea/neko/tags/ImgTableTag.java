package by.bsu.soroka.ea.neko.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class ImgTableTag  extends SimpleTagSupport {
    private int columns;
    private int group;
    private int width;

    private StringWriter body = new StringWriter();


    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(body);
        Scanner sc = new Scanner(body.toString());
        JspWriter out = getJspContext().getOut();
        width = 100/columns;
        List<String> data = new ArrayList<>();
        while(sc.hasNextLine()) {
            String buff = sc.nextLine();
            buff = buff.trim();
            if(!buff.isEmpty()){
                data.add(buff);
            }
        }

        out.println("<table width=\"80%\" align=\"center\">");

        for(int offset = 0; offset < data.size(); offset += group * columns) {
            out.println("<tr style=\"margin-top=0;\">");
            for(int cl = 0; cl < columns; ++cl) {
                openTd(out);
                for(int gr = 0; gr < group; ++gr) {
                    int index = offset + cl * group + gr;
                    if(index < data.size()) {
                        out.println(data.get(index));
                        out.println("<br>");
                    } else {
                        break;
                    }
                }
                closeTd(out);
            }
            out.println("</tr>");
        }

        out.println("</table>");
    }

    public int getColumns() {
        return columns;
    }

    private void openTd(JspWriter out) throws IOException {
        out.println("<td width=\"" + width + "%\">");
    }

    private void closeTd(JspWriter out) throws IOException {
        out.println("</td>");
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
