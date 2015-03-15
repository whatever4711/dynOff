package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors;

import java.io.Serializable;


@SuppressWarnings("serial")
public class TestMessage implements Serializable {
    // private static final long serialVersionUID = -2800066513310728483L;

    private final String content;

    private final byte[] image;

    public TestMessage(String content, byte[] image) {
        this.content = content;
        this.image = image;
    }

    public String getContent() {
        return content;
    }  

    public byte[] getImage() {
        return image;
    }   

}
