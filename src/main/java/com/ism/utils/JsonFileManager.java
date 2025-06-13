package com.ism.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManager {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String DATA_DIR = "data/";

    static {
        objectMapper.registerModule(new JavaTimeModule());
        // Créer le dossier data s'il n'existe pas
        new File(DATA_DIR).mkdirs();
    }

    public static <T> List<T> readList(String filename, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(DATA_DIR + filename);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            ConsoleUtils.printError("Erreur lors de la lecture du fichier " + filename + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static <T> void writeList(String filename, List<T> list) {
        try {
            File file = new File(DATA_DIR + filename);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (IOException e) {
            ConsoleUtils.printError("Erreur lors de l'écriture du fichier " + filename + ": " + e.getMessage());
        }
    }

    public static <T> T readObject(String filename, Class<T> clazz) {
        try {
            File file = new File(DATA_DIR + filename);
            if (!file.exists()) {
                return null;
            }
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            ConsoleUtils.printError("Erreur lors de la lecture du fichier " + filename + ": " + e.getMessage());
            return null;
        }
    }

    public static <T> void writeObject(String filename, T object) {
        try {
            File file = new File(DATA_DIR + filename);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
        } catch (IOException e) {
            ConsoleUtils.printError("Erreur lors de l'écriture du fichier " + filename + ": " + e.getMessage());
        }
    }
}