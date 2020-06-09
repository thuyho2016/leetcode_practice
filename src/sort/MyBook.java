package sort;

import java.util.Comparator;

public class MyBook {

    public String name;
    public int id;
    public String author;
    public String publisher;

    public MyBook(String name, int id, String author, String publisher) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "MyType{" +
                "name=" + name + '\'' +
                ", id=" + id +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}' + System.getProperty("line.separator");
    }
    
    public static Comparator<MyBook> NameComparator = new Comparator<MyBook>() {

		public int compare(MyBook b1, MyBook b2) {
		   int result = b2.name.compareTo(b1.name);
		   return result;
		}
	};
}
