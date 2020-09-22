public class Part2 {
    public int howMany(String stringa, String stringb){
        int currOccurrence = 0;
        int countHowManyTimes = 0;
        
        while(true){
            currOccurrence = stringb.indexOf(stringa, currOccurrence);
            if(currOccurrence == -1){
                break;
            }
            currOccurrence = currOccurrence + stringa.length();             
            countHowManyTimes = countHowManyTimes + 1;
        }
        return countHowManyTimes;
    }
    
    public void testHowMany(){
        // Test 1
        int occurrence = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("GAA in ATGAACGAATTGAATC occurs " + occurrence + " times");
        
        // Test 2
        occurrence = howMany("AA", "ATAAAA");
        System.out.println("AA in ATAAAA occurs " + occurrence + " times");        
    }
}
