public class Part3 {
    public boolean twoOccurrences(String  stringa, String stringb){
        int firstOccurrence = stringb.indexOf(stringa);
        int secondOccurrence = stringb.indexOf(stringa, firstOccurrence + stringa.length());
        
        if((firstOccurrence > -1) && (secondOccurrence > -1)){
            return true;
        }
        else{
            return false;
        }  
    }
    
    public void testing(){
        boolean result = false;
        
        //Test1
        String stringa = "by";
        String stringb = "A story by Abby Long";
        result = twoOccurrences(stringa, stringb);
        System.out.println("stringa = " + stringa + " and stringb = " + stringb);
        System.out.println("Result is " + result);
        
        //Test2
        stringa = "a";
        stringb = "banana";
        result = twoOccurrences(stringa, stringb);
        System.out.println("stringa = " + stringa + " and stringb = " + stringb);
        System.out.println("Result is " + result);
        
        //Test3
        stringa = "atg";
        stringb = "ctgtatgta";
        result = twoOccurrences(stringa, stringb);
        System.out.println("stringa = " + stringa + " and stringb = " + stringb);
        System.out.println("Result is " + result);
        
        //Test 4
        stringa = "an";
        stringb = "banana";
        String lastpartResult = lastPart(stringa, stringb);
        System.out.println("stringa = " + stringa + " and stringb = " + stringb);
        System.out.println("Result is " + lastpartResult);
        
        //Test 4
        stringa = "zoo";
        stringb = "forest";
        lastpartResult = lastPart(stringa, stringb);
        System.out.println("stringa = " + stringa + " and stringb = " + stringb);
        System.out.println("Result is " + lastpartResult);
    }
    
    public String lastPart(String stringa, String stringb){
        String found = stringb;
        int occurrence = stringb.indexOf(stringa);
        if(occurrence != -1){
            found = stringb.substring(occurrence + stringa.length(), stringb.length());
        }
        return found;
    }
}