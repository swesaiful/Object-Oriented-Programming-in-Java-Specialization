public class Part1 {
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        String result = "";
        if(startIndex == -1){ // no ATG
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if(endIndex == -1){ // no TAA
            return "";
        }
        if((endIndex - startIndex) %3 == 0){
            result = dna.substring(startIndex, endIndex + 3);
        }
        else {
            return "";
        }
        return result;
    }
    
    public void testSimpleGene(){
        // Test 1: DNA with no “ATG”
        String dna = "ACGGTGGTAAG";
        System.out.println("DNA with no ATG " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 2: DNA with no “TAA”
        dna = "ACGATGGTTAG";
        System.out.println("DNA with no TAA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 3: DNA with no “ATG” or “TAA”
        dna = "ACGGTGGTGAG";
        System.out.println("DNA with no ATG or TAA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 4:DNA with ATG, TAA and the substring 
        // between them is a multiple of 3 (a gene)
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        //dna = "ACGATGGTGGTGAGTTAA";
        System.out.println("DNA with ATG, TAA and multiple of mod 3 " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 5: DNA with ATG, TAA and the substring 
        // between them is not a multiple of 3
        dna = "ACGATGGTGGTGAGTAA";
        System.out.println("DNA with ATG, TAA and not multiple of mod 3 " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}

