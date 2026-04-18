package core;

public class Note {

    private String content;

    public Note(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                '}';
    }
}