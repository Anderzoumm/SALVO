package DAO;

import Exceptions.GenericException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Loja;
import utils.FileManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LojaRepository {

    private List<Loja> lojas = new ArrayList<>();

    private static final String FILE_NAME = "lojas.json";

    public List<Loja> listar() {
        return lojas;
    }

    public void inserir(Loja loja) throws Exception {

        lojas.add(loja);

        salvar();
    }

    public void salvar() throws Exception {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(lojas);

        FileManager.saveData(
                new FileOutputStream(FILE_NAME),
                json
        );
    }

    public void carregar() {

        try {

            String json =
                    FileManager.loadData(
                            new FileInputStream(FILE_NAME)
                    );

            Type tipo =
                    new TypeToken<List<Loja>>() {}.getType();

            lojas =
                    new Gson().fromJson(json, tipo);

            if(lojas == null){
                lojas = new ArrayList<>();
            }

        } catch (Exception e) {

            lojas = new ArrayList<>();
            throw new GenericException("ERRO AO CARREGAR CLIENTES: " + e.getMessage());
        }
    }
}