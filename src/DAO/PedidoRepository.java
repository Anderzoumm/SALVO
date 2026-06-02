package DAO;

import Exceptions.GenericException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Pedido;
import utils.FileManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {

    private List<Pedido> pedidos = new ArrayList<>();

    private static final String FILE_NAME = "pedidos.json";

    public void inserir(Pedido pedido) throws IOException {

        pedidos.add(pedido);

        salvar();
    }

    public boolean deletar(int index) throws IOException {

        if (index < 0 || index >= pedidos.size()) {
            return false;
        }

        pedidos.remove(index);

        salvar();

        return true;
    }

    public List<Pedido> listar() {
        return pedidos;
    }

    public List<Pedido> buscarPorCliente(String email) {

        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos) {

            if (pedido.getEmailCliente().equals(email)) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }

    public void salvar() throws IOException {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(pedidos);

        FileManager.saveData(
                new FileOutputStream(FILE_NAME),
                json
        );
    }

    public void carregar() {

        try {

            String json = FileManager.loadData(
                    new FileInputStream(FILE_NAME)
            );

            if (json == null || json.trim().isEmpty()) {

                pedidos = new ArrayList<>();

                return;
            }

            Type tipo = new TypeToken<List<Pedido>>(){}.getType();

            pedidos = new Gson().fromJson(json, tipo);

            if (pedidos == null) {
                pedidos = new ArrayList<>();
            }

        } catch (Exception e) {

            pedidos = new ArrayList<>();
            throw new GenericException("ERRO AO CARREGAR PEDIDOS: " + e.getMessage());
        }
    }
}