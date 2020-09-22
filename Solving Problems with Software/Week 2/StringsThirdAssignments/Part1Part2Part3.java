// copied code style from the lecture 

import edu.duke.*;

public class Part1Part2Part3 {
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
        return dna.length();
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
            
            System.out.println("The genes are: " + currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public void testPrintAllGenes (){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        printAllGenes(dna.toUpperCase());
    }

    public StorageResource getAllGenes(String dna){     
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna.substring(startIndex, dna.length()));
            if(currentGene.isEmpty()){
                break;
            }            
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }

    public void testGetAllGenes(){
        String dna = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        StorageResource sr = getAllGenes(dna);
        for (String s: sr.data()){
            System.out.println(s);
        }
    }

    public int countSubString(String subStr, String str){
        int currIndex = 0;
        int count = 0;
        while (true){
            currIndex = str.indexOf(subStr, currIndex);
            if (currIndex == -1){
                break;
            }
            currIndex = currIndex + subStr.length();
            count = count + 1;
        }
        return count;
    }

    public float cgRatio(String dna){
        int nOfC = countSubString("C", dna);
        int nOfG = countSubString("G", dna);
        float cgRatio = (float)(nOfC + nOfG)/dna.length();
        return cgRatio;
    }

    public int countCTG(String dna){
        int result = (countSubString("CTG", dna));
        return result;
    }    

    public void processGenes(StorageResource sr){
        
        int maxLength = 0;
        int countLength = 0;
        int countCgRatio = 0;
        
        for(String str: sr.data()){
            str = str.toUpperCase();
            
            StorageResource geneList = getAllGenes(str);
            System.out.println("How many genes are in this file?: " + geneList.size());
            //System.out.println("Gene lists are: ");
            for (String gene: geneList.data()){ 

                if (gene.length() > maxLength){
                    maxLength = gene.length();
                }
                if (gene.length() > 60){
                    countLength = countLength + 1;
                }
                if (cgRatio(gene) > 0.35){
                    countCgRatio = countCgRatio + 1;
                }
            }
            System.out.println("How many genes in this file are longer than 60?: " + countLength);
            System.out.println("How many genes in this file have cgRatio greater than 0.35?: " + countCgRatio);
            System.out.println("What is the length of the longest gene in the collection of genes found in this file?:  " + maxLength);
        }
    }

    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        sr.add(dna);
        int codonCRTG = countCTG(dna);
        System.out.println("How many times does the codon CTG appear in this strand of DNA? " + codonCRTG);
        processGenes(sr);
    }
}
