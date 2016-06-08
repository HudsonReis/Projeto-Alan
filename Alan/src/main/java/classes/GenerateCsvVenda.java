package classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCsvVenda {

    public GenerateCsvVenda() {
        //generateCsvFile("C:\\Users\\Giovane Pecora\\Desktop\\test.csv"); 
    }

    private static void generateCsvFile(List<VendaListagem> lista, String sFileName) {
        try {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("idVenda");
            writer.append(';');
            writer.append("idFilial");
            writer.append(';');
            writer.append("DataVenda");
            writer.append(';');
            writer.append("CodigoProduto");
            writer.append(';');
            writer.append("NomeProduto");
            writer.append(';');
            writer.append("IdUsuario");
            writer.append(';');
            writer.append("Quantidade");
            writer.append(';');
            writer.append("ValorUnitario");
            writer.append(';');
            writer.append("Valor");
            writer.append('\n');

            for (VendaListagem cl : lista) {
                writer.append("" + cl.getIdVenda());
                writer.append(';');
                writer.append("" + cl.getIdFilial());
                writer.append(';');
                writer.append("" + cl.getDataVenda());
                writer.append(';');
                writer.append("" + cl.getCodigoProduto());
                writer.append(';');
                writer.append(cl.getNomeProduto());
                writer.append(';');
                writer.append("" + cl.getIdUsuario());
                writer.append(';');
                writer.append("" + cl.getQuantidade());
                writer.append(';');
                writer.append("" + cl.getVrUnitario());
                writer.append(';');
                writer.append("" + cl.getValor());
                writer.append('\n');
            }

            //generate whatever data you want
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
