// copied code style from the lecture 

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff %3 == 0){
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length(); // nothing is found when searching
    }
    
    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int index = findStopCodon(dna,0,"TAA");
        if (index != 9) System.out.println("error on 9");
        index = findStopCodon(dna,9,"TAA");
        if (index != 21) System.out.println("error on 21");
        index = findStopCodon(dna,1,"TAA");
        if (index != 26) System.out.println("error on 26");
        index = findStopCodon(dna,0,"TAG");
        if (index != 26) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
    
        int minIndex = 0;
        
        if(taaIndex == -1 || (tgaIndex != -1) && tgaIndex < taaIndex){
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex,minIndex + 3);
    }
    
    public void testFindGene(){
        // Test 1: DNA with no “ATG”
        String dna = "xxxyyyTAAxx";
        String gene = findGene(dna);
        System.out.println("DNA with no ATG = " + dna);
        System.out.println("Gene is: " + gene);
        
        // Test 2: DNA with "ATG” and one valid stop codon
        dna = "xxxyyyATGxxxyyyTAA";
        gene = findGene(dna);
        System.out.println("DNA with ATG and one valid stop codons = " + dna);
        System.out.println("Gene is: " + gene);
        
        // Test 3: DNA with “ATG” and multiple valid stop codons
        dna = "xxxyyyATGTAGTAATGA";
        gene = findGene(dna);
        System.out.println("DNA with ATG and multiple valid stop codons = " + dna);
        System.out.println("Gene is: " + gene);
        
        // Test 4: DNA with “ATG” and no valid stop codons
        dna = "xxxyyyATGxxx";
        gene = findGene(dna);
        System.out.println("DNA with ATG and no valid stop codons = " + dna);
        System.out.println("Gene is: " + gene);
        
        // Test 5: DNA with "ATG" and one valid stop codon
        dna = "ATGxxxyyyTAA";
        gene = findGene(dna);
        System.out.println("DNA with ATG and one valid stop codon = " + dna);
        System.out.println("Gene is: " + gene);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna.substring(startIndex, dna.length()));
            if(currentGene.isEmpty()){
                break;
            }
            
            System.out.println("Current Gene is: " + currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            String currentGene = findGene(dna.substring(startIndex, dna.length()));
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println("Current Gene is: " + currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            count = count + 1;
        }
        return count;
    }
    
    public void testCountGenes(){
        String dna = "xxxATGyTAGzzTAAxxATGTAGyy";
        int results = countGenes(dna);
        System.out.println("The dna is: " + dna + "and the number of genes in dna is: " + results);
    }
}
