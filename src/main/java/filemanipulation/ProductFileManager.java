package filemanipulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductFileManager {

    private List<Product> products = new ArrayList<>();

    public void readProductsFromFile(Path path) {
        List<String> fileContent = readCsv(path);
        addProductFromFileContent(fileContent);
    }

    private void addProductFromFileContent(List<String> fileContent) {
        if (!fileContent.isEmpty()) {
            addProducts(fileContent);
        } else {
            throw new IllegalStateException("Empty file!");
        }
    }

    private void addProducts(List<String> fileContent) {
        for (String row : fileContent) {
            String[] productProperties = row.split(";");
            products.add(new Product(productProperties[0], productProperties[1], Integer.parseInt(productProperties[2])));
        }
    }

    private List<String> readCsv(Path path) {
        List<String> fileContent;
        try {
            fileContent = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't read file!", ioe);
        }
        return fileContent;
    }

    public void writePriceOverToFile(Path path, int price) {
        List<Product> productsToWrite = createFilteredListOfProducts(price);
        List<String> rowsToWrite = createListOfProductStrings(productsToWrite);
        writeCsvFile(path, rowsToWrite);
    }

    private void writeCsvFile(Path path, List<String> rowsToWrite) {
        try {
            Files.write(path, rowsToWrite);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't write file!");
        }
    }

    private List<String> createListOfProductStrings(List<Product> productsToWrite) {
        List<String> rowsToWrite = new ArrayList<>();
        if (!productsToWrite.isEmpty()) {
            for (Product actual : productsToWrite) {
                rowsToWrite.add(actual.toString());
            }
        } else {
            throw new IllegalArgumentException("Nothing to write!");
        }
        return rowsToWrite;
    }

    private List<Product> createFilteredListOfProducts(int price) {
        List<Product> productsToWrite = new ArrayList<>();
        if (!products.isEmpty()) {
            for (Product actual : products) {
                selectProducts(price, productsToWrite, actual);
            }
        } else {
            throw new IllegalArgumentException("Empty product list!");
        }
        return productsToWrite;
    }

    private void selectProducts(int price, List<Product> productsToWrite, Product actual) {
        if (actual.getPrice() > price) {
            productsToWrite.add(actual);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
