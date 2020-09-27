import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            if (record.get("Country").equals(country)){
                String result = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return result;
            }
        }
        return "NOT FOUND";
    }          

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser)
        {
            String exportProduct = record.get("Exports");
            if(exportProduct.contains(exportItem1) && exportProduct.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int numberOfExport = 0;
        for(CSVRecord record: parser){
            if (record.get("Exports").contains(exportItem)){
                numberOfExport = numberOfExport + 1;
            }
        }
        return numberOfExport;
    }

    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){
            if (record.get("Value (dollars)").length() > amount.length()){
                System.out.print(record.get("Country") + " ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }

    public void tester (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Malaysia"));
        //listExportersTwoProducts(parser, "fish", "nuts");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        //bigExporters(parser, "$999,999,999");
    }
}
