package structural.decorator;

import org.w3c.dom.Text;

interface TextView {
    void render();
}

class PlainTextView implements TextView {

    private final String text;

    public PlainTextView(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println(text);
    }
}

// one subclass per feature
class BoldTextView implements TextView {

    private final String text;

    public BoldTextView(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println("<b>" + text + "</b>");
    }
}

class ItalicTextView implements TextView {

    private final String text;

    public ItalicTextView(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println("<i>" + this.text + "</i>");
    }
}

class BoldItalicTextView implements TextView {
    private final String text;

    public BoldItalicTextView(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println("<b> <i> " + this.text + " </i> </b>");
    }
}

public class WhyDecorator {
    static void main() {

        TextView plainTextView = new PlainTextView("Hello World");
        TextView boldTextView = new BoldTextView("Hello World");
        TextView italicTextView = new ItalicTextView("Hello World");
        TextView BoldItalicTextView = new BoldItalicTextView("Hello World");

        plainTextView.render();
        boldTextView.render();
        italicTextView.render();
        BoldItalicTextView.render();

    }
}
