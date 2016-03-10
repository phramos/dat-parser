import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new Exception("Favor informar o <arquivo-origim> e o <destino-do-arquivo> a ser gerado.\nEx.: java -jar parse-dat linhas.csv linhas.dat");
            };

            CsvParser parser = new CsvParser(new CsvParserSettings());
            List<String[]> data = null;
            try {
               data = parser.parseAll(new FileReader(args[0]));
                generateCsvFile(args[1], data);
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo nao encontrado: "+ e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void generateCsvFile(String sFileName,  List<String[]> data) {
        try {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("param n := ");
            writer.append(Integer.toString(data.get(0).length-2)+";");
            writer.append('\n');
            writer.append("/* quantidade de nos distintos no problema(estacoes)*/");
            writer.append('\n');

            writer.append("param origem := 44;");
            writer.append('\n');
            writer.append("/* Usera como origem*/");
            writer.append('\n');

            writer.append("param destino := 51;");
            writer.append('\n');
            writer.append("/* Ibiza como destino*/");
            writer.append('\n');
            writer.append('\n');

            writer.append("param : E :  c :=");
            writer.append("\n");

            for (int i = 1; i < data.get(0).length; i++) {
                for (int j = 1; j < data.get(j).length-1; j++) {
//                    if (data.get(j)[i].equals("1")){ //Estações
                    if (data.get(j)[i].equals("1") || data.get(j)[i].equals("0")){ //Linhas
                        writer.append("      ");
//                        writer.append(data.get(0)[i]);
                        writer.append(Integer.toString(i-1));
                        writer.append(" ");
//                        writer.append(data.get(j)[0]);
                        writer.append(Integer.toString(j-1));
                        writer.append("  ");
                        writer.append(data.get(j)[i]);
                        writer.append("\t#" + data.get(0)[i] + "->"+data.get(j)[0]);
                        writer.append('\n');
                    }
                }
            }
            writer.append(";");
            writer.append("\n");
            writer.append("/* Todos os possiveis caminhos entre vertices do problema e seus respectivos pesos*/");
            writer.append("\n");
            writer.append("end;");

            writer.flush();
            writer.close();
        }
        catch(IOException e) {
            System.out.println("Esrro na criacao do arquivo: "+ e.getMessage());
        }
    }
}
