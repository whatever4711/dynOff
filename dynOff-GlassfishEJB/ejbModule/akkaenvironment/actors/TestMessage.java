package akkaenvironment.actors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;


@SuppressWarnings("serial")
public class TestMessage implements Serializable {
    // private static final long serialVersionUID = -2800066513310728483L;

    private String content;

    private byte[] image;

    public TestMessage(String content, byte[] image) {
        this.content = content;
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
