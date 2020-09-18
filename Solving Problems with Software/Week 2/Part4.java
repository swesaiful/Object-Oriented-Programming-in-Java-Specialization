public class Part4 {
   
   public static void main(String [] args){
    URLResource file = new  URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
   	for (String item : file.words()) {
       	   String search = item.toLowerCase();
       	   int pos = search.indexOf("youtube.com");
       	   if (pos != -1) {
           	int beginningUrl = item.lastIndexOf("\"",pos);
           	int endUrl = item.indexOf("\"", pos+1);
           	System.out.println(item.substring(beginningUrl+1,endUrl));
        }
    }
}
}
