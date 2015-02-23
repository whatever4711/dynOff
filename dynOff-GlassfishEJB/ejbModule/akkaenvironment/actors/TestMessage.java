package akkaenvironment.actors;

import java.io.Serializable;

/**
 * Nachricht um einen String zu Testzwecken zu kapseln und in TestActor zu
 * verarbeiten.
 */

@SuppressWarnings("serial")
public class TestMessage implements Serializable {

    //private static final long serialVersionUID = -2800066513310728483L;
	
	private String content;

	public TestMessage(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
