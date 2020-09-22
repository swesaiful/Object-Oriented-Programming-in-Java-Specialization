public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        
        if(dna == dna.toUpperCase()){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        else if(dna == dna.toLowerCase()){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        
        int startIndex = dna.indexOf(startCodon);
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
       
        if((startIndex == -1) || (stopIndex == -1)){
            return "";
        }
       
        if((stopIndex - startIndex) %3 == 0){
            result = dna.substring(startIndex, stopIndex + 3);
        }
        else {
            return "";
        }
        
        return result;
    }
    
    public void testSimpleGene(){
        // Test 1: DNA with no “ATG”
        String dna = "ACGGTGGTAAG";
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("DNA with no ATG " + dna);
        String gene = findSimpleGene(dna, startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        // Test 2: DNA with no “TAA”
        dna = "ACGATGGTTAG";
        startCodon = "ATG";
        stopCodon = "TAA";
        System.out.println("DNA with no TAA " + dna);
        gene = findSimpleGene(dna, startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        // Test 3: DNA with no “ATG” or “TAA”
        dna = "ACGGTGGTGAG";
        startCodon = "ATG";
        stopCodon = "TAA";
        System.out.println("DNA with no ATG or TAA " + dna);
        gene = findSimpleGene(dna, startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        // Test 4:DNA with ATG, TAA and the substring 
        // between them is a multiple of 3 (a gene)
        dna = "ACGATGGTGGTGAGTTAA";
        startCodon = "ATG";
        stopCodon = "TAA";
        System.out.println("DNA with ATG, TAA and multiple of mod 3 " + dna);
        gene = findSimpleGene(dna, startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        // Test 5: DNA with ATG, TAA and the substring 
        // between them is not a multiple of 3
        dna = "ACGATGGTGGTGAGTAA";
        startCodon = "ATG";
        stopCodon = "TAA";
        System.out.println("DNA with ATG, TAA and not multiple of mod 3 " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        // Test 6: DNA with ATG, TAA and with lowercase
        dna = "gatgctataat";
        startCodon = "ATG";
        stopCodon = "TAA";
        System.out.println("DNA with ATG, TAA and not multiple of mod 3 " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
    }
}