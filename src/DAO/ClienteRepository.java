package DAO;

import Exceptions.GenericException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Cliente;
import utils.FileManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.GsonBuilder;

public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();

    private static final String FILE_NAME = "clientes.json";

    public void inserir(Cliente cliente) throws IOException {

        if (buscarPorEmail(cliente.getEmail()) == null) {

            cliente.setId(clientes.size() + 1);

            clientes.add(cliente);

            salvar();
        }
    }

    public boolean deletar(int id) throws IOException {

        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getId() == id) {

                clientes.remove(i);

                salvar();

                return true;
            }
        }

        return false;
    }

    public List<Cliente> listar() {
        return clientes;
    }

    public Cliente buscarPorEmail(String email) {

        for (Cliente cliente : clientes) {

            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }

        return null;
    }

    public void salvar() throws IOException {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(clientes);

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
                clientes = new ArrayList<>();
                return;
            }

            Type tipo = new TypeToken<List<Cliente>>(){}.getType();

            clientes = new Gson().fromJson(json, tipo);

            if (clientes == null) {
                clientes = new ArrayList<>();
            }

        } catch (Exception e) {
            clientes = new ArrayList<>();
            throw new GenericException("ERRO AO CARREGAR CLIENTES: " + e.getMessage());
        }
    }
}