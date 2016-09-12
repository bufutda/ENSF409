public class Text implements Comparable<Text> {
    private String text;

	public Text(String text) {
       this.text = text;
	}


	public void setText(String newText){
		text = newText;
	}

	public String getText(){
		return text ;
	}

    public int compareTo(Text other) {
        return text.compareTo(other.text);
    }

	@Override
	public String toString(){
		return (text);
	}
}
