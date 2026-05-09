package structural.decorator;

interface TextViewII {
    public void render();
}

class PlainTextViewII implements TextViewII {

    private final String text;

    public PlainTextViewII(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.print(text);
    }
}

abstract class TextViewDecorator implements TextViewII {
    protected final TextViewII view;

    public TextViewDecorator(TextViewII view) {
        this.view = view;
    }
}

class BoldTextViewDecorator extends TextViewDecorator {
    public BoldTextViewDecorator(TextViewII view) {
        super(view);
    }

    @Override
    public void render() {
        System.out.print("<b> ");
        view.render();
        System.out.print(" </b>");
    }
}

class ItalicTextViewDecorator extends TextViewDecorator {

    public ItalicTextViewDecorator(TextViewII view) {
        super(view);
    }

    @Override
    public void render() {
        System.out.print("<i> ");
        view.render();
        System.out.print(" </i>");
    }
}

public class ThisIsWhyDecorator {
    static void main() {
        new BoldTextViewDecorator(new ItalicTextViewDecorator(new PlainTextViewII("Hello"))).render();
    }
}
